package eu.fatalerrorcoded.sesame.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;

@Mixin(LoginHelloC2SPacket.class)
public interface LoginHelloC2SPacketAccessor {
    @Accessor(value = "name")
    public void setName(String name);

    @Accessor(value = "publicKey")
    public void setPublicKey(Optional<PlayerPublicKey.PublicKeyData> publicKey);
}
