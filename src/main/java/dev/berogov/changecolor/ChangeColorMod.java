package dev.berogov.changecolor;


import dev.berogov.changecolor.core.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(ChangeColorMod.MOD_ID)
public class ChangeColorMod {
    public static final String MOD_ID = "changecolor";

    public  ChangeColorMod() {
        Registration.init();
        MinecraftForge.EVENT_BUS.register(this);
    }
}