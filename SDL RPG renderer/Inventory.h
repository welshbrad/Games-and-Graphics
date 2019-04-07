#pragma once
#include "Item.h"
#include <vector>
#include <memory>
#include <array>


#define TOTAL_INVENTORY_SLOTS 16


class Inventory {
public:
	Inventory();
	inline int getTotalSlots() {
		return TOTAL_INVENTORY_SLOTS;
	}

	int getFilledSlots() {
		return m_filledSlots;
	}

	void emptyInventory();
	bool hasRoom();

	bool addItem(Item item);

	//returns true if the slot is available, otherwise false
	bool checkSlot(int slot);

	Item* getSlotItem(int slotNum);

private:
	int m_filledSlots;
	std::array<Item*, TOTAL_INVENTORY_SLOTS> m_inventory;
};