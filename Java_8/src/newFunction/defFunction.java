package newFunction;

/**
 * @File Name: newFunction
 * @Author: WQL //作者及
 * @Date: 2019/6/25 11:57//完成日期
 * @Description: // java8默认方法
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class defFunction implements def
{

    /**
     * 调用指定接口的默认方法
     */
    public void print()
    {
        def.super.sout();
    }

    public void blow()
    {
        def.blowHorn();
    }

    public static void main(String[] args)
    {
        defFunction function = new defFunction();
        function.print();
        function.blow();
    }
}

interface def{
    default void sout()
    {
        System.out.println("我是接口的默认方法");
    }

    static void blowHorn()
    {
        System.out.println("按喇叭！");
    }
}