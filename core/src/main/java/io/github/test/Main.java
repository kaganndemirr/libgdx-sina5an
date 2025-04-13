package io.github.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    private Sprite s1;

    private List<Sprite> spriteList;

    private float deltaTime;

    @Override
    public void create() {
        spriteList = new ArrayList<>();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        s1 = new Sprite(image);
        s1.setPosition(100, 100);
    }

    @Override
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        input();
        logic();
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    private void input(){
        float speed = 50;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            s1.translateX(-speed * deltaTime);
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            s1.translateX(speed * deltaTime);
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            s1.translateY(speed * deltaTime);
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            s1.translateY(-speed * deltaTime);
        }

        if (Gdx.input.isTouched()){
            // En başta oyun alanıyla kameranın gösterdiği aynı boyut
            // Ekranı büyüttüğünde aynı değil bunun için viewport kullanılıyor
            Sprite sprite = new Sprite(image);
            sprite.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            spriteList.add(sprite);
        }
    }

    private void draw(){
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        s1.draw(batch);
        for(Sprite sprite: spriteList){
            sprite.draw(batch);
        }
        batch.end();
    }

    private void logic(){

    }
}
