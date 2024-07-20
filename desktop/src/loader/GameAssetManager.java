package loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;

public class GameAssetManager {
	public final AssetManager manager = new AssetManager();

	public final String playerImage = "link.png";
	public final String enemyImage = "drop.png";
	public final String playingSong = "music/Dr._Wily_Castle.mp3";
	public final String titleTheme = "music/title_theme.mp3";
	// Skin
	public final String skin = "skin/glassy/glassy-ui.json";
	public final String skinMetal = "skin/metal/metal-ui.json";
	// Textures
	public final String title = "title.png";
	public final String anim = "linksprite.png";
	public final String animwalk = "walklink.png";
	//Atlas
	public final String walkAtlas = "atlas/linkwalk.atlas";
	public final String gameImages = "atlas/game.atlas";
	public final String loadingImages = "atlas/linkanim.atlas";

		
	// a small set of images used by the loading screen
	public void queueAddLoadingImages(){
		manager.load(loadingImages, TextureAtlas.class);
		manager.load(animwalk,Texture.class);
	}
	public void queueAddSkin(){
		SkinParameter params = new SkinParameter("skin/glassy/glassy-ui.atlas");
		SkinParameter params1 = new SkinParameter("skin/metal/metal-ui.atlas") ;
		manager.load(skinMetal, Skin.class, params1);
		manager.load(skin, Skin.class, params);
	}
	public void queueAddImages() {
		manager.load(walkAtlas,TextureAtlas.class);
		manager.load(gameImages, TextureAtlas.class);
		manager.load(playerImage, Texture.class);
		manager.load(enemyImage, Texture.class);
	}
	public void queueAddMusic(){
		manager.load(playingSong, Music.class);
		manager.load(titleTheme, Music.class);
	}
	public void queueAddFonts() {
		// TODO Auto-generated method stub
		
	}
	public void queueAddParticleEffects() {
		// TODO Auto-generated method stub
		
	}
	public void queueAddSounds() {
		// TODO Auto-generated method stub
		
	}
}
