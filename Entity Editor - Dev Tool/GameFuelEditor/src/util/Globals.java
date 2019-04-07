package util;

import java.util.ArrayList;
import java.util.List;

public class Globals {
	
	/** Current version of the GameFuel Engine */
	public final static float VERSION = 1.01f;
	/** Defines the title of the window on startup */
	public final static String applicationTitle = "GameFuel Editor - v" + VERSION;
	/** Defines the width of the application JFrame */
	public final static int WIDTH = 1000;
	/** Defines the height of the application JFrame */
	public final static int HEIGHT = 800;

	/** Location of entity save files */
	public final static String ENTITY_DIRECTORY = "saves/entities/";
	/** Location of texture files used by entities */
	public final static String TEXTURE_DIRECTORY = "saves/textures/";

	public final static List<String> ACCEPTABLE_EXTENSIONS = new ArrayList<String>();

	public static void init() {
		ACCEPTABLE_EXTENSIONS.add(".ser");
		ACCEPTABLE_EXTENSIONS.add(".txt");
		ACCEPTABLE_EXTENSIONS.add(".png");
		ACCEPTABLE_EXTENSIONS.add(".gif");

	}
}
