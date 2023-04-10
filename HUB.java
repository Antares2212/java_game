package com.tutorial;

import java.awt.*;

public class HUB {

    static int HEALTH = 100;
    private int greenValue = 255;

    private static int score;
    private static int level = 1;

    public void tick(){
        HEALTH = Game.clamp(HEALTH, 0,100);
        greenValue = Game.clamp(greenValue,0, 255);

        greenValue = HEALTH*2;

        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score: " + score, 10, 64);
        g.drawString("Level: " + level, 10, 82);
        g.drawString("Space for Shop", 10, 100);
    }

    static void setScore(){
        HUB.score = 0;
    }

    static int getScore(){
        return score;
    }

    static int getLevel(){
        return level;
    }

    static void setLevel(int level){
        HUB.level = level;
    }
}
