package info.silin.gdx.core;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;
import com.netthreads.libgdx.scene.Scene;

public class StageColorAccessor implements TweenAccessor<Scene> {

	@Override
	public int getValues(Scene target, int tweenType, float[] returnValues) {
		returnValues[0] = target.getSpriteBatch().getColor().a;
		return 1;
	}

	@Override
	public void setValues(Scene target, int tweenType, float[] newValues) {
		Color color = target.getSpriteBatch().getColor();
		target.getSpriteBatch().setColor(
				new Color(color.r, color.g, color.b, newValues[0]));
	}

}
