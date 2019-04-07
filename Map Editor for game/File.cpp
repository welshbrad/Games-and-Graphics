#include "File.h"
#include <algorithm>
#include <assert.h>

using namespace std;
namespace File{
	void loadMap(char* path, vector<Map::Cell>* cells){
		std::string lineData;
		int lineCount = 0;

		ifstream mapFile;
		mapFile.open(path);

		if (!mapFile.is_open()){
			std::cout << "Unable to open file: " << path << endl;
			return;
		}

		while (getline(mapFile, lineData))
		{
			if (lineCount < 2) {
				lineCount++;
				continue;  }

			stringstream linestream(lineData);
			int x, y, z;
			int spriteKey;
			int rotation; //degrees (limit use to 90, 180, 270)
			int currentFrame;

			getline(linestream, lineData, '\t'); 
			linestream >> x >> y >> z >> rotation >> spriteKey >> currentFrame;

			Map::Cell cell = { x, y, z, rotation, spriteKey, currentFrame};
			cells->push_back(cell);
			lineCount++;
		}
		mapFile.close();
	}

	//modifies width and height used in save method
	void calculateWidthHeight(vector<Map::Cell>* cells, int* width, int* height, int* x_offset, int* y_offset) {


		auto x_min = std::min_element(cells->begin(), cells->end(),
			[](const Map::Cell &a, const Map::Cell &b)
		{
			return a.x < b.x;
		});
		auto y_min = std::min_element(cells->begin(), cells->end(),
			[](const Map::Cell &a, const Map::Cell &b)
		{
			return a.y < b.y;
		});

		auto y_max = std::max_element(cells->begin(), cells->end(),
			[](const Map::Cell &a, const Map::Cell &b)
		{
			return a.y < b.y;
		});

		auto x_max = std::max_element(cells->begin(), cells->end(),
			[](const Map::Cell &a, const Map::Cell &b)
		{
			return a.x < b.x;
		});

		*width = x_max->x + abs(x_min->x) * ((x_min->x < 0) ? 1 : -1) + 1;// +1;
		//assert(*width >= (x_max->x - x_min->x) && *width <= (x_max->x + x_min->x + 1));

		*height = y_max->y + abs(y_min->y) * ((y_min->y <= 0) ? 1 : -1) + 1;// +1;
		//assert(*height >= (y_max->y - y_min->y) && *height <= (y_max->y + y_min->y + 1));

		cout << *width << " " << *height << endl;

		if (x_min->x < 0) {
			*x_offset = abs(x_min->x);
		}
		else {
			*x_offset = 0;
		}

		if (y_min->y < 0) {
			*y_offset = abs(y_min->y);
		}
		else {
			*y_offset = 0;
		}
	}

	void saveMap(char* path, vector<Map::Cell>* cells){
		ofstream saveFile;
		int width, height;
		int x_offset, y_offset;
		
		calculateWidthHeight(cells, &width, &height, &x_offset, &y_offset);

		saveFile.open(path);
		saveFile << width << '\n';
		saveFile << height << '\n';

		for (int i = 0; i < cells->size(); i++){
			if (cells->at(i).spriteKey == 0) {
				cells->at(i).z = 1;
			}
			cells->at(i).x += x_offset;
			cells->at(i).y += y_offset;
			saveFile << '\t' << cells->at(i).x << '\t' << cells->at(i).y<< '\t' << cells->at(i).z << '\t' << cells->at(i).r << '\t' << cells->at(i).spriteKey << '\t' << cells->at(i).currentFrame << '\n';
		}
		saveFile.close();
	}
}
	
