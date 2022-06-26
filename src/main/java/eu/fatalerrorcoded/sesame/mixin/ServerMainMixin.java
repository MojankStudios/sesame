package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.fatalerrorcoded.sesame.util.AuthServer;
import net.minecraft.client.main.Main;

@Mixin(Main.class)
public class ServerMainMixin {
	@Inject(method = "main", at = @At("HEAD"), remap = false)
	private static void main(String[] args, CallbackInfo ci) {
		AuthServer.targetAuthServer = "https://yggoxide.insert.moe";
	}
}
