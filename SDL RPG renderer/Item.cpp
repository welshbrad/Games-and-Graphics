#include "Item.h"
#include "Display.h"

Item::Item(std::string m_name, int m_id, int m_spriteKey, SDL_Rect& boundRect, SDL_Rect& srcRect) {
	this->m_name = m_name;
	this->m_id = m_id;
	this->m_spriteKey = m_spriteKey;
	boundingRect = boundRect;
	sourceRect = srcRect;
}

void Item::render(Display display, SDL_Rect dest, SDL_Texture& tex, SDL_Rect& src) {
	SDL_SetRenderTarget(display.getSDLRenderer(), &tex);
	SDL_RenderCopy(display.getSDLRenderer(), &tex, &src, &dest);
}

Item::~Item() {

}