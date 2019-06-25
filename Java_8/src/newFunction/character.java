package newFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @File Name: newFunction
 * @Author: WQL //作者及
 * @Date: 2019/6/24 13:54//完成日期
 * @Description: // Predicate <T> 函数式接口
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class character
{

    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        eval(list, x -> x > 3);
    }

    /**
     * Predicate <T> 接口是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果。
     *
     * @param list
     * @param predicate
     */
    public static void eval(List<Integer> list, Predicate<Integer> predicate)
    {
        // 写法一
        for (Integer n : list)
        {
            if (predicate.test(n))
            {
                System.out.println(n);
            }
        }
        // 写法二
        list.stream().filter(predicate).forEach(System.out::println);
        // 写法三
        list.stream().filter(n ->  n > 3).forEach(System.out::println);
    }

}