package com.hugosergeant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private final static float GRAVITY = -9.81f;
    private final static int SPEED = 50;

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bound;
    private Animation animation;
    private Sound flapSound;
    private Sound dieSound;

    public Bird(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("merge_from_ofoct.png");
        flapSound = Gdx.audio.newSound(Gdx.files.internal("audio/flap.ogg"));
        dieSound = Gdx.audio.newSound(Gdx.files.internal("audio/die.ogg"));
        animation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bound = new Rectangle(x,y,texture.getWidth() / 3f, texture.getHeight());
    }

    public void update(float dt) {
        animation.update(dt);
        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(SPEED*dt,velocity.y, 0);
        if(position.y < 0) position.y = 0;
        bound.setPosition(position.x, position.y);
        velocity.scl(1/dt);
    }

    public void jump() {
        velocity.y = 250;
        flapSound.play(0.1f);
    }

    public void die() {
        dieSound.play(0.3f);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getCurrentTexture() {
        return animation.getFrame();
    }

    public Rectangle getBound() { return bound; }

    public void draw(SpriteBatch sb) { sb.draw(getCurrentTexture(), position.x, position.y); }

    public void dispose() {
        texture.dispose();
        flapSound.dispose();
        dieSound.dispose();
    }
}
