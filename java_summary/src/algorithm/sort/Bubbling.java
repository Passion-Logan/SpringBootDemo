package algorithm.sort;

import java.util.Arrays;

/**
 * @File Name: algorithm.Bubbling
 * @Author: WQL //作者及
 * @Date: 2019/8/20 15:36//完成日期
 * @Description: // 冒泡排序
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Bubbling
{
    public static void main(String[] args)
    {
        int[] arr = new int[]{1,6,3,5,2};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }

    public static void swap(int[] a, int before, int after) {
        int temp = a[before];
        a[before] = a[after];
        a[after] = temp;
    }

    /**
     * 冒泡排序(大的值从前往后冒泡)
     *  优点：稳定排序，适用于数组存储的数据和链表存储的数据
     * @param a
     * @return
     */
    public static int[] bubbleSort(int[] a) {
        for (int end = a.length - 1; end > 0; end--) {
            // 增加一个判断是否发生过交换的标记
            boolean flag = false;
            for (int j  = 0; j < end; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    flag = true;
                }
            }
            // 如果扫描一遍发现没有发生交换则说明序列已经有序，退出循环
            if (!flag) {
                break;
            }
        }
        return a;
    }

    /**
     * 冒泡排序(小的值从后往前下沉)
     *  优点：稳定排序，适用于数组存储的数据和链表存储的数据
     * @param a
     * @return
     */
    public static int[] bubbleSort2(int[] a) {
        for (int start = 0; start < a.length - 1; start++) {
            boolean flag = false;
            for (int j = a.length - 1; j > start; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j , j - 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return a;
    }
}
