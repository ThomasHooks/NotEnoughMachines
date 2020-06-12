package com.kilroy790.notenoughmachines.blocks.building;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;




public class LinenBlock extends Block {

	public LinenBlock(Properties properties) {
		super(properties);
	}

	
	
   /**
    * Block's chance to react to a living entity falling on it.
    */
	@Override
   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
      super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
   }

	
   
   /**
    * Called when an Entity lands on this Block. This method *must* update motionY because the entity will not do that
    * on its own
    */
   @Override
   public void onLanded(IBlockReader worldIn, Entity entityIn) {
      if (entityIn.isSneaking()) {
         super.onLanded(worldIn, entityIn);
      } else {
         Vec3d vec3d = entityIn.getMotion();
         if (vec3d.y < 0.0D) {
            double d0 = entityIn instanceof LivingEntity ? 1.0D : 0.8D;
            entityIn.setMotion(vec3d.x, -vec3d.y * (double)0.45F * d0, vec3d.z);
         }
      }
   }
}







