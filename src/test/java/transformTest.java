import io.netty.buffer.ByteBuf;

import java.text.DecimalFormat;
import java.util.Scanner;

public class transformTest {
    public static void main(String[] args) {
        String string ="017D";//输入十六进制数

         byte[] bytes = string.getBytes();

        int sum=0;
        for(int i=0;i<string.length();i++)
        {
            int m=string.charAt(i);//将输入的十六进制字符串转化为单个字符
            int num=m>='A'?m-'A'+10:m-'0';//将字符对应的ASCII值转为数值
            sum+=Math.pow(16, string.length()-1-i)*num;
        }

        DecimalFormat df=new DecimalFormat("0.0");//设置保留位数

        String result = df.format((float)sum/10);

        System.out.println(result);

    }
}
