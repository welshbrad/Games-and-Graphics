#ifndef MAIN_H
#define MAIN_H

#include "Sprite.h"
#include "Player.h"
#include "Display.h"
#include <SDL2\SDL.h>
#include "Global.h"
#include <time.h>
#include "Map.h"
#include "Entity.h"
#include "Camera.h"
#include "World.h"
#include "Mob.h"
#include "HUD.h"


class HUD;
class Display;
class Map;
class World;
class Player;
class Entity;
class Mob;
class Sprite;

struct HUDcomponent;


	class CMain	{
	public:

		std::vector<Entity*> entities;

		CMain();
		~CMain();

		void init();
		void tick(clock_t* currentTimeMillis, clock_t* lastTime, double* delta, clock_t* divisions);
		void gameLoop(void);
		void update(int frame);
		void render();
		bool quit;
		Display* getDisplay() { return display; }
		Map* getMap() { return map; }
		Player* getPlayer(){ return player; }
		HUD* getOverlay(){ return mainOverlay; }		
	private:
	
		void pushEntity(Entity* p_entity);

		Display* display;
		Entity* playerEntity;
		Player* player;
		Map* map;
		Camera camera;
		World* m_world;
		
		HUD* mainOverlay;
	};
#endif