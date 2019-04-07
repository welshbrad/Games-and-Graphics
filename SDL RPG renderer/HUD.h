#ifndef HUD_H
#define HUD_H

#include "Global.h"
#include "main.h"
#include "Sprite.h"
#include "Inventory.h"

#include <array>
#include <memory>
#include <vector>

#define MAX_INV_ITEMS 16

using namespace std;

class Display;
class Sprite;
class Inventory;

struct HUDcomponent{
	SDL_Rect boundingRect;
	SDL_Rect sourceRect;
	Sprite* componentSprite;
	float rotation;
};

class HUD{
public:
	~HUD();
	HUD();
	void addHUDComponent(shared_ptr<HUDcomponent> component);

	void throwUpdate();
	void throwPlayerUpdate(Inventory* inv);
	void init(Display* displayptr);
	void refresh();
	void render(Display* display);
	void initComponent(shared_ptr<HUDcomponent>& component, char* spritePath, int w, int h, int x, int y, int sw, int sh, int sx, int sy);
	void setHP(float health, float maxHealth);
	void resetHealthBar();
	void updateInventory();
private:
	void initInventory();

	float m_hp, m_maxhp;
	vector<shared_ptr<HUDcomponent>> m_HUDcomponents;
	bool m_updateRequest;
	Display* m_displayptr;

	shared_ptr<HUDcomponent> healthbar = make_shared<HUDcomponent>();
	shared_ptr<HUDcomponent> healthBarBase = make_shared<HUDcomponent>();
	shared_ptr<HUDcomponent> m_invComponent = make_shared<HUDcomponent>();
	vector<shared_ptr<HUDcomponent>> m_invItems;
	
	shared_ptr<HUDcomponent> m_sidePanel = make_shared<HUDcomponent>();
	int m_spriteCount;
	vector<shared_ptr<Sprite>> m_Sprites;
};
#endif