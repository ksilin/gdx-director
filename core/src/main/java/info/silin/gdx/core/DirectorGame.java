package info.silin.gdx.core;

import com.badlogic.gdx.ApplicationListener;
import com.google.common.eventbus.Subscribe;
import com.netthreads.libgdx.director.AppInjector;
import com.netthreads.libgdx.director.Director;
import com.netthreads.libgdx.scene.transition.MoveInLTransitionScene;
import com.netthreads.libgdx.scene.transition.TransitionScene;
import com.netthreads.libgdx.sound.SoundCache;

public class DirectorGame implements ApplicationListener {

	public static final int DEFAULT_WIDTH = 320;
	public static final int DEFAULT_HEIGHT = 480;
	public static final CharSequence APPLICATION_NAME = "Directing";

	private int transitionDuration = 1000;

	private Director director;
	private SoundCache soundCache;

	@Override
	public void create() {
		director = AppInjector.getInjector().getInstance(Director.class);
		director.setWidth(DEFAULT_WIDTH);
		director.setHeight(DEFAULT_HEIGHT);

		soundCache = AppInjector.getInjector().getInstance(SoundCache.class);
		soundCache.load(AppSoundDefinitions.SOUNDS);

		Events.INSTANCE.getBus().register(this);

		director.setScene(MenuScene.getInstance());
	}

	@Subscribe
	public void onEvent(DurationChangeEvent event) {
		transitionDuration = event.getValue();
	}

	@Override
	public void resize(int width, int height) {
		director.recalcScaleFactors(width, height);
	}

	@Override
	public void render() {
		director.update();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		// textureCache.dispose();
		soundCache.dispose();
		director.dispose();
	}

	@Subscribe
	public void changeScene(ChangeSceneEvent event) {

		if (event.getEaseEquation() == null) {
			director.setScene(event.getScene());
			return;
		}
		AlphaTransitionScene transitionScene = new AlphaTransitionScene(
				event.getScene(), director.getScene(), transitionDuration,
				event.getEaseEquation());
		director.setScene(transitionScene);
	}
}
