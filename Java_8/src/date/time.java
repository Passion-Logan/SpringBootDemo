package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @File Name: date
 * @Author: WQL //作者及
 * @Date: 2019/6/18 9:44//完成日期
 * @Description: // 返回一个时间段内的所有日期
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class time
{

    public static void main(String[] args)
    {
        String start = "2019-06-20";
        String end = "2019-06-20";

        time time = new time();

        System.out.println(time.getBetweenDates(start,end).toString());
    }

    private List<String> getBetweenDates(String start, String end) {
        List<String> result = new ArrayList<>();

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date start_date = sdf.parse(start);
            Date end_date = sdf.parse(end);

            Calendar tempStart = Calendar.getInstance();

            tempStart.setTime(start_date);

            Calendar tempEnd = Calendar.getInstance();

            tempEnd.setTime(end_date);

            while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
                result.add(sdf.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.reverse(result);

        return result;
    }

}