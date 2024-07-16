package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.physics.bullet.linearmath.int4;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Box2DTutorial;

public class LoadingScreen implements Screen {
	private Box2DTutorial parent;
	private TextureAtlas atlas;
	private AtlasRegion link;
	private AtlasRegion title;
	private SpriteBatch sb;
	private int currentLoadingStage;
	private int stateTime;
	private Animation linkAnimation;
	
	public final int IMAGE = 0; // loading images
	public final int FONT = 1; // loading fonts
	public final int PARTY = 2; // loading particle effects
	public final int SOUND = 3; // loading sounds
	public final int MUSIC = 4;

	public float countDown = 5f;

	public LoadingScreen(Box2DTutorial bdd) {
		parent = bdd;
		currentLoadingStage = 0;
		sb = new SpriteBatch();
		sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void show() {
		parent.assetManager.queueAddLoadingImages();
		parent.assetManager.queueAddImages();
		parent.assetManager.manager.finishLoading();

		atlas = parent.assetManager.manager.get("atlas/game.atlas");
		link = atlas.findRegion("link");
		title = atlas.findRegion("title");
		
		linkAnimation = new Animation(1f, atlas.findRegion("linksprites"), PlayMode.LOOP);

		parent.assetManager.queueAddImages();
		System.out.println("Loading images... ");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += delta; // Accumulate elapsed animation time // new
		// Get current frame of animation for the current stateTime
		TextureRegion currentFrame =  (TextureRegion) linkAnimation.getKeyFrame(stateTime, true); // new

		sb.begin();
		drawLoadingBar(currentLoadingStage*2, currentFrame);
		sb.draw(title, 135, 250);
		sb.end();

		if (parent.assetManager.manager.update()) {
			currentLoadingStage += 1;
			switch (currentLoadingStage) {
			case FONT:
				System.out.println("Loading fonts....");
				parent.assetManager.queueAddFonts(); // first load done, now start fonts
				break;
			case PARTY:
				System.out.println("Loading Particle Effects....");
				parent.assetManager.queueAddParticleEffects(); // fonts are done now do party effects
				break;
			case SOUND:
				System.out.println("Loading Sounds....");
				parent.assetManager.queueAddSounds();
				break;
			case MUSIC:
				System.out.println("Loading fonts....");
				parent.assetManager.queueAddMusic();
				break;
			case 5:
				System.out.println("Finished"); // all done
				break;
			}
			if (currentLoadingStage > 5) {
				countDown -= delta; // timer to stay on loading screen for short preiod once done loading
				currentLoadingStage = 5; // cap loading stage to 5 as will use later to display progress bar anbd more
											// than 5 would go off the screen
				if (countDown < 0) { // countdown is complete
					parent.changeScreen(Box2DTutorial.MENU); /// go to menu screen
				}
			}
		}

		parent.changeScreen(Box2DTutorial.MENU);
	}

	private void drawLoadingBar(int stage, TextureRegion currentFrame) {
		for (int i = 0; i < stage; i++) {
			sb.draw(currentFrame, 50 + (i * 50), 150, 50, 50);
			sb.draw(link, 35 + (i * 50), 140, 80, 80);
		}
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
