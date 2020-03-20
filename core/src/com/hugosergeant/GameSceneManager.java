package com.hugosergeant;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameSceneManager {

    private Stack<Scene> scenes;

    public GameSceneManager() {
        scenes = new Stack<>();
    }

    public void push(Scene scene) {
        scenes.push(scene);
    }

    public void pop() {
        scenes.pop().dispose();
    }

    public void set(Scene scene) {
        pop();
        push(scene);
    }

    public void update(float dt) {
        scenes.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        scenes.peek().render(sb);
    }
}
