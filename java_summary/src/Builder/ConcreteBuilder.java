package Builder;

/**
 * @File Name: Builder
 * @Author: WQL //作者及
 * @Date: 2019/8/18 20:55//完成日期
 * @Description: // 具体建造者：实现了抽象建造者接口
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class ConcreteBuilder extends Builder
{
    @Override
    public void buildPartA()
    {
        product.setPartA("建造 PartA");
    }

    @Override
    public void buildPartB()
    {
        product.setPartA("建造 PartB");
    }

    @Override
    public void buildPartC()
    {
        product.setPartA("建造 PartC");
    }
}

/**
 * 产品角色：包含多个组成部件的复杂对象
 */
class Product {
    private String partA;
    private String partB;
    private String partC;

    public void setPartA(String partA)
    {
        this.partA = partA;
    }

    public void setPartB(String partB)
    {
        this.partB = partB;
    }

    public void setPartC(String partC)
    {
        this.partC = partC;
    }

    public void show() {
        System.out.println("我是产品属性");
    }
}

/**
 * 抽象建造者：包含创建产品各个子部件的抽象方法
 */
abstract class Builder {
    // 创建产品对象
    protected Product product = new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();

    // 返回产品对象
    public Product getResult() {
        return product;
    }
}

/**
 * 指挥者：调用建造者中的方法完成复杂对象的创建
 */
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    // 产品构建与组装方法
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
