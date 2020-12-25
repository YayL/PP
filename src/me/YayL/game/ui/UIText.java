package me.YayL.game.ui;

import me.YayL.game.entity.handler.Size;
import me.YayL.game.graphics.ImageUtils;
import me.YayL.game.states.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class UIText extends UIComponent{

    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color color;

    private boolean dropShadow;
    private int dropShadowOffset;
    private Color shadowColor;

    private Font font;

    public UIText(String text) {
        this.text = text;
        this.fontSize = 24;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = Font.MONOSPACED;
        this.color = Color.WHITE;
        
        this.dropShadow = true;
        this.dropShadowOffset = 2;
        this.shadowColor = new Color(95, 91, 91);
    }

    @Override
    public Image getSprite() {
        BufferedImage img = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = img.createGraphics();
        graphics.setFont(font);

        if(dropShadow){
            graphics.setColor(shadowColor);
            graphics.drawString(text, padding.getLeft() + dropShadowOffset, fontSize + padding.getTop() + dropShadowOffset);
        }

        graphics.setColor(color);
        graphics.drawString(text, padding.getLeft(), fontSize + padding.getTop());

        graphics.dispose();
        return img;
    }

    @Override
    public void update(State state) {
        createFont();
        calculateSize();
    }

    private void calculateSize() {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        size = new Size(
                fontMetrics.stringWidth(text) + padding.getHorizontal(),
                fontMetrics.getHeight() + padding.getVertical()
        );
    }

    private void createFont() {
        font = new Font(fontFamily, fontStyle, fontSize);
    }
}
