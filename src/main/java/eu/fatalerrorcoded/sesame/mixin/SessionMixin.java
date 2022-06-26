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
        if (AuthServer.targetAccessToken != null) {
            cir.setReturnValue(AuthServer.targetAccessToken);
        } else {
            cir.setReturnValue(cir.getReturnValue());
        }
    }
}
