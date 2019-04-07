#include "Mob.h"

Mob::Mob(){
	m_px = 0;
	m_py = 0;
}

Mob::Mob(int mobId, int x, int y, int z, int rot, std::string scriptPath){
	m_px = x;
	m_py = y;
	m_rot = rot;
	currentFrame = 0;
	m_scriptPath = scriptPath;


}

void Mob::init(){

	m_boundingRect.h = STANDARD_SPRITE_SIZE;
	m_boundingRect.w = STANDARD_SPRITE_SIZE;
	m_boundingRect.x = m_px;
	m_boundingRect.y = m_py;

	textureSourceRect.h = STANDARD_SPRITE_SIZE;
	textureSourceRect.w = STANDARD_SPRITE_SIZE;
	textureSourceRect.x = 0;
	textureSourceRect.y = 0;

}

void Mob::checkCollision(){

}

void Mob::render(Display display, SDL_Rect dest, SDL_Texture* tex, SDL_Rect src){
	
	dest.x += getCamera()->px;
	dest.y += getCamera()->py;

	SDL_SetRenderTarget(display.getSDLRenderer(), tex);
	SDL_RenderCopy(display.getSDLRenderer(), tex, &src, &dest);
}

void Mob::update(int frame){
	//if mob is of certain type, run certain update script

	checkCollision();

	
}


void Mob::getInput(){

}

void Mob::setSprite(SDL_Renderer* renderer, char* path, int x, int y, int w, int h){
	if (x == NULL || y == NULL){
		m_currentSprite = new Sprite(renderer, path);
	}
	else {
		m_currentSprite = new Sprite(renderer, path, x, y, w, h);
	}
}

Mob::~Mob(){

}