package io.github.firstGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.firstGame.entity.Bomb;
import io.github.firstGame.entity.Drop;
import io.github.firstGame.entity.DropSpawner;
import io.github.firstGame.entity.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {

    Texture backgroundTexture;
    Player player;

    Sound dropSound;
    Sound bombSound;
    Music music;

    SpriteBatch spriteBatch;
    FitViewport viewport;

    DropSpawner dropSpawner;
    int score;
    private BitmapFont font;


    @Override
    public void create() {
        backgroundTexture = new Texture("background.png");
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        bombSound = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8,5);



        this.player = new Player(viewport);
        this.dropSpawner = new DropSpawner(viewport);


        music.setLooping(true);
        music.setVolume(.5f);
        music.play();

        font = new BitmapFont();
        font.getData().setScale(0.02f);
        font.setUseIntegerPositions(false);
        score = 0;

    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }


    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }


    private void input(){
        player.input();
    }

    private void logic(){

        player.update();
        dropSpawner.update();

        checkCollisions();

    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        spriteBatch.draw(backgroundTexture,0,0,worldWidth,worldHeight);

        player.draw(spriteBatch);
        dropSpawner.draw(spriteBatch);

        font.draw(spriteBatch, "Score: " + score, 0.2f, 4.8f);

        spriteBatch.end();

    }

    private void checkCollisions(){
        for(Drop drop: dropSpawner.getDrops()){
            if(player.getRectangle().overlaps(drop.getRectangle())){
                dropSound.play();
                score++;
                drop.kill();
            }
        }

        for(Bomb bomb: dropSpawner.getBombs()){
            if (player.getRectangle().overlaps(bomb.getRectangle())){
                bombSound.play();
                score = 0;
                bomb.kill();
            }
        }
    }
}
