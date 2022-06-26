package eu.fatalerrorcoded.sesame.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.fatalerrorcoded.sesame.util.AuthServer;
import net.minecraft.client.main.Main;

@Mixin(Main.class)
public class ClientMainMixin {
	@Inject(method = "main", at = @At("HEAD"), remap = false)
	private static void main(String[] args, CallbackInfo ci) {
		boolean found = false;
		String username = "";
		for (String arg : args) {
			if (found) {
				username = arg;
				break;
			} else {
				if (arg.equals("--username") || arg.equals("-username")) {
					found = true;
				}
			}
		}
		
		if (username != null) {
			if (username.contains("@")) {
				String[] segments = username.split("@");
				AuthServer.targetUsername = username.replace("@", "..");
				AuthServer.targetAuthServer = "https://" + segments[1];
				AuthServer.auth();
			}
		}
	}
}
