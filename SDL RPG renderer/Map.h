#ifndef MAP_H
#define MAP_H

#define TILE_SIZE 32
#define VOID_SPRITE 1
#define GRASS_SPRITE 1
#define DIRT_SPRITE 2
#define SAND_SPRITE 3


#include <vector>
#include <algorithm>
#include <SDL2\SDL.h>
#include "Camera.h"
#include "Display.h"
#include "Mob.h"
#include "Sprite.h"

class Display;
class Mob;
class Sprite;

class Map{

public:

	const enum Stage {
		MAIN_TEST_STAGE,
		MAIN_TEST_STAGE2
	};

	const enum SpawnMap {
		MAIN_TEST_STAGE_SPAWN1,
		MAIN_TEST_STAGE_SPAWN2,
		MAIN_TEST_STAGE2_SPAWN1
	};


	void loadEntities(std::vector<Entity*>* entities, Display* display);
	void loapMap(char* path);
	void loadStaticSpawns(char* path);
	void optimizeMapStructure();
	char* getStagePath(Map::Stage stage);
	char* getSpawnMapPath(Map::SpawnMap spawnMap);

	
	typedef struct {
		int x;
		int y;
		int z;
		int r;
		int spriteKey;
		int currentFrame;
	} Cell;
	
	int getIndexFromCoords(int x, int y);

	SDL_Rect getSourceRect(){
		return m_sourceRect;
	}

	SDL_Rect getDestinationRect(){
		return m_destinationRect;
	}

	Sprite* getSpriteSheet(){
		return m_spriteSheet;
	}

	void inline setSourceRect(int x, int y, int w, int h){
		m_sourceRect.x = x;
		m_sourceRect.x = y;
		m_sourceRect.x = w;
		m_sourceRect.x = h;
	}
	void inline setDestinationRect(int x, int y, int w, int h){
		m_destinationRect.x = x;
		m_destinationRect.x = y;
		m_destinationRect.x = w;
		m_destinationRect.x = h;
	}

	void render(SDL_Renderer* renderer, Camera* camera);
	void update(int frame);
	void setSpriteSheet(SDL_Renderer* renderer, char* path);

	std::vector<Cell> getCells(){
		return m_cells;
	}

	std::vector<Mob*> getStaticSpawns(){
		return m_staticSpawns;
	}

	void findSurroundingTiles(int x, int y, int immediateIndices[]);
	void populateLayer(int* layer, int key);
	int getCellDataFromKey(int key, int index);

	Map(Stage stage, SpawnMap spawnMap);

	static short height;
	static short width;

	inline int getCollisionLayer(int index) {
		return collisionLayer[index];
	}
	inline short getMapWidth() {
		return m_mapWidth;
	}
	inline short getMapHeight() {
		return m_mapHeight;
	}

private:
	short m_mapWidth;
	short m_mapHeight;
	Stage currentStage;
	Sprite* m_spriteSheet;
	SDL_Rect m_sourceRect;
	SDL_Rect m_destinationRect;
	std::vector<Cell> m_cells;
	std::vector<Cell> animatedCells;
	std::vector<Mob*> m_staticSpawns;

	std::vector<int*>* layers;
	int* groundLayer;
	int* animationLayer;
	int* objectLayer;
	int* collisionLayer;

};
#endif