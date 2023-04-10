package com.tutorial;

import java.awt.*;

public class FastEnemy extends  GameObject{

    private Handlers handler;

    FastEnemy(int x, int y, ID id, Handlers handler){
        super(x, y, id);

        this.handler = handler;

        velX = 2;
        velY = 9;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 42) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail((int)x, (int) y, ID.Trail, Color.cyan, 16, 16, 0.01f, handler));
    }

    public void render(Graphics g) {

        g.setColor(Color.CYAN);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
