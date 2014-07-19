package de.tu_darmstadt.gdi1.pacman.tests;

import java.awt.Point;

import de.tu_darmstadt.gdi1.pacman.exceptions.InvalidLevelCharacterException;
import de.tu_darmstadt.gdi1.pacman.exceptions.InvalidLevelFormatException;
import de.tu_darmstadt.gdi1.pacman.exceptions.NoGhostSpawnPointException;
import de.tu_darmstadt.gdi1.pacman.exceptions.NoItemsException;
import de.tu_darmstadt.gdi1.pacman.exceptions.NoPacmanSpawnPointException;
import de.tu_darmstadt.gdi1.pacman.exceptions.ReachabilityException;
//import de.tu_darmstadt.gdi1.pacman.model.Level;
//import de.tu_darmstadt.gdi1.pacman.model.LevelParser;
//import de.tu_darmstadt.gdi1.pacman.model.MapModule;
//import de.tu_darmstadt.gdi1.pacman.model.PacmanGame;

public class PacmanTestAdapterMinimal {
//	protected PacmanGame game;
//
//	/**
//	 * Gibt true aus, wenn aus dem String content ein gültiger Level geladen
//	 * werden kann, ansonsten false. Auftretende Exceptions werden abgefangen
//	 * und haben immer die Rückgabe false zur Folge.
//	 * 
//	 * @param content
//	 * @return true wenn ein g&uuml;ltiger Level geladen werden konnte
//	 */
//	public boolean levelIsValid(String content) {
//		try {
//			LevelParser.fromString(content).validate();
//		} catch (Exception e) {
//			return false;
//		}
//
//		return true;
//	}
//
//	/**
//	 * Parst einen Level aus dem String content und leitet dabei die
//	 * entsprechenden Parserexceptions weiter.
//	 * 
//	 * @param content
//	 *            Textdarstellung des Levels
//	 * @throws InvalidLevelCharacterException
//	 * @throws InvalidLevelFormatException
//	 * @throws NoPacmanSpawnPointException
//	 * @throws ReachabilityException
//	 * @throws NoGhostSpawnPointException
//	 * @throws NoItemsException
//	 */
//	public void levelIsValidWithException(String content)
//			throws InvalidLevelCharacterException, InvalidLevelFormatException,
//			NoPacmanSpawnPointException, ReachabilityException,
//			NoGhostSpawnPointException, NoItemsException {
//		LevelParser.fromString(content).validate();
//	}
//
//	/**
//	 * Gibt die Anzahl der Felder zurück, die zum Spawnen der Spielfigur
//	 * dienen.
//	 * 
//	 * @param content
//	 *            Textdarstellung des Levels
//	 * @return Mögliche Pacman Spawnpunkte
//	 */
//	public int levelGetPacmanSpawnCount(String content) {
//		try {
//			Level level = LevelParser.fromString(content);
//			int count = 0;
//			for (int x = 0; x < level.getWidth(); x++)
//				for (int y = 0; y < level.getHeight(); y++)
//					if (level.get(x, y) == MapModule.PLAYER_SPAWN)
//						count++;
//			return count;
//		} catch (Exception e) {
//			return -1;
//		}
//	}
//
//	/**
//	 * Gibt die Anzahl der Felder zurück, auf denen Geister spawnen können.
//	 * 
//	 * @param content
//	 *            Textdarstellung des Levels
//	 * @return Mögliche Geist Spawnpunkte
//	 */
//	public int levelGetGhostSpawnCount(String content) {
//		try {
//			Level level = LevelParser.fromString(content);
//			int count = 0;
//			for (int x = 0; x < level.getWidth(); x++)
//				for (int y = 0; y < level.getHeight(); y++)
//					if (level.get(x, y) == MapModule.GHOST_SPAWN)
//						count++;
//			return count;
//		} catch (Exception e) {
//			return -1;
//		}
//	}
//
//	/**
//	 * Startet ein neues Spiel und platziert alle Items und Figuren. Das
//	 * gestartete Spiel soll für die folgenden Aufrufe der Spielsteuerung
//	 * verwendet werden.
//	 * 
//	 * @param level
//	 *            Textdarstellung des Levels
//	 */
//	public void startGame(String level) {
//		game = new PacmanGame(true);
//		try {
//			game.changeLevel(LevelParser.fromString(level));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Gibt den char zurück, der das Feld im Level des laufenden Spiels an der
//	 * Position x,y repräsentiert.
//	 * 
//	 * @param x
//	 * @param y
//	 * @return char an Position x,y
//	 */
//	public char getLevelCharAt(int x, int y) {
//		return game.getLevel().get(x, y).getValue();
//	}
//
//	/**
//	 * Ändert das aktuell laufende Level an der Position x,y.
//	 * 
//	 * @param x
//	 * @param y
//	 * @param c
//	 *            Neuer Char des Levels
//	 */
//	public void setLevelChar(int x, int y, char c) {
//		game.getLevel().setField(x, y, c);
//	}
//
//	/**
//	 * Gibt die Textdarstellung des aktuell laufenden Levels aus
//	 * (.toString-Methode).
//	 * 
//	 * @return Textdarstellung des laufenden Levels
//	 */
//	public String getLevelString() {
//		return game.getLevel().toString();
//	}
//
//	/**
//	 * Entfernt alle Geister aus dem Spiel und verhindert deren Respawn
//	 */
//	public void removeGhosts() {
//		game.despawnGhosts();
//	}
//
//	/**
//	 * Versucht die Spielfigur ein Feld nach Oben zu bewegen und gibt aus, ob
//	 * die Bewegung erfolgreich war. Wenn die Bewegung nicht möglich ist, oder
//	 * in diesem Tick bereits eine Bewegung durchgeführt wurde, soll der Befehl
//	 * ignoriert werden.
//	 * 
//	 * @return True, wenn die Bewegung möglich war, sonst false.
//	 */
//	public boolean moveUp() {
//		return game.movePacman(0, -1);
//	}
//
//	/**
//	 * Versucht die Spielfigur ein Feld nach Links zu bewegen und gibt aus, ob
//	 * die Bewegung erfolgreich war. Wenn die Bewegung nicht möglich ist, oder
//	 * in diesem Tick bereits eine Bewegung durchgeführt wurde, soll der Befehl
//	 * ignoriert werden.
//	 * 
//	 * @return True, wenn die Bewegung möglich war, sonst false.
//	 */
//	public boolean moveLeft() {
//		return game.movePacman(-1, 0);
//	}
//
//	/**
//	 * Versucht die Spielfigur ein Feld nach Unten zu bewegen und gibt aus, ob
//	 * die Bewegung erfolgreich war. Wenn die Bewegung nicht möglich ist, oder
//	 * in diesem Tick bereits eine Bewegung durchgeführt wurde, soll der Befehl
//	 * ignoriert werden.
//	 * 
//	 * @return True, wenn die Bewegung möglich war, sonst false.
//	 */
//	public boolean moveDown() {
//		return game.movePacman(0, 1);
//	}
//
//	/**
//	 * Versucht die Spielfigur ein Feld nach Rechts zu bewegen und gibt aus, ob
//	 * die Bewegung erfolgreich war. Wenn die Bewegung nicht möglich ist, oder
//	 * in diesem Tick bereits eine Bewegung durchgeführt wurde, soll der Befehl
//	 * ignoriert werden.
//	 * 
//	 * @return True, wenn die Bewegung möglich war, sonst false.
//	 */
//	public boolean moveRight() {
//		return game.movePacman(1, 0);
//	}
//
//	/**
//	 * Platziert die Spielfigur auf einer beliebigen Position, ohne Prüfungen
//	 * durchzuführen.
//	 * 
//	 * @param x
//	 * @param y
//	 */
//	public void movePacman(int x, int y) {
//		game.getPacman().abortMove();
//		game.getPacman().setPos(new Point(x, y));
//	}
//
//	/**
//	 * Gibt die aktuelle Position der Spielfigur aus.
//	 * 
//	 * @return Aktuelle Position der Spielfigur
//	 */
//	public Point getPacmanPosition() {
//		return game.getPacman().getFieldPos();
//	}
//
//	/**
//	 * Bewegt den Geist zu einer beliebigen Position, ohne Prüfungen
//	 * durchzuführen.
//	 * 
//	 * @param x
//	 * @param y
//	 */
//	public void moveGhost(int x, int y) {
//		game.getGhost().abortMove();
//		game.getGhost().setPos(new Point(x, y));
//	}
//
//	/**
//	 * Gibt die aktuelle Position des Geists aus.
//	 * 
//	 * @return Aktuelle Position des Geists
//	 */
//	public Point getGhostPosition() {
//		return game.getGhost().getFieldPos();
//	}
//
//	/**
//	 * Führt einen Schritt in der Spiellogik, wie das Bewegen von Figuren und
//	 * prüfen auf Kollisionen, aus.
//	 */
//	public void update() {
//		game.updateStep();
//	}
//
//	/**
//	 * Gibt aus, ob das Spiel gewonnen ist.
//	 * 
//	 * @return True, wenn das Spiel gewonnen ist, ansonsten false.
//	 */
//	public boolean isWon() {
//		return game.isWon();
//	}
//
//	/**
//	 * Gibt aus, ob das Spiel verloren ist.
//	 * 
//	 * @return True, wenn das Spiel verloren ist, ansonsten false,
//	 */
//	public boolean isLost() {
//		return game.isLost();
//	}
//
//	/**
//	 * Gibt die aktuelle Punktzahl des Spielers aus.
//	 * 
//	 * @return Aktuelle Punktzahl
//	 */
//	public int getPoints() {
//		return game.getPoints();
//	}
//
//	/**
//	 * Gibt aus, wie oft die die Spielfigur unter Einfluss eines PowerUps einen
//	 * Geist gefressen hat.
//	 * 
//	 * @return Anzahl Geisttötungen
//	 */
//	public int getKills() {
//		return game.getKills();
//	}
//
//	/**
//	 * Gibt aus, ob Pacman aktuell unter Einfluss eines PowerUps steht.
//	 * 
//	 * @return True, wenn ein PowerUp aktiv ist, ansonsten false
//	 */
//	public boolean hasPowerUp() {
//		return game.getPacman().isPoweredUp();
//	}
//
//	/**
//	 * Legt fest, ob Pacman unter dem Einfluss eines PowerUps steht.
//	 * 
//	 * @param enable
//	 */
//	public void setPowerUp(boolean enable) {
//		if (enable)
//			game.getPacman().powerUp();
//		else
//			game.getPacman().deactivatePowerUp();
//	}
}
