package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;

@Mixin(LoginHelloC2SPacket.class)
public class LoginHelloC2SPacketMixin {
	@ModifyArg(
		method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/network/PacketByteBuf;readString(I)Ljava/lang/String;"
		),
		index = 0
	)
	private static int maxReadUsernameLength(int x) {
		return 32;
	}

	@ModifyArg(
		method = "write",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/network/PacketByteBuf;writeString(Ljava/lang/String;I)Lnet/minecraft/network/PacketByteBuf;"
		),
		index = 1
	)
	private int maxWriteUsernameLength(int maxLength) {
		return 32;
	}
}
