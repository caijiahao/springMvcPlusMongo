package light.mvc.controller.sys;

import light.mvc.controller.base.BaseController;
import light.mvc.framework.constant.GlobalConstant;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.SessionInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/baseUtil")
public class PictureCheckCode extends BaseController {

    private static final long serialVersionUID = 1L;

    /*该方法主要作用是获得随机生成的颜色*/
    public Color getRandColor(int s, int e) {
        Random random = new Random();
        if (s > 255) s = 255;
        if (e > 255) e = 255;
        int r, g, b;
        r = s + random.nextInt(e - s);    //随机生成RGB颜色中的r值
        g = s + random.nextInt(e - s);    //随机生成RGB颜色中的g值
        b = s + random.nextInt(e - s);    //随机生成RGB颜色中的b值
        return new Color(r, g, b);
    }

    @RequestMapping("/getCheckCode")
    @ResponseBody
    public void getCheckCode(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(GlobalConstant.SESSION_INFO_ADMIN);
        //设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //指定生成的响应图片,一定不能缺少这句话,否则错误.
        response.setContentType("image/jpeg");
        int width = 86, height = 22;     //指定生成验证码的宽度和高度
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        // 画边框
        g.setColor(getRandColor(160, 200));
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 14, 20);
        }

        // 将认证码存入SESSION
        session.setAttribute("vcode", sRand);
        System.out.println(sRand);

        // 图象生效
        g.dispose();
        // 输出图象到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    @RequestMapping("/CheckCode")
    @ResponseBody
    public Json checkCode(HttpSession session, String ValidateCode) {
        Json j = new Json();
        String code = (String) session.getAttribute("vcode");

        if (ValidateCode.equals(code)) {
            j.setSuccess(true);
        } else {
            j.setSuccess(false);
        }
        return j;
    }

}
