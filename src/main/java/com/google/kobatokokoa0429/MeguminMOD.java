package com.google.kobatokokoa0429;

import com.google.kobatokokoa0429.Block.BlockManatite;
import com.google.kobatokokoa0429.Block.BlockWand;
import com.google.kobatokokoa0429.Entity.EntityBarret;
import com.google.kobatokokoa0429.GUI.GuiHandler;
import com.google.kobatokokoa0429.Handler.ManataitoOreGenerator;
import com.google.kobatokokoa0429.Handler.MeguminEntityPropertiesEventHandler;
import com.google.kobatokokoa0429.Item.ItemManatite;
import com.google.kobatokokoa0429.Item.ItemWand;
import com.google.kobatokokoa0429.Item.ItemWandCore;
import com.google.kobatokokoa0429.Item.ItemWandStick;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = MeguminMOD.MOD_ID, version = MeguminMOD.version, useMetadata = true)

public class MeguminMOD {
	/**
	 * 変更・modIDを大文字に変更。 変更・バージョンを変数に。 追加・ResourceLocation用のドメイン
	 */
	public static final String MOD_ID = "MeguminMod";
	public static final String version = "1.0.0";
	public static final String Domein = "meguminmod";

	/**
	 * 削除・meguminmodtab.java 変更・変数名 変更・独自クラスのインスタンスから元クラスのインスタンスに
	 */
	public static final CreativeTabs tabMeguminMod = new CreativeTabs("meguminMOD") {
		@Override
		public Item getTabIconItem() {
			return itemWand;
		}
	};

	/** Stringを書くのではなくModIDを入れる。 */
	@Mod.Instance(MOD_ID)
	public static MeguminMOD INSTANCE;

	public static final int GUI_ID = 1;

	/**
	 * 変更・変数名をわかりやすく 変更・setCreativeTabを各クラス内に変更
	 */
	public static Block blockTue = new BlockWand();
	public static Block blockMatatite = new BlockManatite();
	public static Item itemManatite = new ItemManatite();
	public static Item itemWandStick = new ItemWandStick();
	public static Item itemWandCore = new ItemWandCore();

	public static Item itemWand;// = new ItemWand(manataito);

	@SidedProxy(clientSide = "com.google.kobatokokoa0429.ClientProxy", serverSide = "com.google.kobatokokoa0429.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		// マテリアル追加（ツール）
		Item.ToolMaterial manataito = EnumHelper.addToolMaterial("manataito", 0, 2000, 5.0F, 5996.0F, 10);

		itemWand = (new ItemWand(manataito));

		// Blockの登録
		GameRegistry.registerBlock(blockTue, "blockTue");
		GameRegistry.registerBlock(blockMatatite, "blockMatatite");
		// Itemの登録
		GameRegistry.registerItem(itemWand, "itemWand");
		GameRegistry.registerItem(itemManatite, "itemManatite");
		GameRegistry.registerItem(itemWandStick, "itemWandStick");
		GameRegistry.registerItem(itemWandCore, "itemWandCore");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// エンティティの登録
		EntityRegistry.registerModEntity(EntityBarret.class, "villagerCannon.villagerHead", 170, this, 128, 5, true);

		// 鉱石生成
		GameRegistry.registerWorldGenerator(new ManataitoOreGenerator(), 0);

		// レシピ登録
		GameRegistry.addShapelessRecipe(new ItemStack(MeguminMOD.itemWand), MeguminMOD.itemWandStick,
				MeguminMOD.itemWandCore);
		GameRegistry.addRecipe(new ItemStack(MeguminMOD.itemWandStick), " XY", "XYX", "YX ", 'Y', Items.stick, 'X',
				Items.string);
		GameRegistry.addRecipe(new ItemStack(MeguminMOD.itemWandStick), " XY", "XYX", "YX ", 'Y', Items.stick, 'X',
				Items.string);
		GameRegistry.addRecipe(new ItemStack(MeguminMOD.itemWandCore), "XXX", " ZY", "XXY", 'X', Items.stick, 'Y',
				Items.gold_ingot, 'Z', MeguminMOD.itemManatite);

		// TileEntityの登録
		proxy.registerTileEntity();

		// GUIの登録
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		// イベントハンドラの登録
		MeguminEntityPropertiesEventHandler MeguminEntityPropertiesEventHandler = new MeguminEntityPropertiesEventHandler();
		MinecraftForge.EVENT_BUS.register(MeguminEntityPropertiesEventHandler);
		FMLCommonHandler.instance().bus().register(MeguminEntityPropertiesEventHandler);
	}

}