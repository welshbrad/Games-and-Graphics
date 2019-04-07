#include "File.h"
using namespace std;
namespace File{
	/*
	Line 1: Height of map
	Line 2: Width of map
	Line 3-end: Each line represents a Map Cell struct

	*********Map MUST be rectangular and start at 0 ******

	Each Cell is pushed into a std::vector<Map::Cell> at an index determined by the Height and Width.
	*/
	void loadMap(char* path, vector<Map::Cell>& cells, short *width, short* height){
		std::string lineData;	//line data string (will be fed into linestream to place into variables)
		
		ifstream mapFile;		
		mapFile.open(path);
		if (!mapFile.is_open()){
			std::cout << "Unable to open file: " << path << endl;
			return;
		}

		//Read W and H
		mapFile >> *width;
		mapFile >> *height;

		while (getline(mapFile, lineData)) {

			stringstream linestream(lineData);

			int x, y, z;
			int spriteKey;
			int rotation; //degrees (limit use to 90, 180, 270)
			int currentFrame;

			getline(linestream, lineData, '\t'); 
			linestream >> x >> y >> z >> rotation >> spriteKey >> currentFrame;
			if (currentFrame > 0){ currentFrame = 0; }
			Map::Cell cell = { x, y, z, rotation, spriteKey, currentFrame};
			cells.push_back(cell);
		}
		cout << "Map width: " << *width << " Map height: " << *height << endl;
		mapFile.close();
	}

	void loadMobConfig(char* path, vector<Mob*>* spawns){
		std::string lineData;

		ifstream mobSpawns;
		mobSpawns.open(path);
		if (!mobSpawns.is_open()){
			std::cout << "Unable to open file: " << path << endl;
			return;
		}

		while (getline(mobSpawns, lineData)) {
			stringstream linestream(lineData);
			int mobId, x, y, z;
			int rotation; //degrees (limit use to 1,2,3,4: Facing me, Facing left, Facing INTO screen, Facing Right
			std::string activeScript = "";

			getline(linestream, lineData, '\t');
			linestream >> mobId >> x >> y >> z >> rotation >> activeScript;

			Mob* mobSpawn = new Mob(mobId, x, y, z, rotation, activeScript);
			mobSpawn->init();
			spawns->push_back(mobSpawn);

		}
		mobSpawns.close();
	}

	//Huge TODO: implement stat loading and parsing
	void loadPlayer(char* path, vector<float>* stats){
		printf("Player loaded");
	}
}
	
