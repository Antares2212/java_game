package com.tutorial;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{

    Random r = new Random();
    Handlers handler;

    public Player(int x, int y, ID id, Handlers handler){
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp((int) x, 0, Game.WIDTH - 38);
        y = Game.clamp((int)y, 0, Game.HEIGHT - 61);

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.white, 32, 32, 0.3f, handler));

        collision();
    }

    private void  collision() {
        for (int i = 0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUB.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);
    }


}
