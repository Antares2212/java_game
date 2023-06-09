package com.tutorial;

import java.awt.*;

public class BasicEnemy extends  GameObject{

    private Handlers handler;

    BasicEnemy(int x, int y, ID id, Handlers handler){
        super(x, y, id);

        this.handler = handler;

        velX = 3;
        velY = 3;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 42) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.red, 16, 16, 0.01f, handler));
    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
