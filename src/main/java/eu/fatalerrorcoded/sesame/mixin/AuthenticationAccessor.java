package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

@Mixin(YggdrasilAuthenticationService.class)
public interface AuthenticationAccessor {
    @Accessor(value = "clientToken", remap = false)
    @Mutable
    public void setClientToken(String clientToken);
}
