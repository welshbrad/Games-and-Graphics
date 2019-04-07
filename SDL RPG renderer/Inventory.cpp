#include "Inventory.h"


Inventory::Inventory() {
	m_filledSlots = 0;
}

bool Inventory::hasRoom() {
	return (m_filledSlots == TOTAL_INVENTORY_SLOTS) ? 0: 1;
}

bool Inventory::checkSlot(int slot) {
	if (slot > TOTAL_INVENTORY_SLOTS) {
		return false;
	} 
	return (m_inventory[slot] != NULL) ? 1 : 0;
}

void Inventory::emptyInventory() {
	for (int i = 0; i < TOTAL_INVENTORY_SLOTS; i++) {
		m_inventory[i] = 0;
	}
}

Item* Inventory::getSlotItem(int slotNum) {
	Item i = *m_inventory[slotNum];
	return &i;
}

bool Inventory::addItem(Item item) {
	if (!hasRoom()) {
		return false;
	}
	else {
		m_inventory[m_filledSlots] = &item;
		m_filledSlots++;
		return true;
	}

}

