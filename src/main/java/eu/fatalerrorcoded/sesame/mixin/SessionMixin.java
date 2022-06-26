package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.fatalerrorcoded.sesame.util.AuthServer;
import net.minecraft.client.util.Session;

@Pseudo
@Mixin(Session.class)
public class SessionMixin {
    @Inject(method = "getAccessToken()Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    private void injectAccessToken(CallbackInfoReturnable<String> cir) {
        if (AuthServer.activeAccessToken != null) {
            cir.setReturnValue(AuthServer.activeAccessToken);
        } else {
            cir.setReturnValue(cir.getReturnValue());
        }
    }

    @Inject(method = "getUsername()Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    private void injectUsername(CallbackInfoReturnable<String> cir) {
        if (AuthServer.activeUsername != null) {
            cir.setReturnValue(AuthServer.activeUsername);
        } else {
            cir.setReturnValue(cir.getReturnValue());
        }
    }

    @Inject(method = "getUuid()Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    private void injectUuid(CallbackInfoReturnable<String> cir) {
        if (AuthServer.activeUuid != null) {
            cir.setReturnValue(AuthServer.activeUuid);
        } else {
            cir.setReturnValue(cir.getReturnValue());
        }
    }
}
