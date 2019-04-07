#include "Map.h"
#include "File.h"

Map::Map(Stage stage, SpawnMap spawnMap){
	layers = new std::vector<int*>;

	m_mapHeight = -1;
	m_mapWidth = -1;

	m_sourceRect.x = 0;
	m_sourceRect.y = 0;
	m_sourceRect.h = TILE_SIZE;
	m_sourceRect.w = TILE_SIZE;

	m_destinationRect.x = 0;
	m_destinationRect.y = 0;
	m_destinationRect.h = TILE_SIZE;
	m_destinationRect.w = TILE_SIZE;

	loapMap(getStagePath(stage));
	optimizeMapStructure();

	loadStaticSpawns(getSpawnMapPath(spawnMap));
}

char* Map::getStagePath(Map::Stage stage) {
	char* stagePath = "";
	if (stage == Stage::MAIN_TEST_STAGE) {
		stagePath = "../res/stages/MAIN_TEST_MAP.stg";
	}
	return stagePath;
}

char* Map::getSpawnMapPath(Map::SpawnMap spawnMap) {
	char* spawnMapPath = "";
	if (spawnMap == SpawnMap::MAIN_TEST_STAGE_SPAWN1) {
		spawnMapPath = "../res/stages/MAIN_TEST_SPAWN1.stg";
	}
	return spawnMapPath;
}

/* 
Given a map coordinate (in tiles, not pixels)
Cycle through all map cells, if any cell at the index matches the given coordinates, 
point the immediate cell to that m_cells Cell. Return the cell.
*/
int Map::getIndexFromCoords(int x, int y){
	return y * m_mapWidth + x;
	/* legacy code */
	/*
	vector<Map::Cell>::iterator it = std::find_if(m_cells.begin(), m_cells.end(), [&](const Cell cell) {
		return (cell.x == x && cell.y == y);
	});
	if (it == m_cells.end()) {
		cerr << "Player went off the grid." << endl;
		return nullptr;
	}
	m_immediateTile = &(*it);
	return &(*it);
	*/
	// ^^^ separate version
	/*
	for (int i = 0; i < (int) m_cells.size(); i++){
		if (m_cells.at(i).x == x && m_cells.at(i).y == y) {
			m_immediateTile = &m_cells.at(i);
			if (m_immediateTile->x != NULL && m_immediateTile->y != NULL){
				return m_immediateTile;
			}
			else{
				return nullptr;
			}
		}
	}
	return nullptr;
	*/
}

void Map::findSurroundingTiles(int x, int y, int immediateIndices[]){
	/* COUT */
	int indexTL = -1;
	int indexTR = -1;
	int indexBL = -1;
	int indexBR = -1;

	//cout << x << ", "<< y << endl;
	int index = getIndexFromCoords(x,y);
	
	//if (x != 0)
		indexTL = index;
	//if (x != m_mapWidth)
		indexTR = index + 1;
	//if (y != 0)
		indexBL = index + m_mapWidth;
	//if (y != m_mapHeight)
		indexBR = index + m_mapWidth + 1;

	immediateIndices[0] = indexTL;
	immediateIndices[1] = indexTR;
	immediateIndices[2] = indexBL;
	immediateIndices[3] = indexBR;
	
	/* legacy code */
	/*
	for (int i = 0; i < (int) m_cells.size(); i++){
		if (m_cells.at(i).x == (x + 1) && m_cells.at(i).y == (y)){
			m_immediateCells->at(0) = &m_cells.at(i);
		}
		if (m_cells.at(i).x == (x - 1) && m_cells.at(i).y == (y)){
			m_immediateCells->at(1) = &m_cells.at(i);
		}
		if (m_cells.at(i).y == (y + 1) && m_cells.at(i).x == (x)){
			m_immediateCells->at(2) = &m_cells.at(i);
		}
		if (m_cells.at(i).y == (y - 1) && m_cells.at(i).x == (x)){
			m_immediateCells->at(3) = &m_cells.at(i);
		}
	}*/
}

void Map::loadEntities(std::vector<Entity*>* entities, Display* display){
	Entity* eCast;
	for (int i = 0; i < (int) getStaticSpawns().size(); i++){
		eCast = getStaticSpawns().at(i);
		entities->push_back(eCast);
	}
}

void Map::setSpriteSheet(SDL_Renderer* renderer, char* path){
	m_spriteSheet = new Sprite(renderer, path);
}

