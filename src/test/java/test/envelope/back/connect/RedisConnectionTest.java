package test.envelope.back.connect;

import cn.hutool.jwt.JWT;
import com.envelope.back.TemplateApplication;
import com.envelope.back.common.utils.RedisUtils;
import com.envelope.back.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemplateApplication.class)
public class RedisConnectionTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void test() {

        // 存
        // RedisUtils.save("key", new Object());

        // 取
        //Object value = RedisUtils.get("key", Object.class);

        String postman_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmMxIiwiaWF0IjoxNjk0ODc1ODgwLCJ1c2VySWQiOiJhYmMxIiwiYXV0aG9yaXR5Ijpbe30se30se30se31dLCJleHAiOjE2OTQ5NjIyODB9.KWPDNbZUZesscyQklwiwjMRqS5IV6zS5PLs3r9pjvV0";

        //String redis_token = "";

        System.err.println("captcha: 1 - " + RedisUtils.hasKey("abc1"));

        System.err.println("captcha: 1 - " + RedisUtils.hasKey("sessionAttr:captcha"));

        String redis_token = RedisUtils.get("abc1", String.class);

        /*JwtProvider jwtProvider = new JwtProvider();
        JWT jwt = jwtProvider.decodeToken(captcha);*/

        if (postman_token.equals(redis_token)){
            System.out.println("yes, there are equal.");
        }
        else{
            System.out.println("no, there ara not equal!");
        }

       // System.out.println(jwt);
        JWT jwt = jwtProvider.decodeToken(redis_token);

        System.out.println(jwt);



        // System.out.println(captcha);

    }

}
