package com.google.kobatokokoa0429.Item;

import com.google.kobatokokoa0429.MeguminMOD;

import net.minecraft.item.Item;

public class ItemWandStick extends Item {

	/**
	 * 変更・クリエイティブタブの設定をここに変更。 変更・setTextureNameの引数のドメイン部分をModIDに
	 */
	public ItemWandStick() {
		setUnlocalizedName("itemWandStick");
		setTextureName(MeguminMOD.MOD_ID + ":tuesutekki");
		setCreativeTab(MeguminMOD.tabMeguminMod);
		setMaxStackSize(1);

	}

}
