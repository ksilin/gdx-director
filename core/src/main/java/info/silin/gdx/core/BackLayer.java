package info.silin.gdx.core;

import aurelienribon.tweenengine.equations.Quart;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.netthreads.libgdx.scene.Layer;

public class BackLayer extends Layer {

	private static final String UI_FILE = "data/uiskin32.json";

	private Table table;
	private Skin skin;

	public BackLayer(float width, float height) {
		setWidth(width);
		setHeight(height);
		table = new Table();
		table.setSize(width, height);

		Gdx.input.setCatchBackKey(true);
		loadTextures();
		buildElements();
	}

	private void loadTextures() {
		skin = new Skin(Gdx.files.internal(UI_FILE));
	}

	private void buildElements() {
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(createListener());

		table = new Table();
		table.row();
		addToTable(backButton);
		table.setFillParent(true);
		table.pack();
		addActor(table);
	}

	private void addToTable(Actor actor) {
		table.add(actor).expand().prefWidth(getWidth()).prefHeight(getHeight());
	}

	private ClickListener createListener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Events.INSTANCE.getBus().post(
						new ChangeSceneEvent(MenuScene.getInstance(),
								Quart.INOUT));
				;
			}
		};
	}
}
