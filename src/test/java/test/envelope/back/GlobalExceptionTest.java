package test.envelope.back;

import com.envelope.back.TemplateApplication;
import com.envelope.back.common.utils.R;
import com.envelope.back.common.utils.RestCode;
import com.envelope.back.enhance.exception.GlobalException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemplateApplication.class)
public class GlobalExceptionTest {

    @Test
    @GetMapping("/test1")
    public void test1(){
        throw new GlobalException("模拟异常抛出", RestCode.ERROR_CUSTOM_CODE);
    }


    @Test
    @GetMapping("/test2")
    public void test2() throws GlobalException{
        int i = 1 / 0;
        // return R.ok().data("list", Arrays.asList(1,2,3,4));
    }

}
