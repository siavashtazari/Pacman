package de.tu_darmstadt.gdi1.pacman.exceptions;

public class NoGhostSpawnPointException extends Exception
{
	public NoGhostSpawnPointException()
	{
		super("Es wurde kein Spawnpunkt für die Geister definiert!");
	}
}
