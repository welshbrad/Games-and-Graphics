#include "main.h"

CMain::CMain(){
	quit = false;
	display = new Display();
	player = new Player();
	playerEntity = player;

	pushEntity(playerEntity);

	mainOverlay = new HUD();

	map = new Map(Map::Stage::MAIN_TEST_STAGE, Map::SpawnMap::MAIN_TEST_STAGE_SPAWN1);
	m_world = World::getInstance();
	init();
}


int main(int argc, char* argv[]){
	CMain* game = new CMain();

	if ((game->getDisplay()->isValid() == 0)){
		return 1;
	}

	game->getPlayer()->setSprite(game->getDisplay()->getSDLRenderer(), "../res/player/basicPlayer.png", NULL, NULL, NULL, NULL);
	game->getPlayer()->setHUD(game->getOverlay());
	game->getMap()->setSpriteSheet(game->getDisplay()->getSDLRenderer(), "../res/mapSprites/map1.png");
	
	game->gameLoop();

	game->~CMain();

	return 0;
}


void CMain::init(){
	globalInit();
	player->init(getDisplay()->getCurrentDisplayMode(), getMap());
	mainOverlay->init(getDisplay());
	m_world->setupWorld();
	m_world->setActor(player);
	m_world->setDisplay(getDisplay());
	m_world->loadAssets();
	initCamera(&camera, playerEntity);

	map->loadEntities(&entities, display);

	for (int i = 1; i <	(int)entities.size(); i++){
		entities.at(i)->setSprite(getDisplay()->getSDLRenderer(), "../res/pirate1.png", NULL, NULL, NULL, NULL);
		entities.at(i)->setCamera(&camera);
	}
}

void CMain::pushEntity(Entity* p_entity){
	entities.push_back(p_entity);
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
	for (int i = 0; i < (int) entities.size(); i++){
		entities[i]->getInput();
		entities[i]->update(frame);
	} //updates player too

	map->update(frame);
	mainOverlay->refresh();
	updateCamera(&camera);
}

void CMain::render(){
	getMap()->render(getDisplay()->getSDLRenderer(), &camera);
	
	//Render entities & Player
	for (int i = 0; i < (int) entities.size(); i++){
		entities[i]->render(*getDisplay(), entities[i]->getBoundingRect(), entities[i]->getSprite()->getTexture(), entities[i]->getTextureSourceRect());
	}

	for (int i = 0; i < World::getInstance()->getItemVec().size(); i++) {
		World::getInstance()->getItemVec()[i].render(*getDisplay(), World::getInstance()->getItemVec()[i].getBoundingRect(), *World::getInstance()->getWorldSprites()[ITEM_SPRITE_SHEET_1]->getTexture(), World::getInstance()->getItemVec()[i].getSourceRect());
	}


	mainOverlay->render(getDisplay());
	getDisplay()->draw();
}

CMain::~CMain()
{
	display->cleanup();
	display->~Display();
	player->~Player();
}