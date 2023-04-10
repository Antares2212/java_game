package com.tutorial;

import java.awt.*;

public abstract class GameObject {

    float x, y;
    protected ID id;
    float velX;
    float velY;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract  Rectangle getBounds();

    float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public ID getId(){
        return id;
    }
    void setVelX(float velX){
        this.velX = velX;
    }
    void setVelY(float velY){
        this.velY = velY;
    }

}
