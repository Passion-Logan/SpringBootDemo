package demoT;

/**
 * @File Name: demoT
 * @Author: WQL //作者及
 * @Date: 2019/7/3 11:06//完成日期
 * @Description: // 有界类型参数
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class MaximumTest
{

    public static void main( String args[] )
    {
        System.out.printf( "%d, %d 和 %d 中最大的数为 %d\n\n",
                3, 4, 5, maximum( 3, 4, 5 ) );

        System.out.printf( "%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n",
                6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ) );

        System.out.printf( "%s, %s 和 %s 中最大的数为 %s\n","pear",
                "apple", "orange", maximum( "pear", "apple", "orange" ) );
    }

    /**
     * 有界的类型参数:
     *
     *可能有时候，你会想限制那些被允许传递到一个类型参数的类型种类范围。例如，
     *一个操作数字的方法可能只希望接受Number或者Number子类的实例。这就是有界类型参数的目的。
     *
     *要声明一个有界的类型参数，首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界。
     *
     *实例
     *下面的例子演示了"extends"如何使用在一般意义上的意思"extends"（类）或者"implements"（接口）。
     * 该例子中的泛型方法返回三个可比较对象的最大值。
     *
     * @param x
     * @param y
     * @param z
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> T maximum(T x, T y, T z)
    {
        // 假设x是初始最大值
        T max = x;

        if (y.compareTo(max) > 0)
        {
            max = y;
        }
        if (z.compareTo(max) > 0)
        {
            max = z;
        }

        return max;
    }

}