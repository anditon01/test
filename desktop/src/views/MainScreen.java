package views;

import java.util.random.RandomGenerator.StreamableGenerator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.B2dModel;
import com.mygdx.game.Box2DTutorial;

import controller.KeyboardController;
import listener.AudioManager;

public class MainScreen implements Screen {
	private Box2DTutorial parent;
	private B2dModel model;
	private OrthographicCamera camera;
	private KeyboardController controller;
	private Box2DDebugRenderer debugRenderer;
	private Texture DownTex;
	private TextureAtlas animAtlas;
	private Texture animaTexture;
	private Music gameMusic;
	private SpriteBatch sb;
	private boolean paused;
	private PauseScreen pausa;
	private Stage stage;
	private Animation<TextureRegion> walkAnimation;
    private float stateTime;
    
	public MainScreen(Box2DTutorial parent) {

		camera = new OrthographicCamera(32, 24);
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
		//playerTex = parent.assetManager.manager.get("link.png");
		create();
	}

	public void create() {
		//animAtlas = parent.assetManager.manager.get("atlas/linkwalk.atlas");
		DownTex = new Texture("linkwalk/walkD.png");
		//animaTexture = playerTex.getTexture();
		TextureRegion[][] tmp = TextureRegion.split(DownTex, 60, 78); // Suponiendo que cada sprite es de 64x64 píxeles
        TextureRegion[] walkFrames = new TextureRegion[10];
        System.arraycopy(tmp[0], 0, walkFrames, 0, 10);
        walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);
        stateTime = 0f;
	}
	
	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(controller);
		// Skin skin = new Skin(Gdx.files.internal("skin/metal/metal-ui.json"));
		// pausa = new PauseScreen(parent);

	}

	@Override
	public void render(float delta) {
		model.logicStep(delta);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (paused) {
			// Update game logic when not paused
			updateGame(delta);
			stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
			stage.draw();
		}
		stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

		sb.setProjectionMatrix(camera.combined);
		sb.begin();	
		sb.draw(currentFrame, model.player.getPosition().x - 1, model.player.getPosition().y  - 1,2,2);
		sb.end();
		
		camera.position.set(model.player.getPosition().x,model.player.getPosition().y,0);
		camera.update();
		debugRenderer.render(model.world, camera.combined);
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			paused=!paused;//pausa
			togglePause();
			paused=!paused;
		}
	}
	
	private void updateGame(float delta) {
        // Update your game world here
        // Example: update physics, handle input, update game state, etc.
    }
	
	private void togglePause() {
		if (paused) {
			// Pause game and show pause menu
			parent.changeScreen(Box2DTutorial.PAUSE);
		} else {
			// Resume game
			// Optionally add resume logic if needed
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height,true);
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
		//playerTex.dispose();
		sb.dispose();

	}

}
