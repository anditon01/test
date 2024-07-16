package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
//https://www.gamedevelopment.blog/full-libgdx-game-tutorial-box2d-body-factory/
public class DesktopLauncher {
	public static void main (String[] arg) {
//		 Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//	      config.setTitle("Box2d");
//	      config.setWindowedMode(800, 480);
//	      config.useVsync(true);
//	      config.setForegroundFPS(60);
//	      new Lwjgl3Application(new Box2DTutorial(), config);
	      Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
			new Lwjgl3Application(new Box2DTutorial(), config);
			//new Lwjgl3Application(new RunnerGame(), config);
	}
}
