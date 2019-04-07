package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Creates and saves object data into a serialized file.
 * 
 * @author Brad Welsh
 */
public class SerializedWriter {

	private static File path;

	/**
	 * Turns an object into a serialized stream of bytes, then writes it into a
	 * .ser file.
	 * 
	 * @param o
	 *            Object to be written into file (must implement serializable
	 *            interface).
	 * @param directory
	 *            Directory for file to be stored (using Global string)
	 * @param fileName
	 *            Name of file
	 */
	public static void writeObject(Object o, String directory, String fileName) {
		fileName.replaceAll(".ser", "");
		path = new File(Globals.ENTITY_DIRECTORY + fileName + ".ser");

		try {
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
