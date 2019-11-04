package decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;
import redis.RedisUtil;
import util.DataUtil;

import java.text.DecimalFormat;
import java.util.Date;


public class Decoder {




    public static void decode(ChannelHandlerContext ctx, ByteBuf byteBuf){

        RedisUtil redisUtil = new RedisUtil();

        byteBuf.markReaderIndex();
        int lenData = byteBuf.readableBytes();
        byte[] dataBytes = new byte[lenData];
        byteBuf.readBytes(dataBytes);
        String rawData = DataUtil.encode(dataBytes);

        System.out.println("源数据 "+ rawData);
        byteBuf.resetReaderIndex();


        System.out.println("开始解码");
        //跳过地址和功能码
        byteBuf.skipBytes(2);

        //读取返回数据长度
        byte[] lenBytes = new byte[1];
        byteBuf.readBytes(lenBytes);
        String lenStr = DataUtil.encode(lenBytes);
        //得到数据个数
        int len = Integer.parseInt(lenStr,16)/2;


        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String dataStr = DataUtil.encode(bytes);

        DecimalFormat df=new DecimalFormat("0.0");//设置保留位数

        //温度
        String temperaturStr = dataStr.substring(0,4);
        int temperaturInt = Integer.parseInt(temperaturStr,16);
        String temperatur = df.format((float)temperaturInt/10);
        //湿度
        String humilityStr = dataStr.substring(4,8);
        int humilityInt = Integer.parseInt(humilityStr,16);
        String humility = df.format((float)humilityInt/10);

        ctx.writeAndFlush(bytes);

        System.out.println("温度 "+temperatur);
        System.out.println("湿度 "+humility);


        String result = rawData+" "+temperatur+" "+humility;

        Long dataLen = redisUtil.getLen();
        System.out.println("dataLen "+dataLen);

        if (dataLen>=500) {
            redisUtil.popData();
            redisUtil.set("testList", result);
        }else {
            redisUtil.set("testList", result);
        }
//        redisUtil.set("testList", result);


    }
}
