package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Box2DTutorial;

public class TitleScreen implements Screen {
	private Box2DTutorial parent;
	private Texture titleTexture;
	private BitmapFont font;
	private Rectangle startButtonBounds;
	private Rectangle exitButtonBounds;

	public TitleScreen(final Box2DTutorial game) {
		this.parent = game;
		titleTexture = new Texture("title.png"); // Reemplaza con tu imagen de título
		font = new BitmapFont();
		startButtonBounds = new Rectangle(300, 200, 200, 50);
		exitButtonBounds = new Rectangle(300, 100, 200, 50);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
