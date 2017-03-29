package com.google.kobatokokoa0429.TileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class energytile extends TileEntity {


    private int energy = 0;

    private boolean canhaveenergy = false;

    private int generateenergy = 0;


    private boolean generate = false;

    /*
     * NBTの読み取り。
     *
     * このメソッドでは、NBTを介してString（文字列）を読み込んでいます。
     * 文字列以外に、変数やboolean、ItemStackなども扱えます。
     *
     * NBTを使えば一時的には記録されますが、
     * チャンク再生成や再ログイン時にデータが消えてしまいます。
     * また、このクラスで行われた処理・サーバ側で行われた処理をクライアント側に反映させるためには、
     * 別途パケット処理も必要です。
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.energy = par1NBTTagCompound.getInteger("energy");
        this.canhaveenergy = par1NBTTagCompound.getBoolean("canhaveenergy");
        this.generateenergy = par1NBTTagCompound.getInteger("generateenergy");
        this.generate = par1NBTTagCompound.getBoolean("generate");
    }

    /*
     * こちらはNBTを書き込むメソッド。
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("energy", this.energy);
        par1NBTTagCompound.setBoolean("canhaveenergy", this.canhaveenergy);
        par1NBTTagCompound.setInteger("generateenergy", this.generateenergy);
        par1NBTTagCompound.setBoolean("generate", this.generate);
    }

    /*
     * パケットの送信・受信処理。
     * カスタムパケットは使わず、バニラのパケット送受信処理を使用。
     */
    @Override
	public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        this.writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }

	@Override
	public void updateEntity() {

		if(this.generate){
			this.energy += this.generateenergy;
		}



	}


    public int getEnergy(){

        return this.energy;

    }

    public void setEnergy(int par1){

    	if(this.canhaveenergy){
    		this.energy = par1;
    	}

    }

    public boolean getCanhaveenergy(){

    	return this.canhaveenergy;

    }

    public void setCanhaveenergy(boolean bool1){

    	this.canhaveenergy = bool1;

    }

    public int getGgenerateenergy(){

    	return this.generateenergy;

    }

    public void setGenerateenergy(int int1){

    	this.generateenergy = int1;

    }

    public boolean getGenerate(){

    	return this.generate;

    }

    public void setGenerate(boolean bool2){

    	this.generate = bool2;

    }


    public int getMetadata()
    {
    	return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }



}
