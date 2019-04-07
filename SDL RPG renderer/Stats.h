#ifndef STATS_H
#define STATS_H

#include "File.h"

#define HEALTH 0

using namespace std;

class Stats {
public:
	Stats();
	~Stats();

	inline float getMax(int skill){
		switch (skill){
		case HEALTH: return m_health;	break;
		default: return -1.0;
		}
	}
	void loadStats(char* path);
	void init();
	void update(int frame);

private:
	vector<float>* m_stats;
	float m_health;

};
#endif //STATS_H