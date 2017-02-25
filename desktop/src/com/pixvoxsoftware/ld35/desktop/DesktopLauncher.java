package com.pixvoxsoftware.ld35.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.pixvoxsoftware.ld35.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                config.setWindowedMode(1280, 720);
		config.setTitle("The Witch & The Witcher");
		new Lwjgl3Application(new Game(), config);
	}
}
