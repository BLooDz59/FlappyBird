package com.hugosergeant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class PlayScene extends Scene {

    private final static int TUBE_SPACING = 150;
    private final static int TUBE_COUNT = 4;
    private final static int GROUND_OFFSET = -80;

    private Bird bird;
    private Texture background;
    private Array<Tube> tubes;
    private Ground ground;
    private Score score;

    public PlayScene(GameSceneManager gsm) {
        super(gsm);
        bird = new Bird(50,200);
        background = new Texture("background-day.png");
        camera.setToOrtho(false, FlappyBird.WIDTH / 2f, FlappyBird.HEIGTH / 2f);
        score = new Score(bird.getPosition().x,FlappyBird.HEIGTH / 2f - camera.viewportHeight / 4f);
        ground = new Ground(camera.position.x - camera.viewportWidth);
        tubes = new Array<>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (camera.position.x - camera.viewportWidth / 2 > ground.getPosition1().x + ground.getTexture().getWidth()) {
            ground.updateFirstPartPosition();
        }
        if (camera.position.x - camera.viewportWidth / 2 > ground.getPosition2().x + ground.getTexture().getWidth()) {
            ground.updateSecondPartPosition();
        }
        bird.update(dt);
        score.update(dt);
        camera.position.x = bird.getPosition().x;
        if(bird.getPosition().y <= ground.getTexture().getHeight() + GROUND_OFFSET) {
            gsm.set(new PlayScene(gsm));
        }
        for(Tube tube : tubes) {
            if(camera.position.x - camera.viewportWidth / 2 > tube.getTopTubePosition().x + Tube.WIDTH) {
                tube.reposition(tube.getTopTubePosition().x + (TUBE_SPACING + Tube.WIDTH) * TUBE_COUNT);
            }
            if(tube.hasCollideWith(bird.getBound())) {
                gsm.set(new PlayScene(gsm));
                break;
            }
            if((int) (bird.getPosition().x + bird.getCurrentTexture().getRegionWidth() / 2f) == (int) (tube.getTopTubePosition().x + Tube.WIDTH / 2f) && !tube.isLockedCountScore()) {
                tube.setLockedCountScore(true);
                score.increase();
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,camera.position.x - camera.viewportWidth / 2,0);
        bird.draw(sb);
        for(Tube tube : tubes) {
            tube.draw(sb);
        }
        ground.draw(sb);
        score.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        background.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        score.dispose();
    }
}
