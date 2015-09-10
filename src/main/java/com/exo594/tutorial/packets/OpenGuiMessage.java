
package com.exo594.tutorial.packets;
// sent using the following code from KeyInputEvent: PacketDispatcher.sendToServer(new OpenGuiMessage(TutorialMain.GUI_CUSTOM_INV));

import com.exo594.tutorial.Main;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;


/**
 * If you are going with the more simple route, the only difference is your inner handler class signature
 * will look like this:
 *
 * public static class Handler implements IMessageHandler<OpenGuiMessage, IMessage>
 *
 * and the only method will be:
 *
 * public IMessage onMessage(OpenGuiMessage, MessageContext)
 *
 * Note that you can't immediately tell what side you are supposed to be on just from looking at the method,
 * and you have to do some work to get the player if you want it. But we fixed all that xD
 */
public class OpenGuiMessage implements IMessage {
 // this will store the id of the gui to open
 private int id;

 // The basic, no-argument constructor MUST be included to use the new automated handling
 public OpenGuiMessage() {}

 // if there are any class fields, be sure to provide a constructor that allows
 // for them to be initialized, and use that constructor when sending the packet
 public OpenGuiMessage(int id) {
 this.id = id;
 }

 @Override
 public void fromBytes(ByteBuf buffer) {
 // basic Input/Output operations, very much like DataInputStream
 this.id = buffer.readInt();
 }

 @Override
 public void toBytes(ByteBuf buffer) {
 // basic Input/Output operations, very much like DataOutputStream
 buffer.writeInt(id);
 }
 
public static class OpenGuiMessageHandler extends AbstractServerMessageHandler<OpenGuiMessage> {
 @Override
 public IMessage handleServerMessage(EntityPlayer player, OpenGuiMessage message, 
MessageContext ctx) {
 // because we sent the gui's id with the packet, we can handle all cases with one line:
 player.openGui(Main.modInstance, message.id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
 return null;
 }
 }
}