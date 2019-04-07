#include "HUD.h"
#include <math.h>
#define HP_BAR_SCALE 0.6
#define INV_BOX_WIDTH 156
#define INV_BOX_HEIGHT INV_BOX_WIDTH

HUD::~HUD() {
}

HUD::HUD() {

}

void HUD::refresh(){

	if (m_updateRequest){
	}
	m_updateRequest = false;
}

void HUD::throwUpdate() {
	m_updateRequest = true;
}

void HUD::setHP(float health, float maxHealth){
	m_hp = health;
	m_maxhp = maxHealth;

	healthbar->boundingRect.w = (int) (healthBarBase->boundingRect.w * (int)m_hp / (int)m_maxhp);
	
	throwUpdate();
}


void HUD::resetHealthBar(){
	healthbar->boundingRect.w = healthBarBase->boundingRect.w;
	m_hp = 0;
	m_maxhp = 0;
}


void HUD::init(Display* display){
	m_hp = 0; m_maxhp = 0;
	m_displayptr = display;
	m_updateRequest = true;

	initComponent(m_sidePanel, "../res/HUD/sidePanel.png", 192, 600, DISPLAY_WIDTH - 192, (DISPLAY_HEIGHT - 600) / 2, 192, 600, 0, 0);
	initComponent(healthBarBase, "../res/HUD/0.png", (m_sidePanel->boundingRect.w - 18), 16 * HP_BAR_SCALE, m_sidePanel->boundingRect.x + 9, m_sidePanel->boundingRect.y + 18, 512, 16, 0, 0);
	initComponent(healthbar, "../res/HUD/1.png", healthBarBase->boundingRect.w, 16 * HP_BAR_SCALE, healthBarBase->boundingRect.x, healthBarBase->boundingRect.y, 512, 16, 0, 0);
	initComponent(m_invComponent, "../res/HUD/inventoryHolder.png", INV_BOX_WIDTH, INV_BOX_HEIGHT, m_sidePanel->boundingRect.x + m_sidePanel->boundingRect.w / 2 - INV_BOX_WIDTH / 2, m_sidePanel->boundingRect.h / 2, INV_BOX_WIDTH, INV_BOX_HEIGHT, 0, 0);

	initInventory();
	updateInventory();

	World::getInstance()->setHUDComponentVec(m_HUDcomponents);

	for (int i = 0; i < MAX_INV_ITEMS; ++i) {
		//m_Sprites.push_back(make_shared<Sprite>(m_displayptr->getSDLRenderer(), ITEM_SPRITE_PATH));
	}
}

void HUD::initInventory() {
	for (int i = 0; i < MAX_INV_ITEMS; ++i) {
		//Fetch inventory data from player and use it to load a sprite for each key
		//Add and implement initComponent(Sprite s, w,h,x,y,sw,sh,sx,sy);
		//initComponent(m_invItems.at(i), ITEM_SPRITE_PATH, 32, 32, 0, 0, 32, 32, 0, 0);
	}
}

void HUD::updateInventory() {
	/* 
	for (int i = 0; i < 16; ++i) {
		int x, y;
		x = m_invComponent->boundingRect.x + 2 + std::floor((i / 4)) * 34;
		y = m_invComponent->boundingRect.y + 2 + (i % 4) * 34;
		m_invItems[i]->boundingRect.x = x;
		m_invItems[i]->boundingRect.y = y;

		m_invItems[i]->
		//FETCH SPRITE DATA FROM HUD ASSET POOL and update items
	}*/
}



//Right now im trying to change all the HUDcomponent structures into smartpointers instead of regular ptrs. Currently need to refactor the below code


void HUD::initComponent(shared_ptr<HUDcomponent>& component, char* spritePath, int w, int h, int x, int y, int sw, int sh, int sx, int sy){
	component->rotation = 0;
	component->boundingRect.w = w;
	component->boundingRect.h = h;
	component->boundingRect.x = x;
	component->boundingRect.y = y;
	component->sourceRect.w = sw;
	component->sourceRect.h = sh;
	component->sourceRect.x = sx;
	component->sourceRect.y = sy;


	//std::shared_ptr<Sprite> spr (new Sprite(m_displayptr->getSDLRenderer(), spritePath));
	//m_Sprites[m_spriteCount] = *spr;


	//component->componentSprite = &m_Sprites[m_spriteCount];
	++m_spriteCount;

	component->componentSprite = new Sprite(m_displayptr->getSDLRenderer(), spritePath);
	addHUDComponent(component);
}

void HUD::addHUDComponent(shared_ptr<HUDcomponent> component){
	m_HUDcomponents.push_back(component);
}

void HUD::render(Display* display){
	for (int i = 0; i < (int) m_HUDcomponents.size(); ++i){
		display->drawOverlay(
			m_HUDcomponents.at(i)->boundingRect,
			m_HUDcomponents.at(i)->componentSprite->getTexture(),
			m_HUDcomponents.at(i)->sourceRect,
			m_HUDcomponents.at(i)->rotation);
	}
}

void HUD::throwPlayerUpdate(Inventory* inv) {
	
	//for (int i = 0; i < 2; ++i) {
	//	m_invitems[i]->componentsprite = &(m_sprites.at(inv->getslotitem(i)->getid()));
	//}
	//m_updaterequest = true;
}
