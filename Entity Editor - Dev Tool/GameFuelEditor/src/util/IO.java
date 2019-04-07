package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Provides basic, static file handling
 * 
 * @author Brad Welsh
 */
public class IO {

	public static File file;
	public static List<String> lines;

	/** Writes a file as a List<String> using UTF_8 Text */
	public static void writeFile(String sourceDirectory, String name, String content) {
		for (String s : Globals.ACCEPTABLE_EXTENSIONS) {
			if (!name.contains(s)) {
				JOptionPane.showMessageDialog(null, "The file must have an appropriate extension", "Invalid Extension", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(Globals.ENTITY_DIRECTORY + name, "UTF-8");
			writer.println("Version: " + Globals.VERSION);
			writer.println(content);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("Could not write to file: " + name);
			e.printStackTrace();
		}

	}

	/** Loads a file as a List<String> using UTF_8 Text */
	public static List<String> loadFile(String sourceDirectory, String name) {
		file = new File(sourceDirectory + name);
		try {
			List<String> lines = readFile(file.toString(), StandardCharsets.UTF_8);
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find file: " + name + ".txt in " + sourceDirectory);
			return null;
		}

	}

	/** Reads a file as a List<String> using UTF_8 Text */
	public static List<String> readFile(String path, Charset encoding) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(path), encoding);
		return lines;
	}

	public static BufferedImage getImage(String path) {
		BufferedImage img = null;
		if (path.length() > 0) {
			try {
				img = ImageIO.read(new File(path));
				if (!checkImageSpecifications(img)) {
					return null;
				}
				return img;
			} catch (IOException e) {
				System.out.println("Could not load image");
			}
		} else {
			System.out.println("No image path entered.");
		}
		return null;
	}

	public static boolean checkImageSpecifications(BufferedImage img) {
		if ((img.getWidth() % 32 != 0) || (img.getHeight() % 32 != 0)) {
			System.out.println("Error loading image: Image dimensions must be multiples of 32.");
			return false;
		}
		return true;
	}

	public static void createFile(String path) {
		@SuppressWarnings("unused")
		File file = new File(path);
	}

	public static boolean fileExists(String sourceDirectory, String name) {
		File checkFile = new File(sourceDirectory + name);
		return checkFile.exists();
	}

}
