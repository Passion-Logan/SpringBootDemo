package date;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @File Name: date
 * @Author: WQL //作者及
 * @Date: 2019/6/12 14:36//完成日期
 * @Description: // 将一个时间字符串集合转为integer类型的集合
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class demo
{

    public static void main(String[] args)
    {
        LocalDateTime dateTime = LocalDateTime.now();

        // 获取当前月
        String month = String.valueOf(dateTime.getMonthValue()).length() == 1 ? "0" + dateTime.getMonthValue() : String.valueOf(dateTime.getMonthValue());

        System.out.println(dateTime.getYear());
        System.out.println(month);
        System.out.println(dateTime.getYear() + "-" + month);

        System.out.println(month);

        String startTime = "00:11";
        String[] before = startTime.split(":");

        System.out.println(Integer.parseInt(before[0]));
        System.out.println(Integer.parseInt(before[1]));

        List<String> list = new ArrayList<>();

        list.add("07:11");
        list.add("14:11");
        list.add("12:05");

        System.out.println();

        demo demo = new demo();

        System.out.println(demo.formatList(list));
    }

    public List<Integer> formatList(List<String> list)
    {
        List<Integer> obj = new ArrayList<>(list.size());

        list.forEach(x -> obj.add(Integer.parseInt(x.replace(":", "").trim())));


        Collections.sort(obj, Comparator.naturalOrder());

        return obj;
    }

}