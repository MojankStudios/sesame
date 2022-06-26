package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;

@Pseudo
@Mixin(YggdrasilMinecraftSessionService.class)
public class YggdrasilMinecraftSessionServiceMixin {
    @ModifyVariable(method = "getTextures", at = @At(value = "HEAD"), index = 2, argsOnly = true, remap = false)
    private boolean disableTextureSignatureCheck(boolean requireSecure) {
        return false;
    }

    @Inject(method = "isAllowedTextureDomain", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void replaceTextureDomainCheck(String url, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
