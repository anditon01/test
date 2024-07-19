package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AppPreferences;

import listener.AudioManager;

public class PauseScreen extends Group{
	public PauseScreen() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy/glassy-ui.json"));
        // Crear el slider de volumen
        final Slider volumeSlider = new Slider(0, 1, 0.01f, false, skin);
        volumeSlider.setValue(AppPreferences.getInstance().getMusicVolume());
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue();
                AudioManager.getInstance().setMusicVolume(volume);
                AppPreferences.getInstance().setMusicVolume(volume);
            }
        });


        // Crear el botón de reanudar el juego
        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
            }
        });

        table.add(new Label("Music Volume", skin)).padBottom(10);
        table.row().pad(10,0,0,10);;
        table.add(volumeSlider);
        table.row().pad(10,0,0,10);
        table.row().pad(10,0,0,10);
        table.add(resumeButton);
    }


}
