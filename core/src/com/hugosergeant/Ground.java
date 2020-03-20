package com.hugosergeant;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ground {
    private final static int GROUND_OFFSET = -80;

    private Vector2 position1, position2;
    private Texture texture;

    public Ground(float x) {
        texture = new Texture("base.png");
        position1 = new Vector2(x, GROUND_OFFSET);
        position2 = new Vector2(position1.x + texture.getWidth(), GROUND_OFFSET);
    }

    public void updateFirstPartPosition() {
        position1.add(texture.getWidth()*2,0);
    }

    public void updateSecondPartPosition() {
        position2.add(texture.getWidth()*2,0);
    }

    public void draw(SpriteBatch sb) {
        sb.draw(texture, position1.x, position1.y);
        sb.draw(texture, position2.x, position2.y);
    }

    public void dispose() { texture.dispose(); }

    public Vector2 getPosition1() {
        return position1;
    }

    public Vector2 getPosition2() {
        return position2;
    }

    public Texture getTexture() {
        return texture;
    }
}
