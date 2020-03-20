package com.hugosergeant;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int WIDTH = 52;

    private static final int VARIATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;

    private Texture topTexture, bottomTexture;
    private Vector2 topTubePosition, bottomTubePosition;
    private Rectangle topBound, bottomBound;
    private Random rand;
    private boolean lockedCountScore;

    public Tube(int x) {
        topTexture = new Texture("pipe-green-top.png");
        bottomTexture =  new Texture("pipe-green-bottom.png");
        rand = new Random();

        topTubePosition = new Vector2(x, rand.nextInt(VARIATION) + LOWEST_OPENING);
        bottomTubePosition = new Vector2(x, topTubePosition.y - TUBE_GAP - bottomTexture.getHeight());

        topBound = new Rectangle(topTubePosition.x, topTubePosition.y, topTexture.getWidth(), topTexture.getHeight());
        bottomBound = new Rectangle(bottomTubePosition.x, bottomTubePosition.y, bottomTexture.getWidth(), bottomTexture.getHeight());
        lockedCountScore = false;
    }

    public Vector2 getTopTubePosition() {
        return topTubePosition;
    }

    public void reposition(float x) {
        lockedCountScore = false;
        topTubePosition.set(x, rand.nextInt(VARIATION) + LOWEST_OPENING);
        bottomTubePosition.set(x, topTubePosition.y - TUBE_GAP - bottomTexture.getHeight());
        topBound.setPosition(topTubePosition.x, topTubePosition.y);
        bottomBound.setPosition(bottomTubePosition.x, bottomTubePosition.y);
    }

    public boolean hasCollideWith(Rectangle r) { return r.overlaps(bottomBound) || r.overlaps(topBound); }

    public void draw(SpriteBatch sb) {
        sb.draw(topTexture, topTubePosition.x, topTubePosition.y);
        sb.draw(bottomTexture, bottomTubePosition.x, bottomTubePosition.y);
    }

    public void dispose() {
        bottomTexture.dispose();
        topTexture.dispose();
    }

    public boolean isLockedCountScore() {
        return lockedCountScore;
    }

    public void setLockedCountScore(boolean lockedCountScore) {
        this.lockedCountScore = lockedCountScore;
    }
}
