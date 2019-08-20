package algorithm.sort;

import java.util.Arrays;

/**
 * @File Name: algorithm.sort
 * @Author: WQL //作者及
 * @Date: 2019/8/20 18:05//完成日期
 * @Description: // 二分法排序
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class TwoPoints
{
    public static void main(String[] args)
    {
        int[] arr = new int[]{1,6,3,5,2};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(binarySort(arr)));
    }

    /**
     *  二分排序
     *  也称折半插入排序，查找次数为O(n log n)，移动次数为O(n^2)
     * @param a
     * @return
     */
    public static int[] binarySort(int[] a) {
        int i, j;
        int low, high, mid;
        int temp;

        for (i = 1; i < a.length; i++) {
            temp = a[i];
            low = 0;
            high = i - 1;
            while (low <= high) {
                mid = (low + high) / 2;
                if (a[mid] > temp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            for (j = i - 1; j > high; j--) {
                a[j + 1] = a[j];
            }
            a[high + 1] = temp;
        }

        return a;
    }
}
