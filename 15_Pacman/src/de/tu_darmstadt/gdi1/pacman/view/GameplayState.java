package de.tu_darmstadt.gdi1.pacman.view;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.pacman.entities.GhostEntity;
import de.tu_darmstadt.gdi1.pacman.entities.PacmanEntity;
import de.tu_darmstadt.gdi1.pacman.parser.Level;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyPressedEvent;

public class GameplayState extends BasicGameState {

	protected int stateID;
	protected StateBasedEntityManager entityManager;
	protected static Random random = new Random();
	protected Level level;

	public final static char[][] STANDARD_GAME_FIELD = {
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'U', ' ', ' ', ' ', ' ', ' ', 'U', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'G', 'G', 'G', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', 'X', 'X', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', 'P', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', 'X', ' ', 'X' },
			{ 'X', 'U', ' ', ' ', 'X', ' ', ' ', 'U', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	public final static char[][] LEVEL_MATRIX = new Level("res/levels/Extended 3.txt").getLevel();
	public final static int SQUARE_SIZE = 35;
	public final static float STARTING_POINT = SQUARE_SIZE / 2.0f;
	public final static int FIELD_SIZE_X = SQUARE_SIZE
			* LEVEL_MATRIX[0].length;
	public final static int FIELD_SIZE_Y = SQUARE_SIZE
			* LEVEL_MATRIX.length;
	protected Entity[][] field;
	protected Vector<Entity> pacmanSpawners;
	protected Vector<Entity> ghostSpawners;
	protected PacmanEntity player;
	protected GhostEntity[] ghosts;
	protected int ghostCnt;

	GameplayState(int sid) {
		stateID = sid;
		entityManager = StateBasedEntityManager.getInstance();
		pacmanSpawners = new Vector<Entity>(4, 4);
		ghostSpawners = new Vector<Entity>(4, 4);
		ghostCnt = 1;
		ghosts = new GhostEntity[ghostCnt];
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		// background
		// Entity background = new Entity("background");
		// background.setPosition(new Vector2f(400, 300));
		// background.addComponent(new ImageRenderComponent(new Image(
		// "res/pictures/theme1/menu/background.jpg")));
		// StateBasedEntityManager.getInstance().addEntity(stateID, background);

		// escape button listener
		Entity esc_Listener = new Entity("ESC_Listener");
		KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);
		esc_pressed.addAction(new ChangeStateAction(Pacman.MAINMENU_STATE));
		esc_Listener.addComponent(esc_pressed);
		entityManager.addEntity(stateID, esc_Listener);

		// create static (non-moving) environment, reading game from matrix
		// standardGameField
		int menubar = 0;
		((AppGameContainer) container).setDisplayMode(FIELD_SIZE_X, FIELD_SIZE_Y
				+ menubar, false);
		for (int i = 0; i < LEVEL_MATRIX.length; i++) {
			for (int j = 0; j < LEVEL_MATRIX[i].length; j++) {
				Entity element = new Entity("element "
						+ (i * LEVEL_MATRIX[i].length + j));
				element.setPosition(new Vector2f(SQUARE_SIZE * j
						+ STARTING_POINT, SQUARE_SIZE * i + STARTING_POINT
						+ menubar));

				String picDir;
				switch (LEVEL_MATRIX[i][j]) {
				case 'X':
					picDir = "res/pictures/theme1/map/X15.png";
					break;
				case 'U':
					picDir = "res/pictures/theme1/entities/powerup.png";
					break;
				case ' ':
					picDir = "res/pictures/theme1/entities/dot.png";
					break;
				case 'G':
					ghostSpawners.add(element);
					picDir = "res/pictures/theme1/map/B.png";
					break;
				case 'P':
					pacmanSpawners.add(element);
					picDir = "res/pictures/theme1/map/B.png";
					break;
				case 'T':
					picDir = "res/pictures/theme1/entities/teleporter.png";
					break;
				case 'S':
					picDir = "res/pictures/theme1/entities/speedup.png";
					break;
				default:
					System.err.println("character not recognized");
					return;
				}
				element.addComponent(new ImageRenderComponent(new Image(picDir)));
				StateBasedEntityManager.getInstance().addEntity(stateID,
						element);
			}
		}

		// pacman
		player = new PacmanEntity("Player");
		Entity spawn = pacmanSpawners
				.get(random.nextInt(pacmanSpawners.size()));
		player.setPosition(spawn.getPosition());
		player.addComponent(new ImageRenderComponent(new Image(
				"res/pictures/theme1/entities/P0.png")));
		StateBasedEntityManager.getInstance().addEntity(stateID, player);

		// ghosts
		for (int i = 0; i < ghosts.length; i++) {
			ghosts[i] = new GhostEntity("Ghost" + i);
			spawn = ghostSpawners.get(random.nextInt(ghostSpawners.size()));
			ghosts[i].setPosition(spawn.getPosition());
			ghosts[i].addComponent(new ImageRenderComponent(new Image(
					"res/pictures/theme1/entities/G" + i % 4 + ".png")));
			StateBasedEntityManager.getInstance().addEntity(stateID, ghosts[i]);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		entityManager.renderEntities(container, game, g);
	}

	@Override
	public int getID() {
		return this.stateID;
	}

	public static Random getRandom() {
		return random;
	}

}
