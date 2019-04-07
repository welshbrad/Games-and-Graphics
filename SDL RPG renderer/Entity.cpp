#include "Entity.h"
#include "Camera.h"

#include "main.h"

Entity::Entity()
{
	m_px = 0;
	m_px = 0;
	m_world_ptr = World::getInstance();

}

Sprite* Entity::getSprite() const{
	return m_currentSprite;
}


Entity::~Entity()
{
}
