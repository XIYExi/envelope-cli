package test.envelope.back.mapper;


import com.envelope.back.TemplateApplication;
import com.envelope.back.mapper.PermissionMapper;
import com.envelope.back.pojo.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemplateApplication.class)
public class SelectPermissionByUserIdTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void test(){
        String userId = "abc1";

        Permission permission = permissionMapper.selectPermissionByUserId(userId);

        System.out.println(permission);

    }

}
