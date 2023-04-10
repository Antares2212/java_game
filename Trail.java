package com.tutorial;

import java.awt.*;

public class Trail extends GameObject{

    private int width, height;

    private float alpha = 1;
    private float life;

    private Handlers handler;
    private Color color;

    Trail(int x, int y, ID id, Color color, int width, int height, float life, Handlers handler){
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public void tick() {
        if (alpha > life){
            alpha -= (life - 0.0001f);
        }
        else handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(makeTranparent(alpha));

        g.setColor(color);
        g.fillRect((int)x, (int) y, width, height);

        g2d.setComposite(makeTranparent(1));
    }

    private AlphaComposite makeTranparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }

    public Rectangle getBounds() {
        return null;
    }

    public void setHandler(Handlers handler) {
        this.handler = handler;
    }
}
