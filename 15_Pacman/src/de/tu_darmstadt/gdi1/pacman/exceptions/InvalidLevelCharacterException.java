package de.tu_darmstadt.gdi1.pacman.exceptions;

public class InvalidLevelCharacterException extends Exception
{
	public InvalidLevelCharacterException(char c)
	{
		super("'" + c + "' ist kein gültiger Levelbaustein!");
	}
}
