package com.tutorial;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends  GameObject{

    private Handlers handler;

    EnemyBossBullet(int x, int y, ID id, Handlers handler){
        super(x, y, id);

        this.handler = handler;

        Random r = new Random();
        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

//        if (y <= 0 || y >= Game.HEIGHT - 42) velY *= -1;
//        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        if (y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.green, 16, 16, 0.04f, handler));
    }

    public void render(Graphics g) {

        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
