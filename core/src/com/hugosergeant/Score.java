package com.hugosergeant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Score {

    private static final int SPACE_OFFSET = 2;
    private Array<Texture> textures;
    private Vector2 position;
    private int score;
    private Sound increaseScoreSound;

    private int hundreds, tens, units;

    public Score(float x, float y) {
        score = 0;
        position = new Vector2(x,y);
        textures = new Array<>();
        textures.add(new Texture("0.png"));
        textures.add(new Texture("1.png"));
        textures.add(new Texture("2.png"));
        textures.add(new Texture("3.png"));
        textures.add(new Texture("4.png"));
        textures.add(new Texture("5.png"));
        textures.add(new Texture("6.png"));
        textures.add(new Texture("7.png"));
        textures.add(new Texture("8.png"));
        textures.add(new Texture("9.png"));
        increaseScoreSound = Gdx.audio.newSound(Gdx.files.internal("audio/point.ogg"));
    }

    public void update(float dt) {
        position.add(50*dt,0);
        hundreds = score / 100;
        tens = (score % 100) / 10;
        units = (score % 100) % 10;
    }

    public void draw(SpriteBatch sb) {
        if(score < 10) {
            sb.draw(getNumberTexture(units), position.x,position.y);
        }
        else if (score >= 10 && score < 100) {
            sb.draw(getNumberTexture(tens), position.x - getNumberTexture(tens).getWidth() - SPACE_OFFSET,position.y);
            sb.draw(getNumberTexture(units), position.x,position.y);
        }
        else if (score <= 999) {
            sb.draw(getNumberTexture(hundreds), position.x - getNumberTexture(hundreds).getWidth() - getNumberTexture(tens).getWidth() - SPACE_OFFSET,position.y);
            sb.draw(getNumberTexture(tens), position.x - getNumberTexture(tens).getWidth() - SPACE_OFFSET,position.y);
            sb.draw(getNumberTexture(units), position.x,position.y);
        }
    }

    private Texture getNumberTexture(int number) {
        return textures.get(number);
    }

    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
        increaseScoreSound.dispose();
    }

    public void increase() {
        score++;
        increaseScoreSound.play(0.2f);
    }
}
