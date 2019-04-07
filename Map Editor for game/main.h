
#ifndef MAIN_H
#define MAIN_H

#include "Global.h"
#include "Sprite.h"
#include "Display.h"
#include <SDL2\SDL.h>
#include <time.h>
#include "Map.h"

class Display;
class Map;
	class CMain	{
	public:

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
		
	private:
	
		Display* display;
		Map* map;


	};
#endif