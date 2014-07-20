package de.tu_darmstadt.gdi1.pacman.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.pacman.entities.PacmanEntity;
import eea.engine.event.Event;

public class NotInSquareDownEvent extends Event {

	private PacmanEntity pacman;

	public NotInSquareDownEvent(PacmanEntity pacman) {
		super("NotInSquareEvent");
		this.pacman = pacman;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		return pacman.getOrientation() == PacmanEntity.SOUTH && pacman.isOnTheMove();
	}

}
