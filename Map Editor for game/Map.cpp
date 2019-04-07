#include "Map.h"
#include "File.h"
#include <algorithm>
#include <math.h>

#define TILE_SIZE 32
#define SCROLL_SPEED 4

Map::Map(Stage stage){
	m_placed = false;
	placex = 0;
	placey = 0;
	m_gridx = 0;
	m_gridy = 0;

	delay = 0;
	keydelay = 0;
	placeDelay = 0;
	m_cursor = new cursor();
	m_cursor->x = 0;
	m_cursor->y = 0;

	m_currentSpriteID = 0;

	m_cursorRect.x = TILE_SIZE;
	m_cursorRect.y = TILE_SIZE;
	m_cursorRect.w = TILE_SIZE;
	m_cursorRect.h = TILE_SIZE;

	m_camera = camera();
	m_camera.x = 0;
	m_camera.y = 0;
	m_sourceRect.x = 0;
	m_sourceRect.y = 0;
	m_sourceRect.h = TILE_SIZE;
	m_sourceRect.w = TILE_SIZE;

	m_destinationRect.x = 0;
	m_destinationRect.y = 0;
	m_destinationRect.h = TILE_SIZE;
	m_destinationRect.w = TILE_SIZE;

	char* stagePath = "";

	if (stage == Stage::MAIN_TEST_STAGE){
		stagePath = "../res/stages/MAIN_TEST_MAP.stg";
	}
	loapMap(stagePath);

	for (int i = 0; i < (int)m_cells.size(); i++){
		if (m_cells.at(i).currentFrame != -1){
			animatedCells.push_back(m_cells.at(i));
		}
	}
}


void Map::setSpriteSheet(SDL_Renderer* renderer, char* path){
	m_spriteSheet = new Sprite(renderer, path);
}

void Map::render(SDL_Renderer* renderer){
	SDL_SetRenderTarget(renderer, m_spriteSheet->getTexture());
	for (int i = 0; i < (int) m_cells.size(); i++){
		m_destinationRect.x = m_cells.at(i).x * (TILE_SIZE) + m_camera.x;
		m_destinationRect.y = m_cells.at(i).y * (TILE_SIZE) + m_camera.y;
		m_sourceRect.y = m_cells.at(i).spriteKey * TILE_SIZE;
		if (m_cells.at(i).currentFrame != -1){
			m_sourceRect.x = (m_cells.at(i).currentFrame) * TILE_SIZE;
		}
		else {
			m_sourceRect.x = 0;
		}
		SDL_RenderCopy(renderer, m_spriteSheet->getTexture(), &m_sourceRect, &m_destinationRect);
	}
	renderRect(renderer);
}

void Map::renderRect(SDL_Renderer* renderer){
	SDL_SetRenderDrawColor(renderer, 0, 0, 0, 255);
	SDL_RenderFillRect(renderer, &m_cursorRect);
	SDL_SetRenderDrawColor(renderer, 0x64, 0x85, 0x93, 0x00);

}

void Map::update(int frame){
	SDL_GetMouseState(&m_cursor->x, &m_cursor->y);

	if (S_KEY_PRESSED && delay >= 8){
		m_camera.y -= TILE_SIZE;
		m_gridy = (-m_camera.y) / TILE_SIZE;
		delay = 0;
	}
	if (W_KEY_PRESSED && delay >= 8){
		m_camera.y += TILE_SIZE;
		m_gridy = (-m_camera.y) / TILE_SIZE;
		delay = 0;
	}
	if (A_KEY_PRESSED && delay >= 8){
		m_camera.x += TILE_SIZE;
		m_gridx = (-m_camera.x) / TILE_SIZE;
		delay = 0;
	}
	if (D_KEY_PRESSED && delay >= 8){
		m_camera.x -= TILE_SIZE;
		m_gridx = (-m_camera.x) / TILE_SIZE;
		delay = 0;
	}
	delay++;

	if (ENTER_KEY_RELEASED){
		File::saveMap("../res/stages/MAIN_TEST_MAP.stg", &m_cells);
		cout << "Here" << endl;
		ENTER_KEY_RELEASED = false;
	}

	//keybinds
	m_cursorRect.x = m_cursor->x - m_cursor->x % TILE_SIZE;
	m_cursorRect.y = m_cursor->y - m_cursor->y % TILE_SIZE;

	if (LEFT_KEY_PRESSED && keydelay >= 16) {
		if (m_currentSpriteID >= 1){
			m_currentSpriteID--;
			cout << "Current Sprite: " << m_currentSpriteID << endl;
		}
		keydelay = 0;
	}
	if (RIGHT_KEY_PRESSED && keydelay >= 16){
		if (m_currentSpriteID < MAX_SPRITES){
			m_currentSpriteID++;
			cout << "Current Sprite: " << m_currentSpriteID << endl;
		}
		keydelay = 0;
	}
	keydelay++;
	if (LEFT_CLICK_PRESSED) {
		placex = m_gridx + (m_cursorRect.x / TILE_SIZE);
		placey = m_gridy + (m_cursorRect.y / TILE_SIZE);
		//printf("Gridx: %d, Gridy: %d\n", m_gridx, m_gridy);
		for (int i = 0; i < (int)m_cells.size(); i++){
			if (m_cells.at(i).x == placex && m_cells.at(i).y == placey){
				if (m_cells.at(i).spriteKey == m_currentSpriteID){
					//printf("This tile already contains sprite: %d\n", m_currentSpriteID);
					m_placed = true;
					break;
				}
				else{
					placeTile();
				}
			}
		}
		if (!m_placed){
			placeTile();	
		}
		m_placed = false;

	}
	//animate tiles
	for (int i = 0; i < (int)m_cells.size(); i++){
		if (m_cells.at(i).currentFrame != -1){
			if (frame % ANIMATION_SPEED == 0){
				m_cells.at(i).currentFrame++;
			}
			if (m_cells.at(i).currentFrame >= 8){
				m_cells.at(i).currentFrame = 0;
			}
		}
	}
}

using std::swap;

void Map::placeTile(){
	for (int i = 0; i < m_cells.size(); i++){
		if (m_cells.at(i).x == placex && m_cells.at(i).y == placey){
			m_cells.at(i) = m_cells.back();
			m_cells.pop_back();
		}
	}
	int currFrame = -1;
	if (m_currentSpriteID == 0){
		currFrame = 0;
	}
	Map::Cell cell = { placex, placey, 0, 0, m_currentSpriteID, currFrame };
	m_cells.push_back(cell);
	printf("Placed a tile at [%d, %d] with sprite: %d\n", placex, placey, m_currentSpriteID);
}

void Map::loapMap(char* path){
	File::loadMap(path, &m_cells);
}

