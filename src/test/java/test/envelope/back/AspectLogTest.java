package test.envelope.back;

import com.envelope.back.TemplateApplication;
import com.envelope.back.enhance.aop.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemplateApplication.class)
public class AspectLogTest {

    @Test
    @GetMapping("/aop")
    @Log(value = "测试日志切面")
    public void test(){
        Arrays.asList(1, 2, 3, 4, 5).forEach(System.out::println);


    }

}
