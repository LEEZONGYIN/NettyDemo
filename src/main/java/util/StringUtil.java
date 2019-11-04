package util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class StringUtil {
    public static ByteBuf String2ByteBuf(String s, ChannelHandlerContext ctx){
        ByteBuf byteBuf = ctx.alloc().ioBuffer();
        byte[] bytes = s.getBytes();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public static String ByteBuf2String(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String res = new String(bytes);
        return res;
    }
}
