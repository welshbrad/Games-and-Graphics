#include "Stats.h"


Stats::Stats(){

}

void Stats::init(){
	m_health = 0;
	
}

void Stats::update(int frame){

}

void Stats::loadStats(char* path){
	File::loadPlayer(path, m_stats);
}

Stats::~Stats(){

}