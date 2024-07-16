package views;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBarPart extends Actor {
	//https://www.gamedevelopment.blog/full-libgdx-game-tutorial-scene2d/
	public LoadingBarPart(AtlasRegion ar, Animation an) {
		super();
		image = ar;
		flameAnimation = an;
		this.setWidth(30);
		this.setHeight(25);
		this.setVisible(false);
	}
}
