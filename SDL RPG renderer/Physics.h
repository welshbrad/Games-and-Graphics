#ifndef PHYSICS_H
#define PHYSICS_H

#include <SDL2\SDL.h>


namespace Physics{

	extern bool rectCollision(const SDL_Rect &rect1, const SDL_Rect &rect2);

}
#endif