package de.tu_darmstadt.gdi1.pacman.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

public class MainMenuState extends BasicGameState {

	private int stateID;
	private StateBasedEntityManager entityManager;

	/**
	 * @param stateID
	 * @param entityManager
	 */
	public MainMenuState(int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		// background
		Entity background = new Entity("menu");
		background.setPosition(new Vector2f(400, 300));
		background.addComponent(new ImageRenderComponent(new Image(
				"res/pictures/theme1/menu/background.jpg")));

		entityManager.addEntity(stateID, background);

		// new game button
		Entity new_Game_Button = new Entity("New Game");
		new_Game_Button.setPosition(new Vector2f(218, 190));
		new_Game_Button.setScale(1);
		new_Game_Button.addComponent(new ImageRenderComponent(new Image(
				"res/pictures/theme1/entities/P0.png")));

		ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(),
				new MouseClickedEvent());
		Action new_Game_Action = new ChangeStateInitAction(
				Pacman.GAMEPLAY_STATE);
		mainEvents.addAction(new_Game_Action);
		new_Game_Button.addComponent(mainEvents);

		entityManager.addEntity(this.stateID, new_Game_Button);

		// quit button
		Entity quit_Button = new Entity("Beenden");
		quit_Button.setPosition(new Vector2f(218, 290));
		quit_Button.setScale(1);
		quit_Button.addComponent(new ImageRenderComponent(new Image(
				"res/pictures/theme1/entities/G1.png")));

		ANDEvent mainEvents_q = new ANDEvent(new MouseEnteredEvent(),
				new MouseClickedEvent());
		Action quit_Action = new QuitAction();
		mainEvents_q.addAction(quit_Action);
		quit_Button.addComponent(mainEvents_q);

		entityManager.addEntity(this.stateID, quit_Button);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		entityManager.renderEntities(container, game, g);

		int start_Position = 180;
		int distance = 100;
		int counter = 0;

		g.drawString("Neues Spiel", 110, start_Position  + counter * distance );
		counter++;
		g.drawString("Beenden", 110, start_Position + counter * distance);
		counter++;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
