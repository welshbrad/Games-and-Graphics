#include "World.h"
#include "Sprite.h"
#include "Display.h"
#include <string>
#include "SDL2\SDL_rect.h"


World *World::instance = 0;

World::World() {

}

void World::setupWorld(){
	m_worldX = 0;
	m_worldY = 0;

	//Testing gold item sprite.
	SDL_Rect boundingRect;
	boundingRect.w = STANDARD_SPRITE_SIZE;
	boundingRect.h = STANDARD_SPRITE_SIZE;
	boundingRect.x = STANDARD_SPRITE_SIZE;
	boundingRect.y = STANDARD_SPRITE_SIZE;

	SDL_Rect sourceRect;
	sourceRect.w = STANDARD_SPRITE_SIZE;
	sourceRect.h = STANDARD_SPRITE_SIZE;
	sourceRect.x = 0;
	sourceRect.y = 0;

	m_worldItems.push_back(Item("Gold", 1, 1, boundingRect, sourceRect));

}

void World::loadAssets() {
	string path = "res/items/items1";
	Sprite* s = new Sprite(m_display->getSDLRenderer(), path);
	worldSprites.push_back(s);
}

World::~World(){

}