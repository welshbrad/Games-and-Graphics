#ifndef MOB_H
#define MOB_H

#include "main.h"
#include "Entity.h"

class Mob : public Entity {

public: 

	Mob();
	Mob(int mobId, int x, int y, int z, int rot, std::string scriptPath);
	~Mob();
	void init();
	void checkCollision();
	void update(int frame);
	void getInput();
	void render(Display display, SDL_Rect dest, SDL_Texture* tex, SDL_Rect src);

	void setSprite(SDL_Renderer* renderer, char* path, int x, int y, int w, int h);

};
#endif