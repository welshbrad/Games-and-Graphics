#include "Physics.h"

#include <iostream>

namespace Physics{

	bool rectCollision(const SDL_Rect &rect1, const SDL_Rect &rect2){
		if (rect1.x < rect2.x && (rect1.x + rect2.w) > rect2.x &&
			rect1.y < (rect2.y + rect2.h) && rect1.y + rect1.h > rect2.y) {
			return false;
		}
		return true;
	}
}