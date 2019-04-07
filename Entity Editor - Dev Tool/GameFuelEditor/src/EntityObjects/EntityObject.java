package EntityObjects;

import java.io.Serializable;
import java.lang.reflect.Field;

public class EntityObject implements Serializable {

	// common to all entity types
	private int type;
	private String name;
	private String id;
	private String texturePath;

	public EntityObject(int type, String name, String id, String texturePath) {
		this.type = type;
		this.name = name;
		this.id = id;
		this.texturePath = texturePath;

	}

	private static final long serialVersionUID = 3772248498261725984L;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTexturePath() {
		return texturePath;
	}

	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
	}

	public static String toString(Object o) {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		if (o instanceof EntityObject) {
			o = (EntityObject) o;
		}
		result.append(o.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		java.lang.reflect.Field[] fields = o.getClass().getDeclaredFields();

		for (Field field : fields) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				result.append(field.get(o));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}

}