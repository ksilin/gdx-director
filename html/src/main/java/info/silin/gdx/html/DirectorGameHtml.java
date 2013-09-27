package info.silin.gdx.html;

import info.silin.gdx.core.DirectorGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class DirectorGameHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new DirectorGame();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
