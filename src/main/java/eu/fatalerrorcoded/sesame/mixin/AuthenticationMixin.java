package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.authlib.Environment;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import eu.fatalerrorcoded.sesame.util.AuthServer;

@Mixin(YggdrasilAuthenticationService.class)
public class AuthenticationMixin {
	@Inject(method = "determineEnvironment", at = @At("HEAD"), cancellable = true, remap = false)
	private static void customEnvironment(CallbackInfoReturnable<Environment> cir) {
		String authServer = AuthServer.targetAuthServer;

		if (authServer != null) {
			cir.setReturnValue(Environment.create(authServer, authServer, authServer, authServer, "PROD"));
		}
	}
}
