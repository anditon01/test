package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.B2dModel;
import com.mygdx.game.Box2DTutorial;

import controller.KeyboardController;

public class MainScreen implements Screen {
	private Box2DTutorial parent;
	private B2dModel model;
	private OrthographicCamera camera;
	private KeyboardController controller;
	private Box2DDebugRenderer debugRenderer;
	private Texture playerTex;
	private Music gameMusic;
	SpriteBatch sb;

	public MainScreen(Box2DTutorial parent) {
		camera = new OrthographicCamera(32, 24);
		controller = new KeyboardController();
		model = new B2dModel(controller, camera);
		debugRenderer = new Box2DDebugRenderer();//(true, true, true, true, true, true);
		this.parent = parent;
		
		sb = new SpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		// tells our asset manger that we want to load the images set in loadImages
		// method
		parent.assetManager.queueAddImages();
		parent.assetManager.queueAddMusic();
		// tells the asset manager to load the images and wait until finsihed loading.
		parent.assetManager.manager.finishLoading();
		// gets the images as a texture
		gameMusic = parent.assetManager.manager.get("music/Dr._Wily_Castle.mp3");
		gameMusic.play();
		playerTex = parent.assetManager.manager.get("link.png");
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void render(float delta) {
		model.logicStep(delta);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sb.begin();
		sb.draw(playerTex, model.player.getPosition().x-1, model.player.getPosition().y-1, 2, 2);
		sb.end();
		debugRenderer.render(model.world, camera.combined);
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
