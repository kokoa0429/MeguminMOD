package com.google.kobatokokoa0429.Energy;



import com.google.kobatokokoa0429.TileEntity.energytile;

import net.minecraft.world.World;

public class EnergyManager{

	World par1World;
	int par2;
	int par3;
	int par4;
	energytile tile;


	public EnergyManager(World par1World, int par2, int par3, int par4) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.par1World = par1World;
		this.par2 = par2;
		this.par3 = par3;
		this.par4 = par4;
		tile = (energytile) par1World.getTileEntity(par2, par3, par4);

	}

	public int getEnergy(){



		if (!par1World.isRemote)//鯖のみ
        {
			return tile.getEnergy();

        }else{

        	return 0;

        }

	}

	public void setEnergy(int energy){


		if (!par1World.isRemote)//鯖のみ
        {

			tile.setEnergy(energy);

        }else{



        }

	}

	public void setCanhaveenergy(boolean bool){

		if (!par1World.isRemote)//鯖のみ
        {

			tile.setCanhaveenergy(bool);

        }else{

        }

	}

	public boolean getCanhameenergy(){

		if(!par1World.isRemote){

			return tile.getCanhaveenergy();

		}else{

			return false;

		}

	}

    public int getGgenerateenergy(){

    	if(!par1World.isRemote){

    		return tile.getGgenerateenergy();

    	}else{

    		return 0;

    	}

    }

    public void setGenerateenergy(int int1){

    	if(!par1World.isRemote){

    		tile.setGenerateenergy(int1);

    	}

    }

    public boolean getGenerate(){

    	if(!par1World.isRemote){

    		return tile.getGenerate();

    	}else{

    		return false;

    	}

    }

    public void setGenerate(boolean bool2){

    	if(!par1World.isRemote){

    		tile.setGenerate(bool2);

    	}


    }

}