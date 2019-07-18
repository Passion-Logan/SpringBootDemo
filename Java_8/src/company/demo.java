package company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @File Name: company
 * @Author: WQL //作者及
 * @Date: 2019/7/9 22:53//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class demo
{

    private List<String[]> first = null;
    private List<String[]> second = null;
    private List<String[]>third = null;

    public static void main(String[] args)
    {
        demo demo = new demo();


        demo.handleList(demo.newData());
    }

    public String handleList(List<String[]> data)
    {
        // List<String[]> first = first(data);
        second(data);
        System.out.println(" 一级项目 " + first.size());
        System.out.println(" 二级项目 " + second.size());
        System.out.println(" 三级项目 " + third.size());

        return "";
    }

    /**
     * 存储一级项目
     *
     * @param data
     * @return
     */
    public List<String[]> first(List<String[]> data)
    {
        first = new ArrayList<>(data.size());
        for (String[] s : data)
        {
            for (int i = 0; i < s.length; i++)
            {
                if (isNum(s[i]))
                {
                    if (i - 1 == 0)
                    {
                        fication(i, s);
                    }
                    break;
                }
            }
        }
        return first;
    }

    /**
     * 存储二级项目
     *
     * @param data
     * @return
     */
    public void second(List<String[]> data)
    {
        second = new ArrayList<>(data.size());
        for (String[] s : data)
        {
            for (int i = 0; i < s.length; i++)
            {
                if (isNum(s[i]))
                {
                    fication(i, s);
                }
                break;
            }
        }
    }

    public void fication(int i, String[] s)
    {

        if (i - 1 == 0)
        {
            first.add(s);
        }
        if (i - 1 == 1)
        {
            second.add(s);
        }
        if (i - 1 == 2)
        {
            third.add(s);
        }
    }

    /**
     * 判断是否为数字
     *
     * @param msg
     * @return
     */
    public boolean isNum(String msg){
        if(java.lang.Character.isDigit(msg.charAt(0))){
            return true;
        }
        return false;
    }

    public List<String[]> newData()
    {
        List<String[]> data = new ArrayList<>();

        String[] a1 = new String[]{"设施运行2", "2.5", "1000", "2500", "空", "admin"};
        String[] a2 = new String[]{"设施运行1", "能源消耗", "2.5", "1000", "2500", "空", "admin"};
        String[] a3 = new String[]{"设施运行", "能源消耗", "2.5", "1000", "2500", "空", "admin"};
        String[] a4 = new String[]{"设施运行", "能源消耗1", "2.5", "1000", "2500", "空", "admin"};
        String[] a5 = new String[]{"专项设备购置1", "生活设备", "锅炉低氮系统更新采购", "300000", "2", "600000", "空", "admin"};
        String[] a6 = new String[]{"专项设备购置1", "生活设备", "德国EKF lacate- Scout+便携式乳酸分析", "9000", "1", "9000", "空", "admin"};
        String[] a7 = new String[]{"专项设备购置2", "生活设备", "锅炉低氮系统更新采购", "300000", "2", "600000", "空", "admin"};
        String[] a8 = new String[]{"专项设备购置2", "生活设备", "德国EKF lacate- Scout+便携式乳酸分析", "9000", "1", "9000", "空", "admin"};
        String[] a9 = new String[]{"专项设备购置2", "其他设备", "电针灸治疗仪", "5000", "10", "50000", "空", "admin"};
        data.add(a1);
        data.add(a2);
        data.add(a3);
        data.add(a4);
        data.add(a5);
        data.add(a6);
        data.add(a7);
        data.add(a8);
        data.add(a9);
        return data;
    }
}
