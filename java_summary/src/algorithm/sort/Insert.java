package algorithm.sort;

import java.util.Arrays;

/**
 * @File Name: algorithm.sort
 * @Author: WQL //作者及
 * @Date: 2019/8/20 17:15//完成日期
 * @Description: // 插入排序
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Insert
{

    public static void main(String[] args)
    {
        int[] arr = new int[]{1,6,3,5,2};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(insertSort(arr)));
    }

    /**
     * 插入排序
     * 把n个待排序元素看成一个有序表和一个无序表
     * 开始时有序表只有一个元素，无序表有n-1个元素
     * 排序过程即每次从无序表中取出第一个元素，将它插入有序表中
     * 使之成为新的有序表，重复n-1次完成整个排序过程
     * @param a
     * @return
     */
    public static int[] insertSort(int[] a) {
        for (int i  = 1; i < a.length; i++) {
            int temp = a[i];
            int j = i;
            while (j > 0 && temp < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
        return a;
    }
}
