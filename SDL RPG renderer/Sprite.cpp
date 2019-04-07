#include "Sprite.h"

Sprite::Sprite(){

}

Sprite::Sprite(SDL_Renderer* renderer, string path){
	m_texture = loadTextureToSprite(renderer, path);
	SDL_QueryTexture(m_texture, &m_texFormat, NULL, &m_sizeX, &m_sizeY);
}

//TODO: Force bounding rectangle to adhere to xy, wh constraints
Sprite::Sprite(SDL_Renderer* renderer, string path, int x, int y, int w, int h){
}

SDL_Texture* Sprite::loadTextureToSprite(SDL_Renderer* renderer, string path){
	SDL_Texture* texture = IMG_LoadTexture(renderer, path.c_str());
	return texture;
	
}



Sprite::~Sprite(){
	SDL_DestroyTexture(m_texture);
}
