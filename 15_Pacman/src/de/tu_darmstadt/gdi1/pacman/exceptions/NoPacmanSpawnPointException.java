package de.tu_darmstadt.gdi1.pacman.exceptions;

public class NoPacmanSpawnPointException extends Exception
{
	public NoPacmanSpawnPointException()
	{
		super("Es wurde kein Spawnpunkt für Pacman definiert!");
	}
}
