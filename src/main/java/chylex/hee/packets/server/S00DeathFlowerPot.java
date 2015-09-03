package chylex.hee.packets.server;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import chylex.hee.init.BlockList;
import chylex.hee.packets.AbstractServerPacket;
import chylex.hee.system.abstractions.Pos;
import io.netty.buffer.ByteBuf;

public class S00DeathFlowerPot extends AbstractServerPacket{
	private Pos pos;
	
	public S00DeathFlowerPot(){}
	
	public S00DeathFlowerPot(Pos pos){
		this.pos = pos.immutable();
	}
	
	@Override
	public void write(ByteBuf buffer){
		buffer.writeLong(pos.toLong());
	}

	@Override
	public void read(ByteBuf buffer){
		pos = Pos.at(buffer.readLong());
	}

	@Override
	protected void handle(EntityPlayerMP player){
		if (pos.checkBlock(player.worldObj,Blocks.flower_pot,0)){
			ItemStack is = player.inventory.getCurrentItem();
			
			if (is != null && is.getItem() == Item.getItemFromBlock(BlockList.death_flower)){
				if (!player.capabilities.isCreativeMode)--is.stackSize;
				pos.setBlock(player.worldObj,BlockList.death_flower_pot,is.getItemDamage());
			}
		}
	}
}
