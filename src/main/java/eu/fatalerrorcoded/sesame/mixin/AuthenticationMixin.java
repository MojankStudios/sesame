package eu.fatalerrorcoded.sesame.mixin;

import com.mojang.authlib.Environment;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(YggdrasilAuthenticationService.class)
public class AuthenticationMixin {
	@Inject(method = "determineEnvironment", at = @At("HEAD"), cancellable = true, remap = false)
	private static void customEnvironment(CallbackInfoReturnable<Environment> cir) {
		String basePath = "https://yggoxide.insert.moe";
		cir.setReturnValue(Environment.create(basePath, basePath, basePath, basePath, "PROD"));
	}
}
