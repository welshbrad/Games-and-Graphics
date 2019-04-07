package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * Reads saved serialized files into a castable object.
 * 
 * @author Brad Welsh
 */
public class SerializedReader {

	private static String file;
	private static Object retrievedObject;
	private static ObjectInputStream ois;

	/** Returns the instance of the object saved in the <fileName>.ser */
	public static Object readObjectFromFile(String directory, String fileName) throws IOException, ClassNotFoundException {
		if (!fileName.contains(".ser")) {
			fileName = fileName + ".ser";
		}
		file = Globals.ENTITY_DIRECTORY + fileName;
		try {
			FileInputStream streamIn = new FileInputStream(file);
			ois = new ObjectInputStream(streamIn);
			retrievedObject = ois.readObject();
			return retrievedObject;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could not load file: '" + file);
		} finally {
			ois.close();
		}
		return null;
	}
}
