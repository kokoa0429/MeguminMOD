package com.google.kobatokokoa0429.Item;

import com.google.kobatokokoa0429.MeguminMOD;
import net.minecraft.item.Item;

public class ItemWandCore extends Item {

	/**変更・クリエイティブタブの設定をここに変更。
	 * 変更・setTextureNameの引数のドメイン部分をModIDに*/
	public ItemWandCore() {
		
		setUnlocalizedName("itemWandCore");
		setTextureName(MeguminMOD.MOD_ID+":tueue");
		setCreativeTab(MeguminMOD.tabMeguminMod);
		setMaxStackSize(16);
    }
}
