#ifndef FILE_H
#define FILE_H
#include <fstream>
#include <vector>
#include <iostream>
#include <string>
#include <sstream>
#include "Map.h"

class Map;

using namespace std;
namespace File{
	extern void loadMap(char* path, vector<Map::Cell>* cells);
	extern void saveMap(char* path, vector<Map::Cell>* cells);
}
#endif