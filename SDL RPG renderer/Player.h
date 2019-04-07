#ifndef PLAYER_H
#define PLAYER_H
#include <SDL_image.h>
#include <SDL2\SDL.h>
#include <assert.h>
#include "Entity.h"
#include "Physics.h"
#include "Map.h"
#include "Inventory.h"

#define ANIMATION_SPEED 10
#define HEALTH_BAR 1
class Map;
class Display;
class HUD;

class Player : public Entity{
	
public:

	const int walkSpeed = 3;
	Player();
	~Player();

	void getInput();
	void update(int frame);
	void render(Display display, SDL_Rect dest, SDL_Texture* tex, SDL_Rect src);
	void init(SDL_DisplayMode dm, Map* map);
	void animate(int worldFrame);
	void runDeath();
	void getActiveTile();

	void calculateGrid();

	SDL_Texture *getSDLTexture(){
		return m_currentTexture;
	}

	void setSprite(SDL_Renderer* renderer, char* path, int x, int y, int w, int h);

	SDL_Rect getTextureSourceRect(){
		return textureSourceRect;
	}

	inline float getHealth(){
		return health;
	}

	inline void setHealth(float amount){
		health = amount;
	}

	void takeDamage(float amount);
	
	float getStatMax(int skill){
		return 1000;
	}

	int collided();
private:

	const int spritesOnSheet = 3;
	bool m_walking;
	
	bool alive;
	Map* m_map;
	float health;
	int m_gridx;
	int m_gridy;
	int m_lastGridx, m_lastGridy;
	int mapIndex;

	int m_immediateIndices[4];
	Inventory* m_inv;

protected:

};
#endif