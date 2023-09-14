package test.envelope.back;

import com.envelope.back.TemplateApplication;
import com.envelope.back.enhance.async.AsyncExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemplateApplication.class)
public class AsyncTest {

    @Resource
    AsyncExample asyncExample;

    @Test
    public void test(){
        for(int i=0;i<10;++i){
            asyncExample.run();
        }
    }

}
