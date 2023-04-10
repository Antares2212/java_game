package com.tutorial;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends  GameObject{

    private Handlers handler;
    private Random r = new Random();

    private int timer = 80;
    private int timer2 = 50;

    EnemyBoss(int x, int y, ID id, Handlers handler){
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(timer <= 0)
            velY = 0;
        else
            timer--;

        if (timer <= 0)
            timer2--;
        if (timer2 <= 0) {
            if (velX == 0)
                velX = 2;
                if (velX > 0)
                velX += 0.01f;
                else if (velX < 0);
                velX -= 0.01f;

                velX = Game.clamp(velX, -10, 10);

                int spawn = r.nextInt(10);
                if (spawn == 0) handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler));
        }

//        if (y <= 0 || y >= Game.HEIGHT - 120) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 96) velX *= -1;

        //handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.red, 96, 96, 0.2f, handler));
    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 96, 96);
    }
}
