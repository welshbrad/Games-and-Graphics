#ifndef MAP_H
#define MAP_H

#define ANIMATION_SPEED 10

#include <vector>
#include <SDL2\SDL.h>
#include "main.h"
#include "Global.h"

class Display;
class Sprite;

struct camera{
	int x;
	int y;
};

struct cursor{
	int x;
	int y;
};

class Map{

public:
	void placeTile();
	void loapMap(char* path);
	void loadStaticSpawns(char* path);

	void renderRect(SDL_Renderer* renderer);

	typedef struct {
		int x;
		int y;
		int z;
		int r;
		int spriteKey;
		int currentFrame;
	} Cell;

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

	void render(SDL_Renderer* renderer);
	void update(int frame);
	void setSpriteSheet(SDL_Renderer* renderer, char* path);

	inline const enum Stage {
		MAIN_TEST_STAGE,
		MAIN_TEST_STAGE2
	};

	std::vector<Cell> getCells(){
		return m_cells;
	}


	Map(Stage stage);


private:
	Sprite* m_spriteSheet;
	SDL_Rect m_sourceRect;
	SDL_Rect m_destinationRect;
	std::vector<Cell> m_cells;
	std::vector<Cell> animatedCells;
	camera m_camera;
	SDL_Rect m_cursorRect;
	cursor* m_cursor;
	bool m_placed;
	int delay;
	int keydelay;
	int placeDelay;
	int m_currentSpriteID;
	int m_gridx, m_gridy;
	int placex, placey;
};
#endif