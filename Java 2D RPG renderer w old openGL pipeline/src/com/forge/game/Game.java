package com.forge.game;

import java.io.File;
import java.util.ArrayList;

import com.forge.engine.GameObject;
import com.forge.engine.HudComponent;
import com.forge.engine.Main;
import com.forge.engine.file.IOStream;
import com.forge.gameobject.Player;
import com.forge.gameobject.Zombie;
import com.forge.gameobject.item.IronSword;
import com.forge.gameobject.item.Item;
import com.forge.gameobject.item.WoodenSword;

public class Game {

	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> remove;
	private ArrayList<HudComponent> hudComp;
	private Player player;
	private Item item;
	private File file = new File("res/sprites/");
	private File playerData = new File("res/playerData/Brad.txt");

	public Game() {

		player = new Player();
		hudComp = new ArrayList<HudComponent>();
		objects = new ArrayList<GameObject>();
		remove = new ArrayList<GameObject>();

		objects.add(new Zombie(250, 375, 10));
		objects.add(player);

		for (int i = 0; i < file.listFiles().length; i++) {
			hudComp.add(new HudComponent(i, sortHud(i)));
		}

		for (int x = 0; x < 20; x++) {
			objects.add(new WoodenSword(x * 32, 300, player));
		}
		for (int x = 0; x < 35; x++) {
			objects.add(new IronSword(x * 32, 350, player));
		}


	}

	public void getInput() {
		player.getInput();
	}

	public void update() {
		for (GameObject go : objects) {
			if (!go.getRemove()) {
				go.update();
			} else {
				remove.add(go);
			}

		}
		for (GameObject go : remove)
			objects.remove(go);
		for (HudComponent hc : hudComp) {
			hc.update();
		}
	}

	public void render() {
		for (GameObject go : objects)
			go.render();
		for (HudComponent hc : hudComp) {
			hc.render();
		}

	}

	public int[] sortHud(int i) {
		int[] dimens = { 0, 0, 0 };
		switch(i){
		case 0:
			dimens[0] = 5; // x
			dimens[1] = 20; // y
			break;
		case 1:
			dimens[0] = 5;
			dimens[1] = 5;
			break;
		}
		return dimens;
	}

	public void removeObject(GameObject target) {
		for (GameObject go : objects) {
			if (go == target) {
			}
			objects.remove(go);
		}
	}
}
