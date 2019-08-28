package structure.stack;

import java.util.Arrays;

/**
 * @File Name: structure.stack
 * @Author: WQL //作者及
 * @Date: 2019/8/28 10:42//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Stack
{

    /**
     * 基于数组实现的序列栈，连续存储的线性实现，需要初始化容量
     */
    // 固定数据类型
    private int[] array;
    // 动态数据类型
    private Object[] objArray;

    private int maxSize;

    private int top;

    public Stack() {}

    public Stack(int maxSize) {
        if (maxSize > 0) {
            objArray = new Object[maxSize];
            this.maxSize = maxSize;
            top = -1;
        } else {
            throw new RuntimeException("初始化大小错误：maxSize=" + maxSize);
        }
    }

    /**
     * 入栈
     * @param obj
     */
    public void objPush(Object obj) {
        // 扩容
        grow();
        // ++在前表示先运算再赋值，优先级高，在后表示先赋值再运算，优先级低
        objArray[++top] = obj;
    }

    /**
     * 出栈
     * @return
     */
    public Object objPop() {
        Object obj = peekTop();
        // 声明原顶栈可以回收空间
        objArray[top--] = null;
        return obj;
    }

    /**
     * 查看顶栈
     * @return
     */
    public Object peekTop() {
        if (top != -1) {
            return objArray[top];
        } else {
            throw new RuntimeException("stack is null");
        }
    }

    /**
     * 动态扩容
     */
    public void grow() {
        // <<左移位运算符，1表示乘以2的1次方
        if (top == maxSize - 1) {
            maxSize = maxSize<<1;
            objArray = Arrays.copyOf(objArray, maxSize);
        }
    }

    // 基于链式存储，不连续存储的非线性实现
    private class Node<Object> {
        private Object data;
        private Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node nodeTop;
    private int size;

    public void nodePush(Object obj) {
        // 栈顶指向新元素，新元素的next指向原栈顶元素
        nodeTop = new Node(obj, nodeTop);
        size++;
    }

    public Object nodePop() {
        Node old = nodeTop;
        // 栈顶指向下一个元素
        nodeTop = nodeTop.next;
        // 声明原顶栈可以回收空间
        old.next = null;
        size--;
        return old.data;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[ ");
        for (Node<Object> node = nodeTop; node != null; node = node.next) {
            sb.append(node.data.toString() + " ");
        }
        return sb.toString()+"]";
    }

    public static void main(String[] args)
    {
        /*Stack stack = new Stack(1);
        stack.objPush("123");
        stack.objPush("456");
        System.out.println(stack.peekTop());
        System.out.println(stack.objPop());
        System.out.println(stack.peekTop());*/
        Stack stack = new Stack();
        stack.nodePush("111");
        stack.nodePush("222");
        stack.nodePush("aaa");
        stack.nodePush("bbb");
        stack.nodePop();
        System.out.println(stack);
    }

}
