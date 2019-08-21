package algorithm.sort;

import java.util.Arrays;

/**
 * @File Name: algorithm.sort
 * @Author: WQL //作者及
 * @Date: 2019/8/20 20:03//完成日期
 * @Description: // 快速排序
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Fast
{

    public static void main(String[] args)
    {
        int[] arr = new int[]{3,1,6,5,2};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(quickSort(arr)));
    }

    public static int[] quickSort(int[] a) {
        if (a.length > 0) {
            quickSortRecursion(a,0, a.length - 1);
        }
        return a;
    }

    public static void quickSortRecursion(int[] data, int low, int high) {
        if (low < high) {
            int middle = partition(data, low, high);
            quickSortRecursion(data, low, middle - 1);
            quickSortRecursion(data, middle + 1, high);
        }
    }

    public static int partition(int[] data, int low, int high) {
        int temp = data[low];
        while (low < high) {
            while (low < high && data[high] >= temp) {
                high--;
            }
            // 比中轴小的记录移到低端
            data[low] = data[high];
            while (low < high && data[low] <= temp) {
                low++;
            }
            // 比中轴大的记录移到高端
            data[high] = data[low];
        }
        data[low] = temp;

        // 返回中轴位置
        return low;
    }
}
