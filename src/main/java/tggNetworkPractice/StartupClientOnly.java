package tggNetworkPractice;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;

/**
 * User: The Grey Ghost Date: 24/12/2014
 *
 * The Startup classes for this example are called during startup, in the
 * following order: preInitCommon preInitClientOnly initCommon initClientOnly
 * postInitCommon postInitClientOnly See MinecraftByExample class for more
 * information
 */
public class StartupClientOnly {

    public static void preInitClientOnly() {
    // Client-side message handler must be registered in clientproxy only, unless you are very careful to keep all
        //   client-side-only code out of your client-side message handler.
        //  See further discussion in StartupCommon.
        StartupCommon.simpleNetworkWrapper.registerMessage(MessageHandlerOnClient.class, TargetEffectMessageToClient.class,
                StartupCommon.TARGET_EFFECT_MESSAGE_ID, Side.CLIENT);
    }

    public static void initClientOnly() {
        /*// required in order for the renderer to know how to render your item.  Likely to change in the near future.
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("minecraftbyexample:mbe60_item_airstrike", "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(StartupCommon.itemAirStrike, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
        */
    }

    public static void postInitClientOnly() {
    }
}
