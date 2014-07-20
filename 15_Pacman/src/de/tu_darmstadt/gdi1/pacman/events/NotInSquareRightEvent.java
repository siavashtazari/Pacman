package de.tu_darmstadt.gdi1.pacman.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.pacman.entities.PacmanEntity;
import eea.engine.event.Event;

public class NotInSquareRightEvent extends Event {

	private PacmanEntity pacman;

	public NotInSquareRightEvent(PacmanEntity pacman) {
		super("NotInSquareEvent");
		this.pacman = pacman;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		return pacman.getOrientation() == PacmanEntity.EAST && pacman.isOnTheMove();
	}

}
