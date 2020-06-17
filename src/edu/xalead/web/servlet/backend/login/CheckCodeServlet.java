package edu.xalead.web.servlet.backend.login;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet(name = "CheckCodeServlet", urlPatterns = {"/backend/images/checkcode.png"})
public class CheckCodeServlet extends HttpServlet {
    //验证码的字符集
    private String charCCs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    //生成一位验证码的函数
    private char createOneCC() {
        Random random = new Random();
        int count = charCCs.length();
        int index = random.nextInt(count);
        return charCCs.charAt(index);
    }

    //生成0-200的随机数
    private int createNum() {
        return new Random().nextInt(200);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //存验证码
        StringBuilder stringBuilder = new StringBuilder();
        //生成四位验证码
        for (int i = 0; i < 4; i++) {
            char c = createOneCC();
            stringBuilder.append(c);
        }

        //验证码
        String checkCode = stringBuilder.toString();
        //存到session用于验证
        request.getSession().setAttribute("checkCode", checkCode);

        //在内存缓冲区生成图片
        BufferedImage image = new BufferedImage(44, 20, BufferedImage.TYPE_INT_BGR);

        //生成画笔
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        //设置成白色
        g2d.setColor(new Color(255, 255, 255));
        //
        g2d.fillRect(0, 0, 44, 20);
        //画一个矩阵
        for (int i = 0; i < 4; i++) {
            g2d.setColor(new Color(createNum(), createNum(), createNum()));
            //只有画字符串的
            String s1 = String.valueOf(checkCode.charAt(i));
            g2d.drawString(s1, 11 * i, 14);
        }

        //生成jpg格式并以字符流的形式返回客户端
        OutputStream outputStream = response.getOutputStream();
        //设置输出格式
        response.setContentType("image/jpeg");
        //工厂模式
        JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(outputStream);
        jpegImageEncoder.encode(image);
    }
}
