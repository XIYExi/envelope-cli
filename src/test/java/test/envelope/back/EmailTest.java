package test.envelope.back;

import com.envelope.back.TemplateApplication;
import com.envelope.back.service.impl.EmailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemplateApplication.class)
public class EmailTest {

    /**
     * 在尝试使用邮件测试案例的时候，请修改下方发送邮件的地址
     * ===========================================
     * ======== 不许向我的邮箱发垃圾信息！！！ ========
     * ===========================================
     */

    @Resource // 使用Autowired会有红波浪线报错，但不影响使用
    private EmailServiceImpl emailServiceImpl;

    @Test
    @GetMapping("/send1")
    public void send1() {
        emailServiceImpl.send("1356768966@qq.com", "测试邮件", "欢迎使用envelope-cli开发脚手架");
    }

    @Test
    public void send2() {
        File file = new File("D:\\test.txt");
        emailServiceImpl.send("1356768966@qq.com", "测试邮件", "欢迎使用envelope-cli开发脚手架", file);
    }

    @Test
    public void send3() {
        Context context = new Context();
        context.setVariable("code", 6666);
        context.setVariable("name", "xiye");
        emailServiceImpl.send("1356768966@qq.com", "激活码模板邮件", context);
    }

    @Test
    public void send4() {
        File file = new File("D:\\test.txt");
        Context context = new Context();
        context.setVariable("code", 6666);
        emailServiceImpl.send("1356768966@qq.com", "激活码模板邮件", context, file);
    }
}