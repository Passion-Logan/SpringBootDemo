package nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @File Name: nashorn
 * @Author: WQL //作者及
 * @Date: 2019/7/1 17:49//完成日期
 * @Description: // Java 中调用 	Nashorn, JavaScript 引擎
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class nashornDemo
{

    public static void main(String[] args)
    {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Hello Nashorn";
        Integer result = null;

        try
        {
            nashorn.eval("print('" + name + "')");
            result = (Integer) nashorn.eval("10 + 2");

        } catch (ScriptException e)
        {
            e.printStackTrace();
            System.out.println("执行脚本错误: "+ e.getMessage());
        }

        System.out.println(result.toString());
    }

}