package views;

import org.lwjgl.system.linux.X11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.AppPreferences;
import com.mygdx.game.B2dModel;
import com.mygdx.game.Box2DTutorial;

import database.DatabaseManager;
import listener.AudioManager;

public class PauseScreen implements Screen {
	private Box2DTutorial parent;
	private Stage stage;
	private Table table;
	private Skin skin;
	private DatabaseManager dbManager;
	private B2dModel player;
	 private int currentLevel;
	
	public PauseScreen(Box2DTutorial box2dTutorial) {
		this.parent = box2dTutorial;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/pixthulhu/pixthulhu-ui.json"));
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
	}

	@Override
	public void show() {
		table.clear();

		// Create buttons
		TextButton resumeButton = new TextButton("Resume", skin);
		TextButton optionsButton = new TextButton("Options", skin);
		TextButton quitButton = new TextButton("Quit", skin);
		TextButton saveButton = new TextButton("Save",skin);
		
		// Add listeners to the buttons
		resumeButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.resumeGame(); // Implement this method in Box2DTutorial to resume the game
			}
		});

		optionsButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(Box2DTutorial.PREFERENCES); // Navigate to options screen
			}
		});

		quitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit(); // Exit the application
			}
		});
		
		saveButton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				dbManager.saveGame(player.player.getPosition().x, player.player.getPosition().y, currentLevel);
			}
		});

		// Add buttons to table
		table.add(resumeButton).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(optionsButton).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(saveButton).fill();
		table.row();
		table.add(quitButton).fillX().uniformX();

	}
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
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
		stage.dispose();
        skin.dispose();
	}
	// Method to show or hide the pause menu
    public void togglePauseMenu() {
    	System.out.println("toggle");
        if (stage.getActors().size == 0) {
            stage.addActor(table);
        } else {
            table.clear();
            stage.getActors().removeValue(table, true);
        }
    }
    
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
        	System.out.println("pause");
            togglePauseMenu();
        }
    }
}
