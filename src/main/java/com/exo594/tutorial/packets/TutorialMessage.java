package com.exo594.tutorial.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class TutorialMessage implements IMessage {

    public int extremelyImportantInteger;

    public TutorialMessage() {
    }

    public TutorialMessage(int a) {
        this.extremelyImportantInteger = a;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(extremelyImportantInteger);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.extremelyImportantInteger = buf.readInt();
    }

}
