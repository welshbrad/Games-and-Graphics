#ifndef KEYBOARD_H
#define KEYBOARD_H

#include <SDL2\SDL.h>
#include "main.h"

void processKeyEvents(SDL_Event* event);
void resetKeyboard();

#endif