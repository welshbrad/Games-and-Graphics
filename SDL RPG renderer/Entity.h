#ifndef ENTITY_H
#define ENTITY_H

#include <SDL2\SDL.h>
#include <vector>
#include "Global.h"

#include "World.h"
#define STANDARD_SPRITE_SIZE 32

class Display;
class Entity;
class Sprite;
class HUD;
class World;
struct Camera;

class Entity
{
public:

	inline int getPX(){
		return m_px;
	}
	inline int getPY(){
		return m_py;
	}

	virtual void getInput() = 0;
	virtual void update(int frame) = 0;
	virtual void render(Display display, SDL_Rect dest, SDL_Texture* tex, SDL_Rect src) = 0;

	virtual void setSprite(SDL_Renderer* renderer, char* path, int x, int y, int w, int h) = 0;



	SDL_Rect getBoundingRect(){
		return m_boundingRect;
	}

	Sprite* getSprite() const;

	SDL_Rect getTextureSourceRect(){
		return textureSourceRect;
	}

	void setCamera(Camera *pCamera){
		m_cam = pCamera;
	}

	Camera* getCamera(){
		return m_cam;
	}

	void setHUD(HUD* hud){
		m_hud = hud;
	}

	Entity();
	~Entity();

protected:

	SDL_Rect m_boundingRect;
	Sprite* m_currentSprite;
	SDL_Texture *m_currentTexture;
	SDL_Rect textureSourceRect;
	int currentFrame;

	Camera* m_cam;

	int m_px;
	int m_py;
	int m_rot;
	int old_px, old_py;
	std::string m_scriptPath;

	HUD* m_hud;

	World* m_world_ptr;

};
#endif