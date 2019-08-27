package structure.queue;

/**
 * @File Name: structure.queue
 * @Author: WQL //作者及
 * @Date: 2019/8/27 16:56//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Queue
{

    /**
     * 1. 单向队列(Queue):只能在一端插入数据，另一端删除数据
     * 2. 双向队列(Deque):每一端都可以进行插入、删除数据
     *
     * 与栈不同的是，队列中的数据并不总是从数组的0下标开始的
     * 选择的做法是移动队头和队尾的指针
     * 为了避免队列不满却不能插入新的数据，我们可以让队尾指针绕回到数组开始的位置，这也称为“循环队列”
     */

    // 单向循环队列，顺序存储结构实现
    private Object[] objQueue;
    // 队列大小
    private int maxSize;
    // 顶部
    private int top;
    // 底部
    private int bottom;
    // 实际元素
    private int item;

    public void Queue(int size) {
        maxSize = size;
        objQueue = new Object[maxSize];
        top = 0;
        bottom = -1;
        item = 0;
    }

    /**
     * 入队
     * @param obj
     */
    public void add(Object obj) {
        if (item == maxSize) {
            throw new RuntimeException(obj+" add error, queue is full");
        }
        // 循环队列，首尾结合，下标控制队首和队尾的位置
        if (bottom == maxSize - 1) {
            bottom = -1;
        }
        objQueue[++bottom] = obj;
        item++;
    }

    /**
     * 出队
     * @return
     */
    public Object out() {
        if (item == 0) {
            throw new RuntimeException("queue is null");
        }
        Object obj = objQueue[top];
        // 声明原顶栈可以回收空间(GC)
        objQueue[top] = null;
        top++;
        // 重置下标
        if (top == maxSize) {
            top = 0;
        }
        item--;
        return obj;
    }

    // 链式存储结构实现
    private class NodeQueue<Object> {
        private Object data;

        private NodeQueue next;

        public NodeQueue(Object data, NodeQueue next) {
            this.data = data;
            this.next = next;
        }
    }

    // 队列头 出
    private NodeQueue queueTop;
    // 队列尾 进
    private NodeQueue queueBottom;
    // 队列大小
    private int size;

    public Queue() {
        queueTop = null;
        queueBottom = null;
        size = 0;
    }

    /**
     * 入队
     * @param obj
     */
    public void addNodeQueue(Object obj) {
        if (size == 0) {
            queueTop = new NodeQueue(obj, null);
            // 指向同一存储地址
            queueBottom = queueTop;
        } else {
            NodeQueue<Object> nodeQueue = new NodeQueue<>(obj, null);
            // 让尾节点的next指向新增的节点
            queueBottom.next = nodeQueue;
            // 以新节点作为尾节点
            queueBottom = nodeQueue;
        }
        size++;
    }

    /**
     * 出队
     * @return
     */
    public Object removeNodeQueue() {
        if (size == 0) {
            throw new RuntimeException("queue is null");
        }
        NodeQueue nodeQueue = queueTop;
        queueTop = queueTop.next;
        // 声明原队列头next可以回收 空间(GC)
        nodeQueue.next = null;
        size--;

        return nodeQueue.data;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("{ ");
        for (NodeQueue nodeQueue = queueTop; nodeQueue != null; nodeQueue = nodeQueue.next) {
            sb.append(nodeQueue.data.toString() + " ");
        }
        return sb.toString() + "}";
    }

    public static void main(String[] args)
    {
        Queue queue = new Queue();
        queue.addNodeQueue("123");
        queue.addNodeQueue("abc");
        queue.addNodeQueue("ddd");
        System.out.println(queue);
        queue.removeNodeQueue();
        System.out.println(queue);
        queue.removeNodeQueue();
        queue.removeNodeQueue();
        System.out.println(queue);
    }
}
