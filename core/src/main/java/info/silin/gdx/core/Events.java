package info.silin.gdx.core;

import com.badlogic.gdx.Gdx;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public enum Events {

	INSTANCE;

	private final EventBus bus = new EventBus("app");

	public EventBus getBus() {
		return bus;
	}

	@Subscribe
	public void listen(DeadEvent event) {
		Gdx.app.log("Events", "DeadEvent received");
	}
}
