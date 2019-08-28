package interview;

import java.util.*;

/**
 * @File Name: interview
 * @Author: WQL //作者及
 * @Date: 2019/8/28 17:54//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Topic1
{

    public static void main(String[] args)
    {
        /**
         * 一个List<Map<String, Object>>对象，按照其中Map的key值进行分组并根据value值进行降序排列
         * 要求：
         * 初始化该list一个私有的静态方法完成此算法(至少含有参数List<Map<String, Object>>)
         */

        List<Map<String, Object>> list = new ArrayList(){{
            add(new HashMap(){{put("a", 1); put("c", 3);}});
            add(new HashMap(){{put("a", 11); put("b", 2);}});
            add(new HashMap(){{put("a", 111); put("c", 1);}});
            add(new HashMap(){{put("b", 22);}});
            add(new HashMap(){{put("a", 1111); put("b", 222);}});
        }};

        System.out.println(list);
    }

    public static Map<String, List<Map<String, Object>>> transition(List<Map<String, Object>> list) {
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        // 分组
        for (Map<String ,Object> temp : list) {
            // 获取map的每一对值
            Iterator<Map.Entry<String, Object>> iterator = temp.entrySet().iterator();
            while (iterator.hasNext()) {
                
            }
        }
    }
}
