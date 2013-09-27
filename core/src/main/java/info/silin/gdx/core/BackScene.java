package info.silin.gdx.core;

import com.netthreads.libgdx.scene.Layer;
import com.netthreads.libgdx.scene.Scene;

public class BackScene extends Scene {

	private static BackScene INSTANCE;

	private Layer backLayer;

	public BackScene() {
		backLayer = new BackLayer(getWidth(), getHeight());
		addLayer(backLayer);
	}

	public static BackScene getInstance() {
		if (INSTANCE == null)
			INSTANCE = new BackScene();
		return INSTANCE;
	}
}
