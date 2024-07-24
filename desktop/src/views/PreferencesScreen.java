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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.AppPreferences;
import com.mygdx.game.Box2DTutorial;
import com.mygdx.game.MyGdxGame;

import listener.AudioManager;

public class PreferencesScreen implements Screen {
	private Box2DTutorial parent;
	private Stage stg;
	private MyGdxGame game;
	private Label titleLabel;
	private Label volumeMusicLabel;
	private Label resolutionLabel;
	private Label musicOnOffLabel;
	private Label soundOnOffLabel;
	private MainScreen mainScreen;
	private SelectBox<String> resolutionSelectBox;
	
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

		Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu/pixthulhu-ui.json"));
		resolutionSelectBox = new SelectBox<>(skin);
        resolutionSelectBox.setItems("1280x720", "1920x1080", "800x600");
        
        String resolution = Gdx.graphics.getWidth()+"x"+Gdx.graphics.getHeight();
        System.out.println(resolution);
        resolutionSelectBox.setSelected(resolution);
        resolutionSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selected = resolutionSelectBox.getSelected();
                String[] dimensions = selected.split("x");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                //parent.resize(width, height);
                Gdx.graphics.setWindowedMode(width, height);
            }
        });
        
		final Slider volumeMusicSlider = new Slider(0f, 2f, 0.1f, false, skin);
		volumeMusicSlider.setValue(AppPreferences.getInstance().getMusicVolume());
	//	volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());
		volumeMusicSlider.addListener(event -> {
			if (volumeMusicSlider.isDragging()) {
				float volume =volumeMusicSlider.getValue();
				AudioManager.getInstance().setMusicVolume(volume);
				//parent.getPreferences().setMusicVolume(volume);
			}
			return true;
		});
		
		final Slider volumeSoundSlider = new Slider(0f, 1f, 0.1f, false, skin);
		volumeSoundSlider.setValue(AppPreferences.getInstance().getSoundVolume());
		//volumeSoundSlider.setValue(parent.getPreferences().getSoundVolume());
		volumeSoundSlider.addListener(event ->{
            if (volumeSoundSlider.isDragging() || volumeSoundSlider.isTouchable()) {
                float volume = volumeSoundSlider.getValue();
                AppPreferences.getInstance().setSoundVolume(volume);
                
               // AudioManager.getInstance().setMusicVolume(volume);
            }
            return true;				
		});
		
		final TextButton backButton = new TextButton("Back", skin,"default");
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if ( parent.getMainScreen()!=null) {
					parent.changeScreen(Box2DTutorial.PAUSE);
				}else {
					parent.changeScreen(Box2DTutorial.MENU);					
				}
				
			}
		});
		titleLabel = new Label("Preferences", skin);
		volumeMusicLabel = new Label("Music Volume",skin);
		resolutionLabel = new Label("Resolution", skin);
		musicOnOffLabel = new Label("Sound Volume",skin);
		soundOnOffLabel = new Label("Sound Effect",skin);
		
		table.add(titleLabel).colspan(2);
		table.row().pad(10,0,0,10);
		table.add(volumeMusicLabel);
		table.add(volumeMusicSlider).fill();
		//table.add(volumeSoundLabel);
		//table.add(musiCheckBox);
		table.row().pad(10,0,0,10);
		table.add(musicOnOffLabel);
		table.add(volumeSoundSlider).fill();
		table.row();
		table.add(resolutionLabel);
		table.add(resolutionSelectBox);
		//table.add(soundEffectsCheckBox);
		table.row().pad(10,0,0,10);
		//table.add(saveButton).colspan(2);
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
		stg.getViewport().update(width, height,true);
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
