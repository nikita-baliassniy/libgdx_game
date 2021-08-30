package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private TextureRegion region;

	private float x;
	private float y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("wall.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.5f, 0.67f, 0.63f, 1f);
		batch.begin();
		batch.draw(img, 0, 0, 1920, 1080);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
