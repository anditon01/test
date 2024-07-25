package minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.B2dModel;
import com.mygdx.game.Box2DTutorial;

import controller.KeyboardController;
import listener.AudioManager;
import views.PauseScreen;

public class Casa1028 implements Screen{
	private Box2DTutorial parent;
	private B2dModel model;
	private OrthographicCamera camera;
	private KeyboardController controller;
	private Box2DDebugRenderer debugRenderer;
	// anim text walk
	private Texture DownTex;
	private Texture LeftTex;

	private TextureAtlas animAtlas;
	private Texture animaTexture;
	private Music gameMusic;
	private SpriteBatch sb;
	private boolean paused;
	private PauseScreen pausa;
	private Stage stage;
	private Animation<TextureRegion> walkAnimation;
	private float stateTime;
	private Label messageLabel;
	
	public Casa1028(Box2DTutorial parent) {
		camera = new OrthographicCamera(64, 48);
		controller = new KeyboardController();
		model = new B2dModel(controller, camera);
		debugRenderer = new Box2DDebugRenderer();// (true, true, true, true, true, true);
		this.parent = parent;
		paused = false;
		sb = new SpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		// tells our asset manger that we want to load the images set in loadImages
		// method
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		parent.assetManager.queueAddImages();
		parent.assetManager.queueAddMusic();
		parent.assetManager.queueAddSkin();
		// tells the asset manager to load the images and wait until finsihed loading.
		parent.assetManager.manager.finishLoading();

		// gets the images as a texture
		gameMusic = AudioManager.getInstance().playMusic("music/Dr._Wily_Castle.mp3", true);
		// gameMusic = parent.assetManager.manager.get("music/Dr._Wily_Castle.mp3");
		gameMusic.play();
		// playerTex = parent.assetManager.manager.get("link.png");
		create();
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
