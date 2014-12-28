package chylex.hee.block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import chylex.hee.HardcoreEnderExpansion;
import chylex.hee.tileentity.TileEntityAbstractTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockAbstractTable extends BlockAbstractInventory{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop, iconSide, iconBottom;
	
	public BlockAbstractTable(){
		super(Material.rock);
		setHardness(4F);
		setResistance(2000F);
	}
	
	protected abstract int getGuiID();
	
	@Override
	public final boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		player.openGui(HardcoreEnderExpansion.instance,getGuiID(),world,x,y,z);
		return true;
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int direction){
		TileEntity tile = world.getTileEntity(x,y,z);
		return tile == null || ((TileEntityAbstractTable)tile).isWorking() ? 0 : 15;
	}
	
	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int direction){
		return isProvidingWeakPower(world,x,y,z,direction);
	}
	
	@Override
	public boolean canProvidePower(){
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final IIcon getIcon(int side, int meta){
		return side == 0 ? iconBottom : side == 1 ? iconTop : iconSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void registerBlockIcons(IIconRegister iconRegister){
		iconTop = iconRegister.registerIcon("hardcoreenderexpansion:"+textureName+"_top");
		iconSide = iconRegister.registerIcon("hardcoreenderexpansion:"+textureName+"_side");
		iconBottom = iconRegister.registerIcon("hardcoreenderexpansion:table_bottom");
	}
}
