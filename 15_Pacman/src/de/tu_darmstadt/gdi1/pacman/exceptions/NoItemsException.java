package de.tu_darmstadt.gdi1.pacman.exceptions;

public class NoItemsException extends Exception
{
	public NoItemsException()
	{
		super("Es gibt keine sammelbaren Items!");
	}
}
