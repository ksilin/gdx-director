package info.silin.gdx.core;

import aurelienribon.tweenengine.TweenEquation;

import com.netthreads.libgdx.scene.Scene;

public class ChangeSceneEvent {

	private Scene scene;
	private TweenEquation easeEquation;

	public ChangeSceneEvent(Scene newScene, TweenEquation easeEquation) {
		this.scene = newScene;
		this.easeEquation = easeEquation;
	}

	public Scene getScene() {
		return scene;
	}

	public TweenEquation getEaseEquation() {
		return easeEquation;
	}

}
