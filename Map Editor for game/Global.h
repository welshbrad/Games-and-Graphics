#pragma once
#ifndef GLOBAL_H
#define GLOBAL_H
//--------------------------------
#define DEBUG_ON
//--------------------------------

#define WATER_SPRITE (0)
#define GRASS_SPRITE 1
#define DIRT_SPRITE 2
#define SAND_SPRITE 3

#define MAX_SPRITES 4

#include <stdio.h>
#include <iostream>
#include <string.h>
#include <math.h>



	extern void globalInit();
	//Keyboard

	extern bool RIGHT_CLICK_PRESSED;
	extern bool LEFT_CLICK_PRESSED;

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

	extern bool ENTER_KEY_RELEASED;	
	extern bool ENTER_KEY_PRESSED;




	
#endif