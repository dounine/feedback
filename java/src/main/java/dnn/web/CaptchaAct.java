package dnn.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码类
 *
 * @author huanghuanlai
 */
@RestController
public class CaptchaAct {
    private static final int width = 90;//定义图片的width
    private static final int height = 24;//定义图片的height
    private static final int codeCount = 4;//定义图片上显示验证码的个数
    private static final int xWidth = 18; //字符间的距离
    private static final int fontHeight = 18;
    private static final int codeY = 18;
    private static final char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static Random random = null; // 创建一个随机数生成器类
    private static StringBuffer randomCode = null;// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
    private static BufferedImage buffImg = null;// 定义图像buffer
    private static Graphics gd = null;//笔刷
    private static Font font = null;//笔

    static {
        font = new Font("Fixedsys", Font.BOLD, fontHeight);
        random = new Random();
        randomCode = new StringBuffer();
        buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        gd = buffImg.getGraphics();
        gd.setFont(font); // 创建字体，字体的大小应该根据图片的高度来定。
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void getCode(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // 将图像填充为白色
        randomCode.delete(0, randomCode.length());//清空数据、重新填入新数据
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 画边框。
        //gd.setColor(Color.BLACK);
        //gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        //randomCode.delete(0, randomCode.length());

        for (int i = 0; i < 80; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(4);
            int yl = random.nextInt(4);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(34)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(1);
            green = random.nextInt(255);
            blue = random.nextInt(1);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xWidth - (random.nextInt(12)), codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("captcha", randomCode.toString());
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/gif");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "gif", sos);
        sos.close();
    }
}
