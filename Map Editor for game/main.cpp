#include "main.h"

CMain::CMain(){
	quit = false;
	display = new Display();
	
	map = new Map(Map::Stage::MAIN_TEST_STAGE);
	init();
}


int main(int argc, char* argv[]){
	CMain* game = new CMain();

	if ((game->getDisplay()->isValid() == 0)){
		return 1;
	}

	game->getMap()->setSpriteSheet(game->getDisplay()->getSDLRenderer(), "../res/mapSprites/map1.png");
	
	game->gameLoop();

	game->~CMain();

	return 0;
}


void CMain::init(){
	
	

}


void CMain::gameLoop(){
	clock_t lastTime = clock();
	clock_t divisions = (1000 / 60);
	clock_t currentTimeMillis = 0;
	int frame = 0;
	double delta = 0;

	while (!quit){
		//UPDATE
		tick(&currentTimeMillis, &lastTime, &delta, &divisions);
		while (delta >= 1){
			update(frame);
			delta--;
			if (frame == 60){
				frame = 0;
			}
			frame++;
		}

		//EVENTS and PREP
		quit = display->begin();
			

		//RENDER
		render();

		//Clear buffer
		display->clear();

		lastTime = currentTimeMillis;

	}
	
}

void CMain::tick(clock_t* currentTimeMillis, clock_t* lastTime, double* delta, clock_t* divisions){
	*currentTimeMillis = clock();
	*delta += (*currentTimeMillis - *lastTime) / ((double) CLOCKS_PER_SEC / 60);
}

void CMain::update(int frame){
	map->update(frame);
}

void CMain::render(){
	getMap()->render(getDisplay()->getSDLRenderer());

	getDisplay()->draw();
}

CMain::~CMain()
{
	display->cleanup();
	display->~Display();
}