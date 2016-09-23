package dnn.web;

import dnn.common.request.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码类
 *
 * @author huanghuanlai
 */
@RestController
public class CaptchaAct {

    private static final char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static final String NAME = "captcha";

    private static Random random = new Random(); // 创建一个随机数生成器类
    private static BufferedImage buffImg = null;// 定义图像buffer
    private static Graphics gd = null;//笔刷
    private static Font font = null;//笔

    private static final int width = 100;//定义图片的width
    private static final int height = 36;//定义图片的height
    private static final int codeCount = 4;//定义图片上显示验证码的个数
    private static final int xWidth = 24; //字符间的距离
    private static final int marginLeft = 4;
    private static final int fontHeight = 30;
    private static final int codeY = 30;

    public static void init(int fontHeight, int width, int height) {
        font = new Font("Fixedsys", Font.BOLD, fontHeight);
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        gd = buffImg.getGraphics();
        gd.setFont(font); // 创建字体，字体的大小应该根据图片的高度来定。
    }

    @GetMapping("/captcha")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        init(fontHeight, width, height);

        gd.setColor(Color.white);
        gd.fillRect(0, 0, width, height);

        int red = 0, green = 0, blue = 0;
        StringBuffer codes = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String code = String.valueOf(codeSequence[random.nextInt(34)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, marginLeft+(i * xWidth), codeY);

            codes.append(code);
        }

        gd.setColor(Color.BLACK);

        for (int i = 0; i < 140; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(4);
            int yl = random.nextInt(4);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        request.getSession().setAttribute(NAME,codes.toString());

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public static boolean validCaptcha(String captchaVal) {
        if(StringUtils.isBlank(captchaVal)){
            return false;
        }
        if(captchaVal.equalsIgnoreCase(RequestContext.get().getSession().getAttribute(NAME).toString())){
            return true;
        }
        return false;
    }
}
