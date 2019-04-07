#include "Keyboard.h"

void processKeyEvents(SDL_Event* event){

		switch (event->type) {
		case SDL_MOUSEBUTTONDOWN:
			if (event->button.button == SDL_BUTTON_RIGHT){
				RIGHT_CLICK_PRESSED = true;
			}
			if (event->button.button == SDL_BUTTON_LEFT){
				LEFT_CLICK_PRESSED = true;
			}
			break;
		case SDL_MOUSEBUTTONUP:
			if (event->button.button == SDL_BUTTON_RIGHT){
				RIGHT_CLICK_PRESSED = false;
			}
			if (event->button.button == SDL_BUTTON_LEFT){
				LEFT_CLICK_PRESSED = false;
			}
			break;
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
				break;
			case SDLK_KP_ENTER:
				ENTER_KEY_PRESSED = true;
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
				break;
			case SDLK_RETURN:
				ENTER_KEY_PRESSED = false;
				ENTER_KEY_RELEASED = true;
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
