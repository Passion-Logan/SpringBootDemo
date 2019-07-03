package verificationCode.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;*/
/**
 * @File Name: verificationCode.utils
 * @Author: WQL //作者及
 * @Date: 2019/7/2 11:29//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class demo
{
    public static final String RANDOMCODEKEY = "validCode";
    private Random random = new Random();
    private int width = 80;
    private int height = 30;
    private int lineSize = this.random(0, 50);
    private int stringNum = 4;
    private String randString;

    public demo() {
        this.randString = this.findVerification(this.stringNum);
    }

    private Font getFont() {
        return new Font("微软雅黑", 1, 20);
    }

    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + this.random.nextInt(bc - fc - 16);
        int g = fc + this.random.nextInt(bc - fc - 14);
        int b = fc + this.random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /*public String getRandcode(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage image = new BufferedImage(this.width, this.height, 4);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, this.width, this.height);
        g.setFont(new Font("Times New Roman", 0, 18));
        g.setColor(this.getRandColor(110, 133));

        for(int i = 0; i <= this.lineSize; ++i) {
            this.drowLine(g);
        }

        String randomString = "";

        for(int i = 1; i <= this.stringNum; ++i) {
            randomString = this.drowString(g, randomString, i);
        }

        g.dispose();

        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return randomString;
    }*/

    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(this.getFont());
        g.setColor(new Color(this.random.nextInt(101), this.random.nextInt(111), this.random.nextInt(121)));
        String rand = String.valueOf(this.getRandomString(this.random.nextInt(this.randString.length())));
        randomString = randomString + rand;
        g.translate(this.random.nextInt(3), this.random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    private void drowLine(Graphics g) {
        int x = this.random.nextInt(this.width);
        int y = this.random.nextInt(this.height);
        int xl = this.random.nextInt(13);
        int yl = this.random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    public int random(int min, int max) {
        Random random = new Random();
        int i = random.nextInt(max) % (max - min + 1) + min;
        return i;
    }

    public String findVerification(int length) {
        StringBuffer stringBuffer = new StringBuffer();

        while(true) {
            while(stringBuffer.length() < length) {
                int number = (int)(Math.random() * 200.0D);
                String temp = number + "";
                if (temp.length() == 1) {
                    stringBuffer = stringBuffer.append(temp);
                } else if (number >= 65 && number <= 90 || number >= 97 && number <= 122) {
                    stringBuffer = stringBuffer.append((char)number);
                }
            }

            return String.valueOf(stringBuffer);
        }
    }

    public String getRandomString(int num) {
        return String.valueOf(this.randString.charAt(num));
    }

}