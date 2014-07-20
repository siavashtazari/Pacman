package de.tu_darmstadt.gdi1.pacman.entities;

import java.awt.Point;

import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.pacman.view.GameplayState;
import eea.engine.entity.Entity;

public class PacmanEntity extends Entity {

	private int orientation;
	public final static int NORTH = 0;
	public final static int EAST = 1;
	public final static int SOUTH = 2;
	public final static int WEST = 3;
	private int speed;

	public PacmanEntity(String entityID) {
		super(entityID);
		this.orientation = EAST;
		this.speed = 3;
	}

	public void react(int direction) {
		if (isOnTheMove() || directionIsFree(direction)) {
			orientation = direction;
			move();
		}
	}

	public boolean isOnTheMove() {
		return ((orientation == PacmanEntity.NORTH || orientation == PacmanEntity.SOUTH) && !yIsOnGrid())
				|| ((orientation == PacmanEntity.EAST || orientation == PacmanEntity.WEST) && !xIsOnGrid());
	}

	private boolean yIsOnGrid() {
		float y = getPosition().getY();
		int distanceYToCrossing = ((int) (y - GameplayState.STARTING_POINT))
				% GameplayState.SQUARE_SIZE;
		return distanceYToCrossing <= speed / 2.0
				|| distanceYToCrossing > GameplayState.SQUARE_SIZE - speed
						/ 2.0;
	}

	private boolean xIsOnGrid() {
		float x = getPosition().getX();
		int distanceXToCrossing = ((int) (x - GameplayState.STARTING_POINT))
				% GameplayState.SQUARE_SIZE;
		return distanceXToCrossing <= speed / 2.0
				|| distanceXToCrossing > GameplayState.SQUARE_SIZE - speed
						/ 2.0;
	}

	private boolean directionIsFree(int direction) {
		int x = getInternalPosition().x;
		int y = getInternalPosition().y;
		char res;
		if (direction == NORTH) {
			res = GameplayState.LEVEL_MATRIX[y - 1][x];
		} else if (direction == EAST) {
			res = GameplayState.LEVEL_MATRIX[y][x + 1];
		} else if (direction == SOUTH) {
			res = GameplayState.LEVEL_MATRIX[y + 1][x];
		} else {
			res = GameplayState.LEVEL_MATRIX[y][x - 1];
		}
		return res != 'X';
	}

	private void move() {
		int xDelta, yDelta;
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

	/**
	 * @return the orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
}
