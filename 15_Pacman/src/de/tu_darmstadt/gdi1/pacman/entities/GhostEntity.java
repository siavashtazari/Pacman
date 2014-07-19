package de.tu_darmstadt.gdi1.pacman.entities;

import java.awt.Point;
import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.pacman.view.GameplayState;
import eea.engine.entity.Entity;

public class GhostEntity extends Entity {

	private int orientation;
	private final static int NORTH = 0;
	private final static int EAST = 1;
	private final static int SOUTH = 2;
	private final static int WEST = 3;
	private int speed;
	private float distancePerTick;

	public GhostEntity(String entityID) {
		super(entityID);
		this.orientation = NORTH;
		this.speed = 2;
		this.distancePerTick = GameplayState.SQUARE_SIZE * this.speed / 1000.0f;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		if (isAtSquareCenter(delta)) {
			changeOrientation();
		}
		move(delta);
	}

	private boolean isAtSquareCenter(int delta) {
		float x = getPosition().getX();
		float y = getPosition().getY();
		// with refresh rate of 60 Hz (delta of 17 ms), movement per update is
		// about 0.6*speed pixels
		float distanceXToCrossing = (x - GameplayState.STARTING_POINT)
				% GameplayState.SQUARE_SIZE;
		float distanceYToCrossing = (y - GameplayState.STARTING_POINT)
				% GameplayState.SQUARE_SIZE;
		return (distanceXToCrossing < distancePerTick * delta / 2 || distanceXToCrossing > GameplayState.SQUARE_SIZE
				- distancePerTick * delta / 2)
				&& (distanceYToCrossing < distancePerTick * delta / 2 || distanceYToCrossing > GameplayState.SQUARE_SIZE
						- distancePerTick * delta / 2);
	}

	private void changeOrientation() {
		Vector<Integer> ways = getPossibleWays();
		if (ways.size() == 0) { // has to turn around
			orientation = (orientation + 2) % 4;
		} else { // randomly choose direction
			orientation = ways.get(GameplayState.getRandom().nextInt(
					ways.size()));
		}
		// orientation = (orientation + 1) % 4;
	}

	private Vector<Integer> getPossibleWays() {
		Vector<Integer> res = new Vector<Integer>();
		int x = getInternalPosition().x;
		int y = getInternalPosition().y;
		char[] adjacent = new char[4];
		if (x > 0) {
			adjacent[NORTH] = GameplayState.STANDARD_GAME_FIELD[y - 1][x];
		} else {
			adjacent[NORTH] = 'X';
		}
		if (y < GameplayState.STANDARD_GAME_FIELD[0].length - 1) {
			adjacent[EAST] = GameplayState.STANDARD_GAME_FIELD[y][x + 1];
		} else {
			adjacent[EAST] = 'X';
		}
		if (x < GameplayState.STANDARD_GAME_FIELD.length - 1) {
			adjacent[SOUTH] = GameplayState.STANDARD_GAME_FIELD[y + 1][x];
		} else {
			adjacent[SOUTH] = 'X';
		}
		if (y > 0) {
			adjacent[WEST] = GameplayState.STANDARD_GAME_FIELD[y][x - 1];
		} else {
			adjacent[WEST] = 'X';
		}
		for (int i = 3; i < 6; i++) {
			if (adjacent[(orientation + i) % 4] != 'X') {
				res.add((orientation + i) % 4);
			}
		}
		return res;
	}

	public void move(int delta) {
		float xFactor, yFactor;
		if (orientation == NORTH) {
			xFactor = 0;
			yFactor = -1;
		} else if (orientation == EAST) {
			xFactor = 1;
			yFactor = 0;
		} else if (orientation == SOUTH) {
			xFactor = 0;
			yFactor = 1;
		} else {
			xFactor = -1;
			yFactor = 0;
		}

		setPosition(new Vector2f(getPosition().getX() + xFactor
				* distancePerTick * delta, getPosition().getY() + yFactor
				* distancePerTick * delta));
	}

	public Point getInternalPosition() {
		int x = Math
				.round((getPosition().getX() - GameplayState.STARTING_POINT)
						/ GameplayState.SQUARE_SIZE);
		int y = Math
				.round((getPosition().getY() - GameplayState.STARTING_POINT)
						/ GameplayState.SQUARE_SIZE);
		return new Point(x, y);
	}
}
