package com.forge.engine.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOStream {

	private static String everything;
	

	public static String readFile(String path, int lineNum) {

		String sasd = "0";
		try {
			FileInputStream fstream = new FileInputStream("res/playerData/"
					+ path + ".txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));


			everything = br.readLine();

			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return everything.toString();
	}

}
