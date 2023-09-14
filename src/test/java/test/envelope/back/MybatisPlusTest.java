package test.envelope.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.envelope.back.TemplateApplication;
import com.envelope.back.mapper.UserMapper;
import com.envelope.back.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemplateApplication.class)
public class MybatisPlusTest {



    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        QueryWrapper<User> query = new QueryWrapper<>();

        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }


}
