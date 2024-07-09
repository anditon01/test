package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
	private Box2DTutorial parent;
	private Stage stg;
	
	public MenuScreen(Box2DTutorial box2dTutorial) {
		this.parent = box2dTutorial;
		
		stg= new Stage(new ScreenViewport());
		
		//stg.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		//stg.draw();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stg);
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		stg.addActor(table);
		
		Skin skin = new Skin(Gdx.files.internal("skin/glassy/glassy-ui.json"));
		
		TextButton newGame = new TextButton("New Game", skin);
		TextButton preferences = new TextButton("Preferences",skin);
		TextButton exit = new TextButton("Exit", skin);
		
		table.add(newGame).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(preferences);
		table.row();
		table.add(exit).fillX().uniformX();
		
		exit.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		newGame.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(Box2DTutorial.APPLICATION);
			}
		});
		
		preferences.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(Box2DTutorial.PREFERENCES);;		
			}
		});
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
		stg.dispose();
	}

}
