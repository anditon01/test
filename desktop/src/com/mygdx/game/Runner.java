package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import views.ParallaxView;

public class Runner extends Game implements Screen {
	final RunnerGame game;

    Texture playerTexture;
    Vector2 playerPosition;
    Vector2 playerVelocity;
    boolean isJumping;

    Texture obstacleTexture;
    Array<Rectangle> obstacles;
    long lastObstacleTime;
    ParallaxView parallaxView;
    
    public Runner(final RunnerGame game) {
        this.game = game;

        playerTexture = new Texture(Gdx.files.internal("bucket.png"));
        playerPosition = new Vector2(50, 100);
        playerVelocity = new Vector2(0, 0);
        isJumping = false;

        obstacleTexture = new Texture(Gdx.files.internal("drop.png"));
        obstacles = new Array<>();
        spawnObstacle();
        
        Texture[] layers = new Texture[]{
                new Texture(Gdx.files.internal("fondo_cielo.png")),
                new Texture(Gdx.files.internal("fondo_montanias.png")),
                new Texture(Gdx.files.internal("Fondo_piso.png"))
            };

            float[] parallaxRatios = new float[]{0.2f, 0.5f, 1.0f};
            Vector2 speed = new Vector2(200, 0);
            parallaxView = new ParallaxView(layers, parallaxRatios, speed,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void spawnObstacle() {
        Rectangle obstacle = new Rectangle();
        obstacle.x = 800;
        obstacle.y = 100;
        obstacle.width = 10;
        obstacle.height = 10;
        obstacles.add(obstacle);
        lastObstacleTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          
        parallaxView.update(delta);
        
        game.batch.begin();
        parallaxView.render(game.batch);
        game.batch.draw(playerTexture, playerPosition.x, playerPosition.y);
        for (Rectangle obstacle : obstacles) {
            game.batch.draw(obstacleTexture, obstacle.x, obstacle.y);
        }
        game.batch.end();

        updatePlayer(delta);
        updateObstacles(delta);
    }

    private void updatePlayer(float delta) {
        if (Gdx.input.justTouched() && !isJumping) {
            playerVelocity.y = 500;
            isJumping = true;
        }

        playerVelocity.y -= 1000 * delta; // Gravity
        playerPosition.mulAdd(playerVelocity, delta);

        if (playerPosition.y <= 100) {
            playerPosition.y = 100;
            playerVelocity.y = 0;
            isJumping = false;
        }
    }

    private void updateObstacles(float delta) {
        if (TimeUtils.nanoTime() - lastObstacleTime > 1000000000) {
            spawnObstacle();
        }

        for (int i = 0; i < obstacles.size; i++) {
            Rectangle obstacle = obstacles.get(i);
            obstacle.x -= 200 * delta;

            if (obstacle.x + obstacle.width < 0) {
                obstacles.removeIndex(i);
                i--;
            }

            if (obstacle.overlaps(new Rectangle(playerPosition.x, playerPosition.y, playerTexture.getWidth(), playerTexture.getHeight()))) {
                // Game Over
                //game.setScreen(new GameOverScreen(game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    	 parallaxView = new ParallaxView(
    	            new Texture[]{
    	                new Texture(Gdx.files.internal("fondo_cielo.png")),
    	                new Texture(Gdx.files.internal("fondo_montanias.png")),
    	                new Texture(Gdx.files.internal("Fondo_piso.png"))
    	            },
    	            new float[]{0.2f, 0.5f, 1.0f},
    	            new Vector2(200, 0),
    	            width,
    	            height
    	        );
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        playerTexture.dispose();
        obstacleTexture.dispose();
        parallaxView.dispose();
    }

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
}
