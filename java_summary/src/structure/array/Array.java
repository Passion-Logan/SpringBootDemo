package structure.array;

/**
 * @File Name: structure.array
 * @Author: WQL //作者及
 * @Date: 2019/8/23 15:37//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Array
{
    private int[] intArray;
    private int length;
    private int elems;

    public static void main(String[] args)
    {
        Array array = new Array(3);
        array.add(6);
        array.add(3);
        array.add(7);

        array.display();
    }

    public Array(int max) {
        length = max;
        intArray = new int[max];
        elems = 0;
    }

    /**
     * 添加
     * @param value
     */
    public void add(int value) {
        if (elems == length) {
            System.out.println("error");
            return;
        }
        intArray[elems] = value;
        elems++;
    }

    /**
     * 查找
     * @param searchKey
     * @return
     */
    public int find(int searchKey) {
        int i;
        for (i = 0; i < elems; i++) {
            if (intArray[i] == searchKey) {
                break;
            }
        }
        if (i == elems) {
            return -1;
        }
        return i;
    }

    /**
     * 更新(修改)
     * @param oldValue
     * @param newValue
     * @return
     */
    public boolean update(int oldValue, int newValue) {
        int i = find(oldValue);
        if (i == -1) {
            return false;
        }
        intArray[i] = newValue;
        return true;
    }

    /**
     * 删除
     * @param value
     * @return
     */
    public boolean delete(int value) {
        int i = find(value);
        if (i == -1) {
            return false;
        }
        for (int j = i; i < elems - 1; j++) {
            // 后面的数据往前移
            intArray[j] = intArray[j + 1];
        }
        elems--;
        return true;
    }

    /**
     * 显示所有
     */
    public void display() {
        for (int i = 0; i < elems; i++) {
            System.out.println(intArray[i] + " ");
        }
        System.out.println("\n");
    }
}
