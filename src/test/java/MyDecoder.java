

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName nettySimulator.MyDecoder
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/9/11 10:06
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public class MyDecoder extends ByteToMessageDecoder {
    /**
     * 自定义解码器
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        byteBuf.markReaderIndex();//标记当前readIndex位置
        int dataLength = byteBuf.readInt();       // 读取传送过来的消息的长度。ByteBuf 的readInt()方法会让readIndex增加4
        if (dataLength < 0) { //消息体长度为0，关闭连接。
            channelHandlerContext.close();
        }

        if (byteBuf.readableBytes() < dataLength) { //读到的消息体长度小于传送过来的消息长度，则resetReaderIndex. 配合markReaderIndex把readIndex重置到mark的地方
            byteBuf.resetReaderIndex();
            return;
        }

        int dataType = byteBuf.readInt();
        System.out.println(dataType);

        byte[] body = new byte[dataLength];  // 读到长度，满足要求，取出传送的数据
        byteBuf.readBytes(body);  //

//        byte[] temp = new byte[4];
//        byteBuf.readBytes(temp,0,4);
//        int i = temp[3] & 0xFF |
//                (temp[2] & 0xFF) << 8 |
//                (temp[1] & 0xFF) << 16 |
//                (temp[0] & 0xFF) << 24;
//
//        System.out.println(i);

        Object o = new String(body);  //序列化
        list.add(o);
    }
}