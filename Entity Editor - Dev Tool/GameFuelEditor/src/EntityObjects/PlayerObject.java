package EntityObjects;

import java.io.Serializable;

public class PlayerObject extends EntityObject implements Serializable {

	private static final long serialVersionUID = -4206933466050861233L;
	public byte playerRights = 0;

	public PlayerObject(int type, String name, String id, String texturePath, byte playerRights) {
		super(type, name, id, texturePath);
		this.playerRights = playerRights;
	}

	public byte getPlayerRights() {
		return playerRights;
	}

	public void setPlayerRights(byte playerRights) {
		this.playerRights = playerRights;
	}

}
