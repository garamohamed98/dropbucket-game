package io.github.firstGame.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Drop {

    private Sprite sprite;
    private boolean isAlive;
    private Rectangle rectangle;
    public Drop (Viewport viewport){
        this.sprite = new Sprite(new Texture("drop.png"));
        this.sprite.setSize(1,1);
        this.sprite.setX(MathUtils.random(0f,viewport.getWorldWidth()-sprite.getWidth()));
        this.sprite.setY(viewport.getWorldWidth());
        this.isAlive = true;
        this.rectangle = new Rectangle();
    }

    public void update(){
        this.sprite.translateY(-2f* Gdx.graphics.getDeltaTime());
        this.rectangle.set(
            this.sprite.getX(),
            this.sprite.getY(),
            this.sprite.getWidth(),
            this.sprite.getHeight()
        );
        if(this.sprite.getY() +sprite.getHeight()< 0){
            isAlive = false;
        }
    }

    public void draw(SpriteBatch spriteBatch){
        this.sprite.draw(spriteBatch);
    }

    public boolean isAlive(){
        return isAlive;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public void kill(){
        this.isAlive = false;
    }

}
