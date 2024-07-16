package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import views.GameScreen;

public class RunnerGame extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new Runner(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
