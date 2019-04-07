#include "Player.h"
#include "Global.h"
#include "main.h"
#include "Display.h"

Player::Player(){
	m_currentSprite = new Sprite();
	m_inv = new Inventory();
}

void Player::init(SDL_DisplayMode dm, Map* map){
	m_inv = new Inventory();
	m_map = map;
	
	mapIndex = 0;

	m_lastGridx = 0;
	m_lastGridy = 0;

	m_px = 0;
	m_py = 0;
	health = 1000;

	m_boundingRect.h = STANDARD_SPRITE_SIZE;
	m_boundingRect.w = STANDARD_SPRITE_SIZE;
	m_boundingRect.x = dm.w / 2 - m_boundingRect.w / 2;
	m_boundingRect.y = dm.h / 2 - m_boundingRect.h / 2;

	textureSourceRect.h = STANDARD_SPRITE_SIZE;
	textureSourceRect.w = STANDARD_SPRITE_SIZE;
	textureSourceRect.x = 0;
	textureSourceRect.y = 0;

	m_gridx = (int) floor(m_boundingRect.x / TILE_SIZE);
	m_gridy = (int) floor(m_boundingRect.y / TILE_SIZE);

	currentFrame = 0;
	m_walking = false;

	m_world_ptr = World::getInstance();
}

Player::~Player(){
	delete m_currentSprite;
}

void Player::render(Display display, SDL_Rect dest, SDL_Texture* tex, SDL_Rect src){
	SDL_SetRenderTarget(display.getSDLRenderer(), tex);
	SDL_RenderCopy(display.getSDLRenderer(), tex, &src, &dest);
}

//TODO: Implement xy, wh bounding rectangle
void Player::setSprite(SDL_Renderer* renderer, char* path, int x, int y, int w, int h){
	if (x == NULL || y == NULL){
		m_currentSprite = new Sprite(renderer, path);
	}
	else {
		m_currentSprite = new Sprite(renderer, path, x, y, w, h);
	}
}

void Player::calculateGrid(){
	m_lastGridx = m_gridx;
	m_lastGridy = m_gridy;

	m_gridx = (int) (floor((m_boundingRect.x - m_px) / TILE_SIZE));
	m_gridy = (int) (floor((m_boundingRect.y - m_py) / TILE_SIZE));
}

void Player::update(int frame){
	m_hud->throwPlayerUpdate(m_inv);
	m_world_ptr->getItemVec();
	calculateGrid();
	animate(frame);
	takeDamage(10);

	if (health <= 0){
		
		//Remove
		//<---------------->
		/*
		cout << "Inventory contains: " << m_inv->getFilledSlots() << "Items" << endl;
		m_inv->addItem(Item("Dick", 12, 12));
		m_inv->addItem(Item("Ass", 13, 1));
		m_inv->addItem(Item("Tits", 14, 12));
		cout << "Inventory contains: " << m_inv->getFilledSlots() << "Items" << endl;

		//<--------------->
		*/
		runDeath();
		assert (getHealth() > 0);
	}

	for (int i = 0; i < 16; i++) {
		if (m_inv->checkSlot(i)) {
			//m_inv->addItem(Item("Gold", 10, 1));
		}
		else {
		//	cout << "Slot full: " << i << endl;
		}
	}
}

void Player::getActiveTile(){
	calculateGrid();
	mapIndex = m_map->getIndexFromCoords(m_gridx, m_gridy);
	//if (m_lastGridx != m_gridx || m_lastGridy != m_gridy){
		m_map->findSurroundingTiles(m_gridx, m_gridy, m_immediateIndices);
	//}
}

void Player::runDeath(){
	alive = false;
	currentFrame = 0;
	m_px = 0;
	m_py = 0;
	setHealth(getStatMax(0)); //Get max Health
	m_hud->setHP(getHealth(), getStatMax(0));
	m_hud->resetHealthBar();
	alive = true;
}


void Player::takeDamage(float amount){
	health -= amount;
	m_hud->setHP(getHealth(), getStatMax(0));
}

void Player::animate(int worldFrame){
	if (m_walking){
		if (worldFrame % ANIMATION_SPEED == 0){
			currentFrame++;
			if (currentFrame >= spritesOnSheet){
				currentFrame = 0;
			}
		}
	}
	else{
		currentFrame = 0;
	}
	textureSourceRect.x = m_boundingRect.w * currentFrame;
}

int Player::collided() {
	int index = -1;
	getActiveTile();
	bool directionsColliding[4] = { 0,0,0,0};

	for (int i = 0; i < 4; ++i) {
		index = m_immediateIndices[i];
		//returns 1 for collision, 0 for no collision
		if (m_map->getCollisionLayer(index)) {
			SDL_Rect tileRect = SDL_Rect();
			tileRect.x = index % m_map->getMapWidth();
			tileRect.y = (index - tileRect.x) / m_map->getMapHeight();
			tileRect.w = TILE_SIZE;
			tileRect.h = TILE_SIZE;

			SDL_Rect modifiedPlayerBounds = getBoundingRect();

			if (Physics::rectCollision(tileRect, modifiedPlayerBounds)) {
				directionsColliding[i] = true;
				return true;
			}
		}
				//cout << "No Collision" << endl;
	}
	//uint8_t returnKey = 0;
	return false;
	//if (directionsColliding[0] || directionsColliding[1]) {
	//	returnKey += 1; //x movement
	//}
	//if (directionsColliding[2] || directionsColliding[3]) {
	//	returnKey += 2; //y movement
	//}
	//return returnKey;
}


void Player::getInput(){
	/* Move into animation function */
	textureSourceRect.y = 0;


	m_walking = false;

	old_px = m_px;
	old_py = m_py;
	
	if (DOWN_KEY_PRESSED || S_KEY_PRESSED){
		m_walking = true;
		m_py -= walkSpeed;
		if (collided()) {
			m_walking = false;
			m_py = old_py;
		}
	}
	if (UP_KEY_PRESSED || W_KEY_PRESSED){		
		m_walking = true;
		textureSourceRect.y = textureSourceRect.y = 32;;
		m_py += walkSpeed;
		if (collided()) {
			m_walking = false;
			m_py = old_py;
		}
	}
	if (LEFT_KEY_PRESSED || A_KEY_PRESSED){
		m_walking = true;
		textureSourceRect.y = TILE_SIZE * 3;
		m_px += walkSpeed;
		if (collided()) {
			m_walking = false;
			m_px = old_px;
		}
	}
	if (RIGHT_KEY_PRESSED || D_KEY_PRESSED) {
		m_walking = true;
		textureSourceRect.y = 2 * TILE_SIZE;
		m_px -= walkSpeed;
		if (collided()) {
			m_walking = false;
			m_px = old_px;
		}
	}


}
