package com.tutorial;

import java.util.Random;

public class Spawn {

    private Handlers handler;
    private HUB hub;
    private Game game;
    private Random r = new Random();

    private int scoreKeep;

    Spawn(Handlers handler, HUB hub, Game game){
        this.handler = handler;
        this.hub = hub;
        this.game = game;
    }

    public void tick(){
        scoreKeep++;

        if (scoreKeep >= 300){
            scoreKeep = -10;

            HUB.setLevel(HUB.getLevel() + 1);

            if (game.diff == 0){
                if (HUB.getLevel() == 2){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }
                else if (HUB.getLevel() == 3){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }
                else if (HUB.getLevel() == 4){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }
                else if (HUB.getLevel() == 5){
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                }
                else if (HUB.getLevel() == 6){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }
                else if (HUB.getLevel() == 7){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }
                else if (HUB.getLevel() == 10){
                    handler.clearEnemys();
                    handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 46, -120, ID.EnemyBoss, handler));
                }
            }
            else if (game.diff == 1){

                if (HUB.getLevel() == 2){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }
                else if (HUB.getLevel() == 3){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }
                else if (HUB.getLevel() == 4){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }
                else if (HUB.getLevel() == 5){
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                }
                else if (HUB.getLevel() == 6){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }
                else if (HUB.getLevel() == 7){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }
                else if (HUB.getLevel() == 10){
                    handler.clearEnemys();
                    handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 46, -120, ID.EnemyBoss, handler));
                }
            }
        }
    }
}
