#include "Camera.h"

#include "Global.h"

void initCamera(Camera* camera, Entity* entity){
	camera->px = 0;
	camera->py = 0;
	camera->rot = 0;
	camera->focus = entity;

	m_yQueue = 0;
	m_xQueue = 0;
}

void updateCamera(Camera* cam){
	cam->px = cam->focus->getPX();
	cam->py = cam->focus->getPY();
}

int getXMoveQueue(){
	return m_yQueue;
}

int getYMoveQueue(){
	return m_yQueue;
}
