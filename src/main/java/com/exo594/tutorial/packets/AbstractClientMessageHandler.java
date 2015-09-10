package com.exo594.tutorial.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractClientMessageHandler<T extends IMessage> extends AbstractMessageHandler<T>{
    // implementing a final version of the server message handler both prevents it from
// appearing automatically and prevents us from ever accidentally overriding it
    @Override
public final IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx) {
return null;
}
}
