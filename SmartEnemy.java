package com.tutorial;

import java.awt.*;

public class SmartEnemy extends  GameObject{

    private Handlers handler;
    private GameObject player;

    SmartEnemy(int x, int y, ID id, Handlers handler){
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++){
            if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }

    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));

        velX = (float) ((-1.0/distance) * diffX);
        velY = (float) ((-1.0/distance) * diffY);

        //if (y <= 0 || y >= Game.HEIGHT - 42) velY *= -1;
        //if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail((int) x, (int)y, ID.Trail, Color.green, 16, 16, 0.01f, handler));
    }

    public void render(Graphics g) {

        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
