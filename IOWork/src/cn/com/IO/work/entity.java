package cn.com.IO.work;

public class entity {

    //商品名称
    private String name;
    //商品价格
    private float price;
    //商品类型
    private String clazz;
    //商品数量
    private int number;

    public entity(String name, float price, String clazz, int number) {
        this.name = name;
        this.price = price;
        this.clazz = clazz;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
