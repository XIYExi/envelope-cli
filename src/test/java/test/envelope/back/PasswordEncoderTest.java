package test.envelope.back;

import com.envelope.back.common.utils.SecurityUtils;

public class PasswordEncoderTest {


    public void test1(){
        String s = SecurityUtils.passwordEncoder(String.valueOf(123456));
        System.out.println(s);
    }

    public void test2(){
        String pwd = "$2a$10$8PbcRfWxLoSDmxzITw6RU.kkcdQmhMmWXKj6mr47F83D0//zc3eV6";
        boolean b = SecurityUtils.passwordMatches(String.valueOf(123456), pwd);
        System.out.println(b);

    }


    public static void main(String[] args) {
        PasswordEncoderTest p = new PasswordEncoderTest();
        p.test1();
        System.out.println("======================================");
        p.test2();
    }
}
