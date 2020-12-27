package me.YayL.game.controllers;

import me.YayL.game.entity.Computer;
import me.YayL.game.entity.Player;
import me.YayL.game.entity.handler.GameObject;
import me.YayL.game.entity.handler.MovingEntity;
import me.YayL.game.graphics.Display;

public class AIController implements Controller{

    static double[] c, b;

    static boolean[] controls; // [0] Up [1] Down

    public AIController(){
        controls =  new boolean[]{false, false};
    }


    public static void profAI(GameObject object) { //
        hardAI(object);

    }

    public static void hardAI(GameObject object) { // Follow the ball through the middle point

        GameObject b = MovingEntity.gameObjects.get(2);

        double ballMiddle = b.getPos().getY() + b.getSize().getHeight()/2f;
        double compMiddle = object.getPos().getY() + object.getSize().getHeight()/2f;

        if(ballMiddle > compMiddle){
            controls[0] = false;
            controls[1] = true;
        }else if(ballMiddle < compMiddle){
            controls[1] = false;
            controls[0] = true;
        }else{
            controls[1] = false;
            controls[0] = false;
        }

    }

    public static void  medAI(GameObject object) { // Over shoot ball and stay still until balls changes velocity again
        c = getObjectSpecs( object);// [0] Left [1] Right [2] Top [3] Bottom
        b = getObjectSpecs( MovingEntity.gameObjects.get(2));// [0] Left [1] Right [2] Top [3] Bottom

        double middle = (b[2]+b[3])/2;

        if(middle >= Display.Height/2f) { // Lower half
            if(b[3] > c[2] +5) { // If ball bottom is above computer top
                controls[0] = false;
                controls[1] = true;
            }else if(b[3] < c[2] +5) { // If ball bottom is below computer top
                controls[1] = false;
                controls[0] = true;
            }
        }else if(middle <= Display.Height/2f) { // Upper half
            if(b[2] > c[3] -5) { // If ball top is below computer bottom
                controls[0] = false;
                controls[1] = true;
            }else if(b[2] < c[3] -5) { // If ball top is above computer bottom
                controls[1] = false;
                controls[0] = true;
            }
        }

    }

    public static void easyAI(GameObject object) { // Hold to middle with straight line separating window into upper half and lower half and when ball goes onto the computers side of the field you follow it

        GameObject b = MovingEntity.gameObjects.get(2);

        double verticalMiddle = Display.Width/2f;
        double horizontalMiddle = Display.Height/2f;

        boolean pMove = false;
        boolean cMove = false;

        if(object instanceof Player){
            if( 3*(verticalMiddle/2) > b.getPos().getX()){ // If ball within the adjacent quartile
                pMove = true;
            }
        }else if(object instanceof Computer){
            if( (verticalMiddle/2) < b.getPos().getX()){ // If ball within the adjacent quartile
                cMove = true;
            }

        }

        if(pMove || cMove){
            if(object.getPos().getY() +5 < b.getPos().getY()){ // If object is lower down then ball
                controls[0] = false;
                controls[1] = true;
            }else if(object.getPos().getY() + object.getSize().getHeight() -5 > b.getPos().getY()) { // If object is higher than the ball
                controls[1] = false;
                controls[0] = true;
            }else{
                controls[0] = false;
                controls[1] = false;
            }

        }else{
            if(object.getPos().getY() +5 < horizontalMiddle - object.getSize().getHeight()/2f){ // If object is lower down than middle mark

                controls[0] = false;
                controls[1] = true;
            }else if(object.getPos().getY() + object.getSize().getHeight() -5 > horizontalMiddle + object.getSize().getHeight()/2f){ // If object is higher up than middle mark

                controls[1] = false;
                controls[0] = true;
            }else{
                controls[0] = false;
                controls[1] = false;
            }
        }

    }

    public static void calculateMovement(int diff, GameObject object) {
        if(diff == 0) {
            AIController.easyAI(object);
        }else if(diff == 1) {
            AIController.medAI(object);
        }else if(diff == 2) {
            AIController.hardAI(object);
        }else if(diff == 3) {
            AIController.profAI(object);
        }
    }

    protected static double[] getObjectSpecs(GameObject object) {
        return new double[]{
                object.getPos().getX(), // Left part of the object
                object.getPos().getX() + object.getSize().getWidth(), // Right part of the object
                object.getPos().getY(), // Top part of the object
                object.getPos().getY() + object.getSize().getHeight()}; // Bottom of the object
    }

    @Override
    public boolean isRequestingUp() {
        return controls[0];
    }

    @Override
    public boolean isRequestingDown() {
        return controls[1];
    }

    @Override
    public boolean isRequestingRight() {
        return false;
    }

    @Override
    public boolean isRequestingLeft() {
        return false;
    }
}
