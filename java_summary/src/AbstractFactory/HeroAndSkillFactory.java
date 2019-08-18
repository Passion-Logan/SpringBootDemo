package AbstractFactory;

/**
 * @File Name: AbstractFactory
 * @Author: WQL //作者及
 * @Date: 2019/8/18 11:38//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public abstract class HeroAndSkillFactory
{
    public abstract Heros getHeros(String heroType);
    public abstract Skills getSkills(String skillType);
}
