#pragma once  
#include <string>
#define MAX_ITEMS_IN_GAME 128
#define ITEM_SPRITE_PATH "../res/HUD/items1.png"
#include "SDL2\SDL.h"
class Display;

class Item {
public:
	Item(std::string m_name, int m_id, int m_spriteKey, SDL_Rect& boundingRect, SDL_Rect& sourceRect);
	~Item();
	inline int getId() {
		return m_id;
	}
	void render(Display display, SDL_Rect dest, SDL_Texture& tex, SDL_Rect& src);

	SDL_Rect getBoundingRect() {
		return boundingRect;
	}

	SDL_Rect getSourceRect() {
		return sourceRect;
	}
private:
	std::string m_name;
	int m_id;
	int m_spriteKey;
	SDL_Rect boundingRect;
	SDL_Rect sourceRect;
};