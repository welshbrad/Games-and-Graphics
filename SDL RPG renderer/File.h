#ifndef FILE_H
#define FILE_H
#include <fstream>
#include <vector>
#include <iostream>
#include <string>
#include <sstream>
#include "Map.h"
#include "Mob.h"


namespace File{
	extern void loadMap(char* path, vector<Map::Cell>& cells, short *width, short* height);
	extern void loadMobConfig(char* path, vector<Mob*>* spawns);
	extern void loadPlayer(char* path, vector<float>* stats);
}
#endif