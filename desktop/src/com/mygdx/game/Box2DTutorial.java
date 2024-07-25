package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.bullet.linearmath.int4;

import loader.GameAssetManager;
import minigames.Cantunia;
import minigames.Casa1028;
import minigames.PadreAlmeida;
import views.EndScreen;
import views.LoadingScreen;
import views.MainScreen;
import views.MenuScreen;
import views.PauseScreen;
import views.PreferencesScreen;

public class Box2DTutorial extends Game {
	private AppPreferences preferences;
	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private PauseScreen pauseScreen;
	public GameAssetManager assetManager;// = new GameAssetManager();
	public Cantunia cantunia;
	public Casa1028 casa1028;
	public PadreAlmeida padreAlmeida;
	
	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;
	public final static int PAUSE =4;
	public final static int CANTUNIA=5;
	public final static int CASA1028=6;
	public final static int PADREALMEIDA=7;

	@Override
	public void create() {
		assetManager = new GameAssetManager();
		loadingScreen = new LoadingScreen(this);
		pauseScreen = new PauseScreen(this);
		//preferences = new AppPreferences();
		setScreen(loadingScreen);
				
		assetManager.queueAddMusic();
		assetManager.manager.finishLoading();
		//playingSong = assetManager.manager.get("music/Dr._Wily_Castle.mp3");

		//playingSong.play();
	}

	public void changeScreen(int screen) {
		switch (screen) {
		case MENU:
			if (menuScreen == null)
				menuScreen = new MenuScreen(this);
			this.setScreen(menuScreen);
			break;
		case PREFERENCES:
			if (preferencesScreen == null)
				preferencesScreen = new PreferencesScreen(this);
			this.setScreen(preferencesScreen);
			break;
		case APPLICATION:
			if (mainScreen == null)
				mainScreen = new MainScreen(this);
			this.setScreen(mainScreen);
			break;
		case ENDGAME:
			if (endScreen == null)
				endScreen = new EndScreen(this);
			this.setScreen(endScreen);
			break;
		case PAUSE:
			if(pauseScreen == null)
				System.out.println("creando pause");
				pauseScreen = new PauseScreen(this);
			this.setScreen(pauseScreen);
			break;
		case CANTUNIA:
			if(cantunia == null)
				System.out.println("creando cantunia");
				cantunia = new Cantunia(this);
			this.setScreen(cantunia);
			break;
		case PADREALMEIDA:
			if(padreAlmeida == null)
				System.out.println("creando padreAlmeida");
				padreAlmeida = new PadreAlmeida(this);
			this.setScreen(padreAlmeida);
			break;
		case CASA1028:
			if(casa1028 == null)
				System.out.println("creando casa1028");
				casa1028 = new Casa1028(this);
			this.setScreen(casa1028);
			break;
		}
	}
	 // Method to resume the game
    public void resumeGame() {
    	System.out.println("resumir");
        setScreen(mainScreen); // Return to the game screen
    }
	public AppPreferences getPreferences() {
		return this.preferences;
	}
		
	public MainScreen getMainScreen() {
		return this.mainScreen;
	}
	
	@Override
	public void dispose() {
		//playingSong.dispose();
		//assetManager.manager.dispose();
	}
}
