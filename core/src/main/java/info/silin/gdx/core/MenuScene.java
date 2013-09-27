package info.silin.gdx.core;

import com.netthreads.libgdx.scene.Layer;
import com.netthreads.libgdx.scene.Scene;

public class MenuScene extends Scene {

	private static MenuScene INSTANCE;

	private Layer menuLayer;

	public MenuScene() {
		menuLayer = new MenuLayer(getWidth(), getHeight());
		addLayer(menuLayer);
	}

	public static MenuScene getInstance() {
		if (INSTANCE == null)
			INSTANCE = new MenuScene();
		return INSTANCE;
	}
}
