package chylex.hee.mechanics.compendium.util;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import chylex.hee.item.ItemList;
import chylex.hee.item.ItemSpawnEggs;
import chylex.hee.mechanics.compendium.content.KnowledgeObject;
import chylex.hee.mechanics.compendium.events.CompendiumEvents;
import chylex.hee.mechanics.compendium.objects.IKnowledgeObjectInstance;
import chylex.hee.mechanics.compendium.objects.ObjectItem;
import chylex.hee.mechanics.compendium.objects.ObjectMob;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;

public final class KnowledgeUtils{
	public static KnowledgeObject<? extends IKnowledgeObjectInstance<?>> tryGetFromItemStack(ItemStack is){
		UniqueIdentifier uniqueId = null;
		
		try{
			uniqueId = GameRegistry.findUniqueIdentifierFor(is.getItem());
		}
		catch(Exception e){} // protection against idiots who can't register their shit properly
		
		if (uniqueId != null && uniqueId.modId.equalsIgnoreCase("hardcoreenderexpansion")){
			KnowledgeObject<? extends IKnowledgeObjectInstance<?>> obj = null;
			
			if (is.getItem() == ItemList.spawn_eggs){
				Class<? extends EntityLiving> entity = ItemSpawnEggs.getMobFromDamage(is.getItemDamage());
				if (entity == null)entity = (Class<? extends EntityLiving>)EntityList.IDtoClassMapping.get(is.getItemDamage());
				return entity == null ? null : KnowledgeObject.<ObjectMob>getObject(entity);
			}
			else if (is.getItem() instanceof ItemBlock)return CompendiumEvents.getBlockObject(is);
			else return KnowledgeObject.<ObjectItem>getObject(is.getItem());
		}
		
		return null;
	}
	
	private KnowledgeUtils(){}
}