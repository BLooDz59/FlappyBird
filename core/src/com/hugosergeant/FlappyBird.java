package com.hugosergeant;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH = 400;
	public static final int HEIGTH = 600;

	public static final String TITLE = "Flappy Bird";

	private GameSceneManager gsm;
	private SpriteBatch batch;

	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameSceneManager();
		gsm.push(new MenuScene(gsm));

		music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.05f);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
