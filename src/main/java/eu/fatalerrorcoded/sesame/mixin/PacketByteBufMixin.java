package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import eu.fatalerrorcoded.sesame.util.Constants;
import net.minecraft.network.PacketByteBuf;

@Mixin(PacketByteBuf.class)
public class PacketByteBufMixin {
	@ModifyArg(
		method = "readGameProfile",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/network/PacketByteBuf;readString(I)Ljava/lang/String;"
		),
		index = 0
	)
	private int maxReadUsernameLength(int x) {
		return Constants.MAX_USERNAME_LENGTH;
	}
}
