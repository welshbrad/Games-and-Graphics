#pragma once
#ifndef GLOBAL_H
#define GLOBAL_H
//--------------------------------
#define DEBUG_ON
//--------------------------------
#include <stdio.h>
#include <iostream>
#include <string.h>
#include <math.h>
#include "Camera.h"
#include "Entity.h"

#define TILE_SIZE 32


	extern void globalInit();
	//Keyboard


	extern bool BACKSPACE_PRESSED;
	extern bool DOWN_KEY_PRESSED;
	extern bool UP_KEY_PRESSED;
	extern bool LEFT_KEY_PRESSED;
	extern bool RIGHT_KEY_PRESSED;
	extern bool W_KEY_PRESSED;
	extern bool S_KEY_PRESSED;
	extern bool A_KEY_PRESSED;
	extern bool D_KEY_PRESSED;

	extern bool SPACE_KEY_PRESSED;
	extern bool SHIFT_KEY_PRESSED;



	
#endif