package views;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.bullet.linearmath.int4;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBarPart extends Actor {
	//https://www.gamedevelopment.blog/full-libgdx-game-tutorial-scene2d/
	AtlasRegion image ;
	TextureRegion currentFrame;
	int stateTime;
	Animation<TextureRegion> flameAnimation;
	
	public LoadingBarPart(AtlasRegion ar, Animation<TextureRegion> an) {
		super();
		image = ar;
		flameAnimation = an;
		this.setWidth(30);
		this.setHeight(25);
		this.setVisible(false);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(image, getX(),getY(), 30, 30);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		batch.draw(currentFrame, getX()-5,getY(), 40, 40);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		stateTime += delta; // Accumulate elapsed animation time
	    currentFrame =   flameAnimation.getKeyFrame(stateTime, true);
	}

	

	
	
}
