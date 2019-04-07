#ifndef SPRITE_H
#define SPRITE_H

#include "main.h"
#include "Display.h"
#include <SDL_image.h>
#include <SDL2\SDL.h>

class Sprite
{
public:
	Sprite();
	Sprite(SDL_Renderer* renderer, char* path);
	Sprite(SDL_Renderer* renderer, char* path, int x, int y, int w, int h);
	//For animated sprite

	~Sprite();

	int getWidth() const{
		return m_sizeX;
	}
	int getHeight() const{
		return m_sizeY;
	}

	SDL_Texture* loadTextureToSprite(SDL_Renderer* renderer, char* path);

	SDL_Texture* getTexture(){
		return m_texture;
	}

private:
	int m_sizeX;
	int m_sizeY;
	Uint32 m_texFormat;
	SDL_Texture* m_texture;
};

#endif