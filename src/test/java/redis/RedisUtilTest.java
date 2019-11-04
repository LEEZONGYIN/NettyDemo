package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import util.SerializeUtil;

import static org.junit.Assert.*;

public class RedisUtilTest {
    RedisUtil redisUtil = new RedisUtil();

    SerializeUtil serializeUtil = new SerializeUtil();

    @Test
    public void get() {

        System.out.println( redisUtil.get("product4"));
    }

    @Test
    public void set(){
        redisUtil.set("testList","test1");
    }

   @Test
    public void serialisTest() {
       String s = "21.8";
       byte[] bytes = serializeUtil.serialize(s);

       System.out.println(bytes);
       System.out.println(serializeUtil.unserialize(bytes));
   }

   @Test
   public void testLen(){
       System.out.println(redisUtil.getLen());
   }

   @Test
    public void testPop(){
       System.out.println(redisUtil.popData());
   }
}