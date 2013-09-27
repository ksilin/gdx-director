package info.silin.gdx.core;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.netthreads.libgdx.action.TimelineAction;
import com.netthreads.libgdx.director.AppInjector;
import com.netthreads.libgdx.director.Director;
import com.netthreads.libgdx.scene.Scene;

/**
 * Base transition class.
 *
 * This class forms basis of scene transition. Draws the incoming and outgoing
 * scenes. Ensures scene contents positions are noted on entry and reset on
 * exit. Implements transition "complete" handler to set incoming scene as main
 * scene.
 *
 */
public class AlphaTransitionScene extends Scene implements TweenCallback {
	private boolean complete;

	private Color inColor;
	private Color outColor;

	private Scene inScene;
	private Scene outScene;
	private Group inSceneRoot;
	private Group outSceneRoot;

	private int durationMillis;
	private TweenEquation easeEquation;

	private static Director director = AppInjector.getInjector().getInstance(
			Director.class);

	public AlphaTransitionScene(Scene inScene, Scene outScene,
			int durationMillis, TweenEquation easeEquation) {
		setInScene(inScene);
		inColor = inScene.getSpriteBatch().getColor();
		outColor = outScene.getSpriteBatch().getColor();
		setInSceneRoot(inScene.getRoot());
		setOutScene(outScene);
		setOutSceneRoot(outScene.getRoot());
		setDurationMillis(durationMillis);
		setEaseEquation(easeEquation);
	}

	@Override
	public void enter() {
		complete = false;

		inColor = inScene.getSpriteBatch().getColor();
		inColor = outScene.getSpriteBatch().getColor();

		Tween.registerAccessor(Scene.class, new StageColorAccessor());

		Timeline inTimeline = Timeline
				.createSequence()
				.beginSequence()
				.push(Tween.to(inScene, 0, 0).target(0).ease(getEaseEquation()))
				.push(Tween.to(inScene, 0, getDurationMillis()).target(0, 0)
						.ease(getEaseEquation())).end().start();

		// In Scene TimeLine Action.
		TimelineAction inTimelineAction = TimelineAction.$(inTimeline);
		getInSceneRoot().addAction(inTimelineAction);

		// Out Scene TimeLine.
		Timeline outTimeline = Timeline
				.createSequence()
				.beginSequence()
				.push(Tween.to(outScene, 0, 0).target(0)
						.ease(getEaseEquation()))
				.push(Tween.to(outScene, 0, getDurationMillis()).target(0f)
						.ease(getEaseEquation()))
				.setCallbackTriggers(TweenCallback.COMPLETE).setCallback(this)
				.end().start();

		// Out Scene TimeLine Action.
		TimelineAction outTimelineAction = TimelineAction.$(outTimeline);
		getOutSceneRoot().addAction(outTimelineAction);
	}

	@Override
	public void exit() {
		complete = true;

		inScene.getSpriteBatch().setColor(inColor);
		outScene.getSpriteBatch().setColor(outColor);
	}

	@Override
	public void draw() {
		if (!complete) {
			outScene.draw();
		}
		inScene.draw();
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		inSceneRoot.act(delta);
		outSceneRoot.act(delta);
	}

	@Override
	public void onEvent(int eventType, BaseTween<?> source) {
		switch (eventType) {
		case COMPLETE:
			director.setScene(inScene);
			break;
		default:
			break;
		}
	}

	public boolean isComplete() {
		return complete;
	}

	public Scene getInScene() {
		return inScene;
	}

	public void setInScene(Scene inScene) {
		this.inScene = inScene;
	}

	public Scene getOutScene() {
		return outScene;
	}

	public void setOutScene(Scene outScene) {
		this.outScene = outScene;
	}

	public Group getInSceneRoot() {
		return inSceneRoot;
	}

	public void setInSceneRoot(Group inSceneRoot) {
		this.inSceneRoot = inSceneRoot;
	}

	public Group getOutSceneRoot() {
		return outSceneRoot;
	}

	public void setOutSceneRoot(Group outSceneRoot) {
		this.outSceneRoot = outSceneRoot;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public int getDurationMillis() {
		return durationMillis;
	}

	public void setDurationMillis(int durationMillis) {
		this.durationMillis = durationMillis;
	}

	public TweenEquation getEaseEquation() {
		return easeEquation;
	}

	public void setEaseEquation(TweenEquation easeEquation) {
		this.easeEquation = easeEquation;
	}
}
