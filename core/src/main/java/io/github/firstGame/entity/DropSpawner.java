package io.github.firstGame.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class DropSpawner {
    private Array<Drop> drops;
    private Array<Bomb> bombs;

    private float dropTimer;
    private FitViewport viewport;

    public DropSpawner(FitViewport viewport){
        this.viewport = viewport;
        this.drops = new Array<Drop>();
        this.bombs = new Array<Bomb>();
    }

    public void update(){
        dropTimer += Gdx.graphics.getDeltaTime();
        if(dropTimer > 1f){
            dropTimer = 0;
            spawnEntity();
        }

        for(Drop drop: drops){
            drop.update();
        }
        for(Bomb bomb: bombs){
            bomb.update();
        }
        killEntities();
    }

    public void killEntities(){
        for(int i = drops.size -1; i>=0;i--){
            if(!drops.get(i).isAlive()){
                drops.removeIndex(i);
            }
        }
        for(int i = bombs.size -1; i>=0;i--){
            if(!bombs.get(i).isAlive()){
                bombs.removeIndex(i);
            }
        }
    }

    public void spawnEntity(){
        boolean notBomb = MathUtils.randomBoolean();
        if (notBomb){
            Drop drop = new Drop(viewport);
            drops.add(drop);
        }else{
            Bomb bomb = new Bomb(viewport);
            bombs.add(bomb);
        }
    }

    public void draw(SpriteBatch spriteBatch){
        for(Drop drop: drops){
            drop.draw(spriteBatch);
        }
        for(Bomb bomb: bombs){
            bomb.draw(spriteBatch);
        }
    }

    public Array<Drop> getDrops(){
        return drops;
    }

    public Array<Bomb> getBombs(){
        return bombs;
    }


}
