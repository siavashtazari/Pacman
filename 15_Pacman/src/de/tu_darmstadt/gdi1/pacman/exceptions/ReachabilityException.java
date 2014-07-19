package de.tu_darmstadt.gdi1.pacman.exceptions;

public class ReachabilityException extends Exception
{
	public ReachabilityException()
	{
		super("Im Level sind unerreichbare Bereiche vorhanden.");
	}
}
