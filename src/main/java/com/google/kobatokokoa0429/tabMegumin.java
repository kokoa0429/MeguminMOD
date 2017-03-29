package com.google.kobatokokoa0429;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class tabMegumin extends CreativeTabs {

	public tabMegumin() {
		super("meguminMOD");
	}

	@Override
	public Item getTabIconItem() {

		return MeguminMOD.itemWand;
	}

}
