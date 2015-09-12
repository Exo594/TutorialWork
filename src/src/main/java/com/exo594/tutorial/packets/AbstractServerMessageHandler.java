/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exo594.tutorial.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

/*
 * 
 * This is just a convenience class that will prevent the client-side message handling
 * method from appearing in our server message handler classes.
 *
 */
public abstract class AbstractServerMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {
// implementing a final version of the client message handler both prevents it from
// appearing automatically and prevents us from ever accidentally overriding it

    @Override
    public final IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx) {
        return null;
    }
}
