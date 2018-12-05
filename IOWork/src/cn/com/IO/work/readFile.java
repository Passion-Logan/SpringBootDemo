package cn.com.IO.work;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class readFile {

    public static void main(String[] args) throws IOException {
//        first();
        List<entity> list = second();

        System.out.println("=============��Ʒ�б�=============");
        for(int i = 0; i < list.size(); i++) {
            System.out.println("���ƣ�" + list.get(i).getName() +
                    " �۸�" + list.get(i).getPrice() +
                    " ���ͣ�" + list.get(i).getClazz() +
                    " ������" + list.get(i).getNumber());
        }
        System.out.println("=============������Ʒ�۸��ܺ�=============");
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + "�۸��ܺ�Ϊ��" + list.get(i).getPrice()*list.get(i).getNumber());
        }
        System.out.println("=============���㲻ͬ������Ʒ������=============");
        String tmp = "";

        for(int i = 0; i < list.size(); i++) {
            tmp += list.get(i).getClazz();
        }
        System.out.println("=============ascall�뷽��=============");
        count(tmp);
        System.out.println("=============�ַ����鷽��=============");
        charCount(tmp);

        /*System.out.println("=============��ͬ������Ʒ���ݼ۸�����=============");*/
    }


    public static void count(String str){
        //����26���ռ��С�����飬���26����ĸ
        int[] nums = new int[26];
        for(char i: str.toCharArray()){
            //�Զ���char iת����ascall��
            if(i>=97 && i<= 122){
                //����������������д洢
                nums[i-97]++;
            }
        }

        for(int i = 0; i< nums.length; i++){
            if(nums[i] != 0){
                //i����97������ת��Ϊchar���;Ϳ�����ʾ��Ӧ���ַ�
                char j = (char)(i+97);
                System.out.println( j + "������Ʒ���� " + nums[i] + " ��");
            }
        }
    }

    public static void charCount(String str){
        //���ַ���ת��Ϊ�ַ�����
        char[] chars = str.toCharArray();
        //����һ��HashMap��Ϊhm
        HashMap<Character,Integer> hm = new HashMap();

        //����һ���ַ���c��ѭ����������chars����
        for(char c : chars){
            //containsKey(c),��c��������hm��
            if(!hm.containsKey(c)){
                hm.put(c,1);
            }else{
                //������c��ֵ���Ҽ�1
                hm.put(c, hm.get(c)+1);
            }

            //���������if��else�滻��������һ��
            /*  hm.put(c,hm.containsKey(c) ? hm.get(c)+1:1);*/
        }


        for(Character key: hm.keySet()){
            //hm.keySet()�������м��ļ���,���и�ʽ�����
            System.out.println(key + "������Ʒ���� " + hm.get(key) + " ��");
        }
    }


    /**
     * ��ȡ�ļ�����
     * ��һ�֣�ֱ�ӵõ�������
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
     * �ڶ��֣��Ȼ�ȡ�ļ��ľ���·��
     * @throws IOException
     */
    public static List<entity> second() throws IOException {
        List<entity> list = new ArrayList<>();

        String filePath = readFile.class.getResource("/test.txt").getPath();
        filePath = filePath.substring(1);
        //ÿһ������
        String line = "";

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while((line = bufferedReader.readLine()) != null) {

                String[] as = line.split("��");
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
