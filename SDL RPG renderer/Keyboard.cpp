#include "Keyboard.h"

void processKeyEvents(SDL_Event* event){
		switch (event->type) {
		case SDL_KEYDOWN:
			switch (event->key.keysym.sym) {
			case SDLK_BACKSPACE:
				BACKSPACE_PRESSED = true;
				break;
			case SDLK_ESCAPE:
				//
				break;
			case SDLK_SPACE:
				SPACE_KEY_PRESSED = true;
				break;

			case SDLK_w:
				W_KEY_PRESSED = true;
				break;
			case SDLK_a:
				A_KEY_PRESSED = true;
				break;
			case SDLK_s:
				S_KEY_PRESSED = true;
				break;
			case SDLK_d:
				D_KEY_PRESSED = true;
				break;

			case SDLK_DOWN:
				DOWN_KEY_PRESSED = true;
				break;
			case SDLK_RIGHT:
				RIGHT_KEY_PRESSED = true;
				break;
			case SDLK_LEFT:
				LEFT_KEY_PRESSED = true;
				break;
			case SDLK_UP:
				UP_KEY_PRESSED = true;
				break;
			case SDLK_LSHIFT:
				SHIFT_KEY_PRESSED = true;
			case SDLK_KP_ENTER:
				//
				break;
			}
			break;
		case SDL_KEYUP:
			switch (event->key.keysym.sym) {
			case SDLK_BACKSPACE:
				BACKSPACE_PRESSED = false;
				break;
			case SDLK_ESCAPE:
				//
				break;
			case SDLK_SPACE:
				SPACE_KEY_PRESSED = false;
				break;

			case SDLK_w:
				W_KEY_PRESSED = false;
				break;
			case SDLK_a:
				A_KEY_PRESSED = false;
				break;
			case SDLK_s:
				S_KEY_PRESSED = false;
				break;
			case SDLK_d:
				D_KEY_PRESSED = false;
				break;

			case SDLK_DOWN:
				DOWN_KEY_PRESSED = false;
				break;
			case SDLK_RIGHT:
				RIGHT_KEY_PRESSED = false;
				break;
			case SDLK_LEFT:
				LEFT_KEY_PRESSED = false;
				break;
			case SDLK_UP:
				UP_KEY_PRESSED = false;
				break;
			case SDLK_LSHIFT:
				SHIFT_KEY_PRESSED = false;
			case SDLK_KP_ENTER:
				//
				break;
			}

			break;
		}
	
}
void resetKeyboard(){
	DOWN_KEY_PRESSED = false;
	UP_KEY_PRESSED = false;
	LEFT_KEY_PRESSED = false;
	RIGHT_KEY_PRESSED = false;
	W_KEY_PRESSED = false;
	S_KEY_PRESSED = false;
	A_KEY_PRESSED = false;
	D_KEY_PRESSED = false;
	BACKSPACE_PRESSED = false;
	SPACE_KEY_PRESSED = false;
	SHIFT_KEY_PRESSED = false;
}
