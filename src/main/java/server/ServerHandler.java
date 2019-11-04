package server;

import decode.Decoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import util.DataUtil;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    Timer time = new Timer() ;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        time.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                                System.out.println(new Date() + ": 发出数据");

        ByteBuf buffer = getByteBuf(ctx);

        ctx.channel().writeAndFlush(buffer);
                    }
                },100,2000
        );

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到数据");
        ByteBuf byteBuf = (ByteBuf)msg;
//        判断是否是数据msg
        byteBuf.markReaderIndex();
        int lenData = byteBuf.readableBytes();

        byte[] dataBytes = new byte[lenData];
        byteBuf.readBytes(dataBytes);
        String rawData = DataUtil.encode(dataBytes);
        byteBuf.resetReaderIndex();
        //加上长度判断，防止沾包
        if (!rawData.startsWith("40")&&lenData==9) {
            Decoder.decode(ctx, byteBuf);
        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {

        String str = "010302000002C5B3";


        byte[] bytes = DataUtil.deocde(str);

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }
}