void Map::render(SDL_Renderer* renderer, Camera* camera){
	SDL_SetRenderTarget(renderer, m_spriteSheet->getTexture());
	int index = 0;
	for (int row = 0; row < m_mapHeight; ++row) {
		for (int col = 0; col < m_mapWidth; ++col) {
			index = row * m_mapWidth + col;
			m_destinationRect.x = (col) * (TILE_SIZE) + camera->px;
			m_destinationRect.y = (row) * (TILE_SIZE) + camera->py;
			m_sourceRect.y = groundLayer[index] * TILE_SIZE;
			
			if (animationLayer[index] != -1) {
				m_sourceRect.x = (animationLayer[index]) * TILE_SIZE;
			}
			else {
				m_sourceRect.x = 0;
			}
			SDL_RenderCopy(renderer, m_spriteSheet->getTexture(), &m_sourceRect, &m_destinationRect);
		}
	}
	
	/*
	for (int i = 0; i < m_mapWidth * m_mapHeight; ++i) {

		m_destinationRect.x = (i % m_mapWidth) * (TILE_SIZE) + camera->px;
		m_destinationRect.y = (i % m_mapHeight) * (TILE_SIZE) + camera->py;
		m_sourceRect.y = groundLayer[i] * TILE_SIZE;

		//
		//handle animation layer here
		m_sourceRect.x = 0;
		//


		SDL_RenderCopy(renderer, m_spriteSheet->getTexture(), &m_sourceRect, &m_destinationRect);
	}
	*/

	/* legacy - -for use with vector map instead of layer system */
	/*
	for (int i = 0; i < (int)m_cells.size(); i++){
		m_destinationRect.x = m_cells.at(i).x * (TILE_SIZE)+camera->px;
		m_destinationRect.y = m_cells.at(i).y * (TILE_SIZE)+camera->py;
		m_sourceRect.y = m_cells.at(i).spriteKey * TILE_SIZE;
		if (m_cells.at(i).currentFrame != -1){
			m_sourceRect.x = (m_cells.at(i).currentFrame) * TILE_SIZE;
		}
		else {
			m_sourceRect.x = 0;
		}
		SDL_RenderCopy(renderer, m_spriteSheet->getTexture(), &m_sourceRect, &m_destinationRect);
	}*/
	
}

void Map::update(int frame){
	//animate tiles

	for (int i = 0; i < m_mapHeight * m_mapWidth; ++i) {
		if (animationLayer[i] != -1) {
			if (frame % ANIMATION_SPEED == 0) {
				animationLayer[i]++;
			}
			if (animationLayer[i] >= 8) {
				animationLayer[i] = 0;
			}
		}
	}

	/*legacy code */
	/*
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
	*/
}

void Map::loapMap(char* path){
	File::loadMap(path, m_cells, &m_mapWidth, &m_mapHeight);
}


void Map::loadStaticSpawns(char* path){
	File::loadMobConfig(path, &m_staticSpawns);
}


void Map::optimizeMapStructure() {

	/*		organize into this structure
	------------------------------------------------------------------------------
	(0, 1)(0, 2)(0, 3)...(0, height)					= vector.at(0)
	(1, 1)(1, 2)(1, 3)...(1, height)					= vector.at(1)
	.													.
	.													.
	.													.
	(Width, 1)(width, 2)(width,3)...(width,height)		= vector.at(width * height)
	-------------------------------------------------------------------------------
	Then we use this structure to create layer arrays to optimize the map updating and rendering.
	
	*/

	sort(m_cells.begin()+1, m_cells.end(), [](const Cell& cell1, const Cell& cell2) {
		if (cell1.y < cell2.y)
			return true;
		if (cell1.y > cell2.y)
			return false;
		if (cell1.x < cell2.x)
			return true;
		if (cell1.x > cell2.x)
			return false;
		return (cell1.z < cell2.z);
	});	

	groundLayer = new int[m_mapWidth * m_mapHeight];
	animationLayer = new int[m_mapWidth * m_mapHeight];
	collisionLayer = new int[m_mapWidth * m_mapHeight];
	populateLayer(groundLayer, 0);
	populateLayer(animationLayer, 1); 	
	populateLayer(collisionLayer, 2);

	/*legacy code*/
	/*
	groundLayer = new int[m_mapWidth * m_mapHeight]; //value  at (x,y) will indicate map sprite	
	int index = 0;
	for (int row = 0; row < m_mapWidth; ++row) {
		for (int col = 0; col < m_mapHeight; ++col) {
			index = row * m_mapHeight + col;
			groundLayer[index] = m_cells.at(index+1).spriteKey; 
		}
	}

	layers->push_back(groundLayer);
	*/
	/*
	index = 0;
	for (int row = 0; row < m_mapWidth; ++row) {
		for (int col = 0; col < m_mapHeight; ++col) {
			index = row * m_mapHeight + col;
			animationLayer[index] = m_cells.at(index + 1).currentFrame;  
		}
	}
	layers->push_back(animationLayer);
	*/
}

//layer to be filled and value to be stored at each index. e.g. The animation layer will store key frames
void Map::populateLayer(int* layer, int key) {
	int index = 0;
	for (int row = 0; row < m_mapWidth; ++row) {
		for (int col = 0; col < m_mapHeight; ++col) {
			index = row * m_mapHeight + col;
			layer[index] = getCellDataFromKey(key, index);
		}
	}
	layers->push_back(layer);
}

int Map::getCellDataFromKey(int key, int index) {
	int dataKey = -1;
	index++;
	switch (key) {
		case 0: dataKey = m_cells.at(index).spriteKey; break;		//groundLevel
		case 1: dataKey = m_cells.at(index).currentFrame; break;	//animationLevel
		case 2: dataKey = m_cells.at(index).z; break;				//collisionLevel
		case 3: dataKey = m_cells.at(index).r; break;				//Not yet implemented
		default: dataKey = -1; break;
	} 
	return dataKey;
}
