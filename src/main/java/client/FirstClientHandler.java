package client;

import decode.Decoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Timeout;
import util.DataUtil;
import util.StringUtil;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:23.
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("客户端收到数据");
        ByteBuf byteBuf = (ByteBuf)msg;

        Decoder.decode(ctx,byteBuf);

        ByteBuf response = getByteBuf(ctx);
        ctx.channel().writeAndFlush(response);

    }





    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {

        String str = "0103040116013F5B8B";



        byte[] bytes = DataUtil.deocde(str);

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }
}
