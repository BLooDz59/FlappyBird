package com.hugosergeant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScene extends Scene {
    private Texture background;
    private Texture splashText;

    public MenuScene(GameSceneManager gsm) {
        super(gsm);
        background = new Texture("background-day.png");
        splashText = new Texture("message.png");
        camera.setToOrtho(false, FlappyBird.WIDTH / 2f, FlappyBird.HEIGTH / 2f);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            gsm.set(new PlayScene(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(splashText, camera.position.x - splashText.getWidth() / 2f, camera.position.y - splashText.getHeight() / 2f);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        splashText.dispose();
    }
}
