package info.silin.gdx.core;

import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Circ;
import aurelienribon.tweenengine.equations.Cubic;
import aurelienribon.tweenengine.equations.Elastic;
import aurelienribon.tweenengine.equations.Expo;
import aurelienribon.tweenengine.equations.Linear;
import aurelienribon.tweenengine.equations.Quad;
import aurelienribon.tweenengine.equations.Quart;
import aurelienribon.tweenengine.equations.Quint;
import aurelienribon.tweenengine.equations.Sine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.netthreads.libgdx.scene.Layer;

public class MenuLayer extends Layer {

	private static final String UI_FILE = "data/uiskin32.json";
	private static final String URL_LABEL_FONT = "default-font";

	private Table table;
	private Skin skin;
	private Label titleLabel;

	private Label durationLabel;
	private Slider durationSlider;
	private Group sliderGroup = new Group();

	float prefWidth;
	float prefHeight;

	public MenuLayer(float width, float height) {
		setWidth(width);
		setHeight(height);

		table = new Table();
		table.setSize(width, height);

		prefWidth = width * 0.3f;
		prefHeight = height / 8;

		Gdx.input.setCatchBackKey(true);
		loadTextures();
		buildElements();
	}

	private void loadTextures() {
		skin = new Skin(Gdx.files.internal(UI_FILE));
	}

	private void buildElements() {
		titleLabel = new Label(DirectorGame.APPLICATION_NAME, skin,
				URL_LABEL_FONT, Color.YELLOW);

		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(createListener(Back.INOUT));
		TextButton bounceButton = new TextButton("Bounce", skin);
		bounceButton.addListener(createListener(Bounce.INOUT));
		TextButton circButton = new TextButton("Circ", skin);
		circButton.addListener(createListener(Circ.INOUT));
		TextButton cubicButton = new TextButton("Cubic", skin);
		cubicButton.addListener(createListener(Cubic.INOUT));
		TextButton elasticButton = new TextButton("Elastic", skin);
		elasticButton.addListener(createListener(Elastic.INOUT));
		TextButton expoButton = new TextButton("Expo", skin);
		expoButton.addListener(createListener(Expo.INOUT));
		TextButton linearButton = new TextButton("Linear", skin);
		linearButton.addListener(createListener(Linear.INOUT));
		TextButton quadButton = new TextButton("Quad", skin);
		quadButton.addListener(createListener(Quad.INOUT));
		TextButton quartButton = new TextButton("Quart", skin);
		quartButton.addListener(createListener(Quart.INOUT));
		TextButton quintButton = new TextButton("Quint", skin);
		quintButton.addListener(createListener(Quint.INOUT));
		TextButton sineButton = new TextButton("Sine", skin);
		sineButton.addListener(createListener(Sine.INOUT));

		durationLabel = new Label("duration: " + 1000, skin);
		durationSlider = new Slider(100, 5000, 100, false, skin);
		durationSlider.setValue(1000);
		durationSlider.addListener(createDurationSliderListener());
		durationLabel.setY(12);
		sliderGroup.addActor(durationLabel);
		sliderGroup.addActor(durationSlider);

		table = new Table();
		table.row();
		table.add(titleLabel).center().expand();
		table.row();
		addToTable(backButton);
		addToTable(bounceButton);
		addToTable(circButton);
		table.row();
		addToTable(cubicButton);
		addToTable(elasticButton);
		addToTable(expoButton);
		table.row();
		addToTable(linearButton);
		addToTable(quadButton);
		addToTable(quartButton);
		table.row();
		addToTable(quintButton);
		addToTable(sineButton);
		addToTable(sliderGroup);
		table.setFillParent(true);
		table.pack();
		addActor(table);
	}

	private EventListener createDurationSliderListener() {
		return new EventListener() {
			@Override
			public boolean handle(Event event) {
				if (event instanceof ChangeEvent) {
					int value = (int) durationSlider.getValue();
					durationLabel.setText("duration: " + value + " ms");
					Events.INSTANCE.getBus().post(
							new DurationChangeEvent(value));
					return true;
				}
				return false;
			}
		};
	}

	private void addToTable(Actor actor) {
		table.add(actor).expand().prefWidth(prefWidth).prefHeight(prefHeight);
	}

	private ClickListener createListener(final TweenEquation eq) {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Events.INSTANCE.getBus().post(
						new ChangeSceneEvent(BackScene.getInstance(), eq));
				;
			}
		};
	}
}
