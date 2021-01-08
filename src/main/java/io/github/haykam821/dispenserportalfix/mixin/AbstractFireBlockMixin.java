package io.github.haykam821.dispenserportalfix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
	@Inject(method = "method_30033", at = @At("HEAD"), cancellable = true)
	private static void preventVerticalPortalCheck(World world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> ci) {
		// These are the two directions that do not have a case in Direction.rotateYCounterclockwise.
		// Since nether portals cannot be created when there are blocks inside of them, this should not impact vanilla behavior.
		// Because of this, the method now exits early when the direction would crash the game.
		if (direction == Direction.UP || direction == Direction.DOWN) {
			ci.setReturnValue(false);
		}
	}
}