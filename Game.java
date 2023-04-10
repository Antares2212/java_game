package com.tutorial;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game  extends Canvas implements Runnable{

    static  final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    static boolean paused = false;
    int diff = 0;

    //Переменные
    private Random r = new Random();
    private Handlers handler = new Handlers();
    private HUB hub;
    private Spawn spawner;
    private Menu menu;
    private Shop shop;

    //Вызов методов
    public enum STATE{
        Menu,
        Select,
        Help,
        Shop,
        Game,
        End
    }

    static STATE gameState;

    static {
        gameState = STATE.Menu;
    }

    //Создание Окна и вызов Меню игры
    public Game(){
        handler = new Handlers();
        hub = new HUB();
        shop = new Shop(handler);
        menu = new Menu(this, handler);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Wave", this);

        hub = new HUB();
        spawner = new Spawn(handler, hub, this);
        Random r = new Random();

        if (gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
        }
        else {
            for (int i = 0; i < 15; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
    }

    synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    private synchronized void stop(){
        try {
            thread.join();
            running = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

        if (gameState == STATE.Game){
            if (!paused){
                hub.tick();
                spawner.tick();
                handler.tick();

                if (HUB.HEALTH <= 0){
                    HUB.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemys();

                    for (int i = 0; i < 20; i++){
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                    }
                }
            }
        }
        else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
            menu.tick();
            handler.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (paused){
            g.setColor(Color.white);
            g.drawString("PAUSED", 550, 25);
        }

        if (gameState == STATE.Game){
            hub.render(g);
        }
        else if (gameState == STATE.Shop){
                shop.render(g);
            }
            else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
                menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    static int clamp(float var, int min, int max){
        if (var >= max)
            return max;
        else if (var <= min)
            return min;
        else
            return (int) var;
    }

    public static void main(String args[]){
        new Game();
    }
}
