package cn.com.IO.work;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class readFile {

    public static void main(String[] args) throws IOException {
//        first();
        List<entity> list = second();

        System.out.println("=============商品列表=============");
        for(int i = 0; i < list.size(); i++) {
            System.out.println("名称：" + list.get(i).getName() +
                    " 价格：" + list.get(i).getPrice() +
                    " 类型：" + list.get(i).getClazz() +
                    " 数量：" + list.get(i).getNumber());
        }
        System.out.println("=============计算商品价格总和=============");
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + "价格总和为：" + list.get(i).getPrice()*list.get(i).getNumber());
        }
        System.out.println("=============计算不同类型商品的数量=============");
        String tmp = "";

        for(int i = 0; i < list.size(); i++) {
            tmp += list.get(i).getClazz();
        }
        System.out.println("=============ascall码方法=============");
        count(tmp);
        System.out.println("=============字符数组方法=============");
        charCount(tmp);

        /*System.out.println("=============不同类型商品根据价格排序=============");*/
    }


    public static void count(String str){
        //创建26个空间大小的数组，存放26个字母
        int[] nums = new int[26];
        for(char i: str.toCharArray()){
            //自动将char i转化成ascall码
            if(i>=97 && i<= 122){
                //利用数组的索引进行存储
                nums[i-97]++;
            }
        }

        for(int i = 0; i< nums.length; i++){
            if(nums[i] != 0){
                //i加上97并且再转化为char类型就可以显示相应的字符
                char j = (char)(i+97);
                System.out.println( j + "类型商品共有 " + nums[i] + " 件");
            }
        }
    }

    public static void charCount(String str){
        //将字符串转化为字符数组
        char[] chars = str.toCharArray();
        //创建一个HashMap名为hm
        HashMap<Character,Integer> hm = new HashMap();

        //定义一个字符串c，循环遍历遍历chars数组
        for(char c : chars){
            //containsKey(c),当c不存在于hm中
            if(!hm.containsKey(c)){
                hm.put(c,1);
            }else{
                //否则获得c的值并且加1
                hm.put(c, hm.get(c)+1);
            }

            //或者上面的if和else替换成下面这一行
            /*  hm.put(c,hm.containsKey(c) ? hm.get(c)+1:1);*/
        }


        for(Character key: hm.keySet()){
            //hm.keySet()代表所有键的集合,进行格式化输出
            System.out.println(key + "类型商品共有 " + hm.get(key) + " 件");
        }
    }


    /**
     * 读取文件方法
     * 第一种：直接得到输入流
     * @throws IOException
     */
    public static void first() throws IOException {
        InputStream inputStream = readFile.class.getClassLoader().getResourceAsStream("test.txt");

        byte[] bytes = new byte[1024];
        int length;

        while((length = inputStream.read(bytes)) != -1) {
            System.out.write(bytes, 0, length);
        }
    }

    /**
     * 第二种：先获取文件的绝对路径
     * @throws IOException
     */
    public static List<entity> second() throws IOException {
        List<entity> list = new ArrayList<>();

        String filePath = readFile.class.getResource("/test.txt").getPath();
        filePath = filePath.substring(1);
        //每一行内容
        String line = "";

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while((line = bufferedReader.readLine()) != null) {

                String[] as = line.split("，");
                entity entity = new entity(as[0] ,Float.parseFloat(as[1]) ,as[2] ,Integer.valueOf(as[3]));

                list.add(entity);
        }

        return list;

        /*InputStream inputStream = new FileInputStream(new File(filePath));

        byte[] bytes = new byte[1024];
        int length;

        while((length = inputStream.read(bytes)) != -1) {
            System.out.write(bytes, 0, length);
        }*/
    }
}
