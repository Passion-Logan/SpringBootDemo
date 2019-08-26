package structure.linkedlist;

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
public class LinkedList
{

    // 链表头
    private Node head;
    // 链表尾
    private Node tail;
    // 节点数
    private int size;

    public static void main(String[] args) {
        LinkedList linkedlist = new LinkedList();
        linkedlist.addHead("456");
        linkedlist.addHead("123");
        //linkedlist.addTail("hello ");
        //linkedlist.addTail("word");
        //linkedlist.deleteHead();
        //linkedlist.deleteTail();
        linkedlist.display();
    }    

    /**
     * 双端链表
     */
    public class Node {
        private Object data;
        // 上一个
        private Node prev;
        // 下一个
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 向链表头添加数据
     * @param object
     */
    public void addHead(Object object) {
        Node node = new Node(object);
        if (size == 0) {
            head = node;
            tail = node;
            size++;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
            size++;
        }
    }

    /**
     * 删除头
     */
    public void deleteHead() {
        // 头部指向下一个，prev值为空则说明是链表的头部
        if (size != 0) {
            head.prev = null;
            head = head.next;
            size--;
        }
    }

    /**
     * 向链表尾部添加数据
     */
    public void addTail(Object object) {
        Node node = new Node(object);
        if (size == 0) {
            head = node;
            tail = node;
            size++;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
            size++;
        }
    }

    /**
     * 删除尾部
     */
    public void deleteTail() {
        // 尾部指向上一个，next值为null则说明是链表的尾部
        if (size != 0) {
            tail.next = null;
            tail = tail.prev;
            size--;
        }
    }

    /**
     * 显示数据
     */
    public void display() {

        if (size > 0) {
            Node node = head;
            int tempSize = size;
            if (tempSize == 1) {
                System.out.println("["+node.data+"]");
                return;
            }

            while (tempSize > 0) {
                if (node.equals(head)) {
                    System.out.print("["+node.data+"->");
                } else if (node.next == null) {
                    System.out.print(node.data+"]");
                } else {
                    System.out.print(node.data+"->");
                }
                node = node.next;
                tempSize--;
            }
        }
    }
}