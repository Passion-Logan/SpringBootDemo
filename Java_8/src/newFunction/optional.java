package newFunction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @File Name: newFunction
 * @Author: WQL //作者及
 * @Date: 2019/6/25 15:49//完成日期
 * @Description: // 生成六位随机数验证码
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class optional
{
    public static void main(String[] args) {

        Set<String> store = getletterandnum(5);

        printSet(store);

    }

    public static Set<String> getletterandnum(int length) {

        Set<String> set =new HashSet<>();

        for (int i = 0; i < length; i++) {

            String value = getrandom();

            set.add(value);
        }

        //如果没有生成6位
        if (set.size()<length) {

            //继续调用生成随机数的方法
            String value = getrandom();

            set.add(value);

        }

        return  set;
    }

    /**
     * 生成随机字母和数字方法
     *
     * @return
     */
    private static String getrandom() {
        String value = "";
        Random random = new Random();

        //0、1、2
        int gen = random.nextInt(2);

        String charornum = gen % 2 == 0 ? "char" : "num";

        if ("char".equals(charornum)) {

            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;

            int ascii = random.nextInt(26);

            value += (char) (ascii + temp);

        } else if ("num".equalsIgnoreCase(charornum)) {

            value += String.valueOf(random.nextInt(10));
        }
        return value;
    }

    /**
     *  打印set的方法
     *
     * @param set
     */
    public static  void printSet(Set set){
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String ele = (String) iterator.next();
            System.out.print(ele+" ");
        }
    }
}