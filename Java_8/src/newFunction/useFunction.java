package newFunction;

import java.util.Arrays;
import java.util.List;

/**
 * @File Name: newFunction
 * @Author: WQL //作者及
 * @Date: 2019/6/25 11:23//完成日期
 * @Description: // Java8 方法引用
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class useFunction
{
    public static void main(String[] args)
    {
        // 构造器引用
        final Car car = Car.create(Car::new);

        final List<Car> cars = Arrays.asList(car);

        // 静态方法引用
        cars.forEach(Car::collide);

        // 特定类的任意对象的方法引用
        cars.forEach(Car::repair);

        // 特定对象的方法引用
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );

    }

}

class Car{
    public static Car create(final Supplier<Car> supplier)
    {
        return supplier.get();
    }

    public static void collide(final Car car)
    {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}

@FunctionalInterface
interface Supplier<T>{
    T get();
}