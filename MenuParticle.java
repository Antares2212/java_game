package com.tutorial;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends  GameObject{

    private Handlers handler;

    private Color col;

    MenuParticle(int x, int y, ID id, Handlers handler){
        super(x, y, id);

        this.handler = handler;

        Random r = new Random();
        velX = (r.nextInt(5 - -7) + -7);
        velY = (r.nextInt(5 - -7) + -7);
        if (velX == 0)
            velX = 1;
        if (velY == 0)
            velY = 1;

        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 42) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail((int)x, (int) y, ID.Trail, col, 16, 16, 0.1f, handler));
    }

    public void render(Graphics g) {

        g.setColor(col);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
