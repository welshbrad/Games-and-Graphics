#ifndef CAMERA_H
#define CAMERA_H

#include "Global.h"

class Entity;

struct Camera {
	int px;
	int py;
	float rot;
	Entity* focus;
};

void initCamera(Camera* camera, Entity* entity);
int getXMoveQueue();
int getYMoveQueue();

void updateCamera(Camera* camera);
	
static int m_yQueue;
static int m_xQueue;


#endif