package io.github.firstGame.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class DropSpawner {
    private Array<Drop> drops;

    private float dropTimer;
    private FitViewport viewport;

    public DropSpawner(FitViewport viewport){
        this.viewport = viewport;
        this.drops = new Array<Drop>();
    }

    public void update(){
        dropTimer += Gdx.graphics.getDeltaTime();
        if(dropTimer>1f){
            dropTimer = 0;
            createDroplet();
        }

        for(Drop drop: drops){
            drop.update();
        }
        killDroplets();
    }

    public void killDroplets(){
        for(int i = drops.size -1; i>=0;i--){
            if(!drops.get(i).isAlive()){
                drops.removeIndex(i);
            }
        }
    }

    public void createDroplet(){
        Drop drop = new Drop(viewport);
        drops.add(drop);
    }

    public void draw(SpriteBatch spriteBatch){
        for(Drop drop: drops){
            drop.draw(spriteBatch);
        }
    }

    public Array<Drop> getDrops(){
        return drops;
    }

}
