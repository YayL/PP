package me.YayL.game.controllers;

import me.YayL.game.entity.handler.GameObject;
import me.YayL.game.entity.handler.MovingEntity;
import me.YayL.game.graphics.Display;

public class AIController implements Controller{

    static double[] c, b;

    static boolean[] controls; // [0] Up [1] Down

    public AIController(){
        this.controls =  new boolean[]{false, false};
    }


    public static void profAI(GameObject object) { //

    }

    public static void hardAI(GameObject object) { // Hold to middle with straight line seperating window into upper half and lower half and when ball goes onto the computers side of the field you follow it

    }

    public static void  medAI(GameObject object) { // Follow the ball

        c = getObjectSpecs( object); // [0] Left [1] Right [2] Top [3] Bottom
        b = getObjectSpecs( MovingEntity.gameObjects.get(2));// [0] Left [1] Right [2] Top [3] Bottom

        if (c[3] > b[2]+50 ) { // If paddle bottom is below ball top
            controls[1] = false;
            controls[0] = true;
        }else if(c[2] < b[3] - 50){ // If paddle top is above ball bottom
            controls[0] = false;
            controls[1] = true;
        }

    }

    public static void easyAI(GameObject object) { // Over shoot ball and stay still until balls changes velocity again
        c = getObjectSpecs( object);// [0] Left [1] Right [2] Top [3] Bottom
        b = getObjectSpecs( MovingEntity.gameObjects.get(2));// [0] Left [1] Right [2] Top [3] Bottom

        double middle = (b[2]+b[3])/2;

        if(middle > Display.Height/2) { // Lower half
            if(b[3] > c[2]+5) { // If ball bottom is above computer top
                controls[0] = false;
                controls[1] = true;
            }else if(b[3] < c[2]+5) { // If ball bottom is below computer top
                controls[1] = false;
                controls[0] = true;
            }
        }else if(middle < Display.Height/2) { // Upper half
            if(b[2] > c[3]-5) { // If ball top is below computer bottom
                controls[0] = false;
                controls[1] = true;
            }else if(b[2] < c[3]-5) { // If ball top is above computer bottom
                controls[1] = false;
                controls[0] = true;
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
