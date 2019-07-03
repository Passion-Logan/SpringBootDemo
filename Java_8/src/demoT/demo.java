package demoT;

/**
 * @File Name: demoT
 * @Author: WQL //作者及
 * @Date: 2019/7/3 10:43//完成日期
 * @Description: // 泛型 无界类型参数
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class demo
{

    public static void main(String[] args)
    {
        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println( "整型数组元素为:" );
        printArray( intArray  );

        System.out.println( "\n双精度型数组元素为:" );
        printArray( doubleArray );

        System.out.println( "\n字符型数组元素为:" );
        printArray( charArray );
    }

    public static <E> void printArray(E[] inputArray)
    {
        for (E element : inputArray)
        {
            System.out.printf( "%s ", element );
        }
        System.out.println();
    }

}