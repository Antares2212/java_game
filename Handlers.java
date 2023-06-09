package com.tutorial;

import java.awt.*;
import java.util.LinkedList;

public class Handlers {

    private boolean clearing = false;

    LinkedList<GameObject> object = new LinkedList<>();

    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            if (clearing){
                return;
            }
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }



    public void  addObject(GameObject object){
        this.object.add(object);
    }

    void removeObject(GameObject object){
        this.object.remove(object);
    }


    void clearEnemys(){
        clearing = true;
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if (tempObject.getId() == ID.Player) {
                object.clear();

                if (Game.gameState != Game.STATE.End)
                    addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
            }
        }
        clearing = false;
    }
}
