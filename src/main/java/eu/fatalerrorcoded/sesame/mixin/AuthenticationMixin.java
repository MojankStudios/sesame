package eu.fatalerrorcoded.sesame.mixin;

import java.net.Proxy;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
			// Overwrite Yggdrasil's configuration
			cir.setReturnValue(Environment.create(authServer, authServer, authServer, authServer, "PROD"));
		}
	}

	@Inject(method = "<init>(Ljava/net/Proxy;Ljava/lang/String;Lcom/mojang/authlib/Environment;)V", at = @At("TAIL"), remap = false)
	public void YggdrasilAuthenticationService(final Proxy proxy, final String clientToken, final Environment environment, CallbackInfo ci) {
		if (AuthServer.targetClientToken != null) {
			((AuthenticationAccessor) this).setClientToken(AuthServer.targetClientToken);
		}
	}
}
