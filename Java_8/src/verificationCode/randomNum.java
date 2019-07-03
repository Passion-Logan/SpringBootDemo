package verificationCode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @File Name: verificationCode
 * @Author: WQL //作者及
 * @Date: 2019/7/2 9:42//完成日期
 * @Description: // 生成随机四位数数字验证码
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class randomNum
{

    public static void main(String[] args)
    {

        StringBuffer code = new StringBuffer(4);

        ThreadLocalRandom.current().ints(0, 10).distinct().limit(4).forEach(x -> code.append(x));

        System.out.println(code);
    }

}