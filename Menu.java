package com.tutorial;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handlers handler;
    private HUB hub;
    private Random r = new Random();

    Menu(Game game, Handlers handler){
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == Game.STATE.Menu) {
            //play button
            if (mouseOver(mx, my, 150)) {
                Game.gameState = Game.STATE.Select;

//                Sounds.getSound("menu_sound").play();
                return;
            }

            //help button
            if (mouseOver(mx, my, 250)) {
                Game.gameState = Game.STATE.Help;
            }

            //quit button
            if (mouseOver(mx, my, 350)) {
                System.exit(1);
            }
        }

        if (Game.gameState == Game.STATE.Select) {
            //normal button
            if (mouseOver(mx, my, 150)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

                game.diff = 0;

//                Sounds.getSound("menu_sound").play();
            }

            //hard button
            if (mouseOver(mx, my, 250)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

                game.diff = 1;
            }

            //back button
            if (mouseOver(mx, my, 350)) {
                Game.gameState = Game.STATE.Menu;
            }
        }

        //back button for help
        if (Game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 350)) {
                Game.gameState = Game.STATE.Menu;
            }
        }

        //try again button for play
        if (Game.gameState == Game.STATE.End) {

            if (mouseOver(mx, my, 350)) {
                Game.gameState = Game.STATE.Menu;
                HUB.setLevel(1);
                HUB.setScore();
            }
        }
    }

    public void  mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int y) {
        return mx > 210 && mx < 210 + 200 && my > y && my < y + 64;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if (Game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Wave", 255, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 280, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 280, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Exit", 280, 390);
        }
        else if (Game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);
            Font fnt3 = new Font("arial", Font.BOLD, 25);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 255, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies", 10, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 280, 390);
        }
        else if (Game.gameState == Game.STATE.End){
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);
            Font fnt3 = new Font("arial", Font.BOLD, 25);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 200, 70);

            g.setFont(fnt3);
            g.drawString("You Lost with a score of: " + HUB.getScore(), 160, 200);
            g.setFont(fnt3);
            g.drawString("On level: " + HUB.getLevel(), 160, 250);

            g.setFont(fnt2);
            g.drawRect(220, 350, 200, 64);
            g.drawString("Try Again", 250, 390);
        }
        else if (Game.gameState == Game.STATE.Select) {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("SELECT DIFFICULTY", 75, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 265, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 280, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 280, 390);
        }
    }

    public Game getGame() {
        return game;
    }
}
