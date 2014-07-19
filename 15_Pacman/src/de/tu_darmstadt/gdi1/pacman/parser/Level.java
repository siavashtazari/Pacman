package de.tu_darmstadt.gdi1.pacman.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Level {

	// Entity[][] / char[][]
	char[][] level;
	String fileName;

	public Level(String fileName) {
		this.fileName = fileName;
		try {
			parseFrom();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createCorrectMatrix() throws IOException {
		FileReader fin = new FileReader(fileName);
		BufferedReader in = new BufferedReader(fin);
		int lineCounter = in.readLine().length();
		String line;
		int columnCounter = 1;
		while ((line = in.readLine()) != null)
			columnCounter++;
		in.close();
		this.level = new char[columnCounter][lineCounter];
	}

	public void parseFrom() throws IOException {
		FileReader fin = new FileReader(fileName);
		BufferedReader in = new BufferedReader(fin);
		createCorrectMatrix();
		String line;
		int column = 0;
		while ((line = in.readLine()) != null) {

			char whitespace = ' ';
			char background = 'B';
			char ghostSpawn = 'G';
			char pacmanSpawn = 'P';
			char speedUp = 'S';
			char teleporter = 'T';
			char powerUp = 'U';
			char wall = 'X';

			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == whitespace)
					level[column][i] = whitespace; // = new Dot;
				if (line.charAt(i) == background)
					level[column][i] = background; // = new Barrier;
				if (line.charAt(i) == ghostSpawn)
					level[column][i] = ghostSpawn; // = new GhostSpawn;
				if (line.charAt(i) == pacmanSpawn)
					level[column][i] = pacmanSpawn; // = new PacmanSpawn;
				if (line.charAt(i) == speedUp)
					level[column][i] = speedUp; // = new SpeedUp;
				if (line.charAt(i) == teleporter)
					level[column][i] = teleporter; // = new Teleporter;
				if (line.charAt(i) == powerUp)
					level[column][i] = powerUp; // = new PowerUp;
				if (line.charAt(i) == wall)
					level[column][i] = wall; // = new Wall;
			}
			column++;
		}
		in.close();
	}

	public char[][] getLevel() {
		return level;
	}
}
