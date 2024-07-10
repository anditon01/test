package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Box2DTutorial;

public class PreferencesScreen implements Screen {
	private Box2DTutorial parent;
	private Stage stg;
	private Label titleLabel;
	private Label volumeMusicLabel;
	private Label volumeSoundLabel;
	private Label musicOnOffLabel;
	private Label soundOnOffLabel;

	public PreferencesScreen(Box2DTutorial parent) {
		this.parent = parent;
		stg= new Stage(new ScreenViewport());
	}

	@Override
	public void show() {
		stg.clear();
		
		Gdx.input.setInputProcessor(stg);
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		stg.addActor(table);

		Skin skin = new Skin(Gdx.files.internal("skin/glassy/glassy-ui.json"));
		
		final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
		volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());
		volumeMusicSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
				return false;
			}
		});
		
		final Slider volumeSoundSlider = new Slider(0f, 1f, 0.1f, false, skin);
		volumeSoundSlider.setValue(parent.getPreferences().getSoundVolume());
		volumeSoundSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				parent.getPreferences().setSoundVolume(volumeSoundSlider.getValue());
				return false;
			}
		});
//		final CheckBox musiCheckBox = new CheckBox(null, skin);
//		//musiCheckBox.setChecked(parent.get);
//		musiCheckBox.addListener(new EventListener() {
//			
//			@Override
//			public boolean handle(Event event) {
//				boolean enabled = musiCheckBox.isChecked();
//				return false;
//			}
//		});
//		
//		final CheckBox soundEffectsCheckBox = new CheckBox(null, skin);
//		//musiCheckBox.setChecked(parent.get);
//		musiCheckBox.addListener(new EventListener() {
//			
//			@Override
//			public boolean handle(Event event) {
//				boolean enabled = soundEffectsCheckBox.isChecked();
//				return false;
//			}
//		});
		final TextButton saveButton = new TextButton("Save", skin,"small");
		saveButton.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
				//System.out.println(parent.getPreferences().getMusicVolume());
				System.out.println(parent.getPreferences().getSoundVolume());
				parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
				return false;
			}
		});
		
		final TextButton backButton = new TextButton("Back", skin,"small");
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(Box2DTutorial.MENU);
			}
		});
		titleLabel = new Label("Preferences", skin);
		volumeMusicLabel = new Label("Music Volume",skin);
		volumeSoundLabel = new Label("Music", skin);
		musicOnOffLabel = new Label("Sound Volume",skin);
		soundOnOffLabel = new Label("Sound Effect",skin);
		
		table.add(titleLabel).colspan(2);
		table.row().pad(10,0,0,10);
		table.add(volumeMusicLabel);
		table.add(volumeMusicSlider);
		//table.add(volumeSoundLabel);
		//table.add(musiCheckBox);
		table.row().pad(10,0,0,10);
		table.add(musicOnOffLabel);
		table.add(volumeSoundSlider).left();
		//table.row();
		//table.add(soundOnOffLabel);
		//table.add(soundEffectsCheckBox);
		table.row().pad(10,0,0,10);
		table.add(saveButton).colspan(2);
		table.row().pad(10,0,0,10);
		table.add(backButton);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// tell our stage to do actions and draw itself
		stg.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stg.draw();
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
