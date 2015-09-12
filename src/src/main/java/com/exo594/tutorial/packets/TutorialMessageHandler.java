package com.exo594.tutorial.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class TutorialMessageHandler implements IMessageHandler<TutorialMessage, IMessage> { 

    @Override
    public IMessage onMessage(TutorialMessage message, MessageContext ctx) { 
        System.out.println(message.extremelyImportantInteger);
        return null;
    }

}
