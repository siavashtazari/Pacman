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

	public GhostEntity(String entityID) {
		super(entityID);
		this.orientation = NORTH;
		this.speed = 3;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		if (isAtSquareCenter()) {
			changeOrientation();
			// System.out.println("orientation changed");
		}
		// System.out.println(delta);
		// refresh rate is 60 Hz (delta = 16-17 ms)
		move(delta);
	}

	private boolean isAtSquareCenter() {
		float x = getPosition().getX();
		float y = getPosition().getY();
		int distanceXToCrossing = ((int) (x - GameplayState.STARTING_POINT))
				% GameplayState.SQUARE_SIZE;
		int distanceYToCrossing = ((int) (y - GameplayState.STARTING_POINT))
				% GameplayState.SQUARE_SIZE;
		return (distanceXToCrossing <= speed / 2.0 || distanceXToCrossing > GameplayState.SQUARE_SIZE
				- speed / 2.0)
				&& (distanceYToCrossing <= speed / 2.0 || distanceYToCrossing > GameplayState.SQUARE_SIZE
						- speed / 2.0);
	}

	private void changeOrientation() {
		Vector<Integer> ways = getPossibleWays();
		if (ways.size() == 0) {
			orientation = (orientation + 2) % 4; // has to turn around
		} else {
			orientation = ways.get(GameplayState.getRandom().nextInt(
					ways.size())); // randomly choose direction
		}
	}

	private Vector<Integer> getPossibleWays() {
		Vector<Integer> res = new Vector<Integer>();
		int x = getInternalPosition().x;
		int y = getInternalPosition().y;
		char[] adjacent = new char[4];
		if (y > 0) {
			adjacent[NORTH] = GameplayState.LEVEL_MATRIX[y - 1][x];
		} else {
			adjacent[NORTH] = 'X';
		}
		if (x < GameplayState.LEVEL_MATRIX[0].length - 1) {
			adjacent[EAST] = GameplayState.LEVEL_MATRIX[y][x + 1];
		} else {
			adjacent[EAST] = 'X';
		}
		if (y < GameplayState.LEVEL_MATRIX.length - 1) {
			adjacent[SOUTH] = GameplayState.LEVEL_MATRIX[y + 1][x];
		} else {
			adjacent[SOUTH] = 'X';
		}
		if (x > 0) {
			adjacent[WEST] = GameplayState.LEVEL_MATRIX[y][x - 1];
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
		float xDelta, yDelta;
		if (orientation == NORTH) {
			xDelta = 0;
			yDelta = -1;
		} else if (orientation == EAST) {
			xDelta = 1;
			yDelta = 0;
		} else if (orientation == SOUTH) {
			xDelta = 0;
			yDelta = 1;
		} else {
			xDelta = -1;
			yDelta = 0;
		}
		setPosition(new Vector2f(getPosition().getX() + xDelta * speed,
				getPosition().getY() + yDelta * speed));
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
