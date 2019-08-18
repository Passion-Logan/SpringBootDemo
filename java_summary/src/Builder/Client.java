package Builder;

/**
 * @File Name: Builder
 * @Author: WQL //作者及
 * @Date: 2019/8/18 21:04//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Client
{
    public static void main(String[] args)
    {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }
}
