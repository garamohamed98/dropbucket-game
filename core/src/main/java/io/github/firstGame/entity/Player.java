package io.github.firstGame.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Player {

    public Sprite sprite;
    float speed = 4f;
    float delta = Gdx.graphics.getDeltaTime();
    Vector2 touchPos;
    FitViewport viewport;
    public Rectangle rectangle;

    public Player(FitViewport viewport){
        this.sprite = new Sprite(new Texture("bucket.png"));
        this.sprite.setSize(1,1);
        this.rectangle = new Rectangle();
        this.viewport = viewport;
        this.touchPos = new Vector2();
    }

    public void input(){

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            this.sprite.translateX(speed* delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            this.sprite.translateX(-speed*delta);
        }

        if(Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            this.sprite.setCenterX(touchPos.x);
        }
    }

    public void update(){
        sprite.setX(MathUtils.clamp(
            sprite.getX(),
            0,
            viewport.getWorldWidth()-sprite.getWidth()
            )
        );

        rectangle.set(
            sprite.getX(),
            sprite.getY(),
            sprite.getWidth(),
            sprite.getHeight()
        );

    }

    public void draw(SpriteBatch spriteBatch){
        sprite.draw(spriteBatch);
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}
