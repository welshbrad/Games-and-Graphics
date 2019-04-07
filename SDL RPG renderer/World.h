#ifndef WORLD_H
#define WORLD_H
#include <vector>
#include "Global.h"
#include "Item.h"
#include "Display.h"

#include <memory>
#define ITEM_SPRITE_SHEET_1 0
struct HUDcomponent;
class Entity;
class Display;
class Sprite;
using namespace std;

class World{
private:
	//setup a singleton instance so all game objects can access world ptr
	static World* instance;
	int m_worldX, m_worldY;
	Entity* m_mainActor;
	vector<Item> m_worldItems;
	vector<shared_ptr<HUDcomponent>> mp_HUDComponentVec;
	vector<Sprite*> worldSprites;
	Display* m_display;
	
public:
    inline void setDisplay(Display* display) {
		m_display = display;
	}
	void loadAssets();

	inline void setHUDComponentVec(vector<shared_ptr<HUDcomponent>>& HUD) {
		mp_HUDComponentVec = HUD;
	}
	
	inline vector<Sprite*> getWorldSprites() {
		return worldSprites;
	}

	inline vector<shared_ptr<HUDcomponent>> getHUDComponentVec() {
		return mp_HUDComponentVec;
	}

	static World* getInstance() {
		if (!instance) {
			instance = new World();
		}
		return instance;
	}
	World();
	
	void setupWorld();

	inline void setActor(Entity* actor) {
		m_mainActor = actor;
	}
	inline vector<Item> getItemVec() {
		return m_worldItems;
	}


	~World();
}; 


#endif