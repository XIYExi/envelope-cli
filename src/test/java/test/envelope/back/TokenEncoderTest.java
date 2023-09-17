package test.envelope.back;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.envelope.back.security.jwt.JwtProvider;

public class TokenEncoderTest {

    public static void main(String[] args) {
        String postman_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmMxIiwiaWF0IjoxNjk0ODc1ODgwLCJ1c2VySWQiOiJhYmMxIiwiYXV0aG9yaXR5Ijpbe30se30se30se31dLCJleHAiOjE2OTQ5NjIyODB9.KWPDNbZUZesscyQklwiwjMRqS5IV6zS5PLs3r9pjvV0";

        String redis_token = "";

        JwtProvider jwtProvider = new JwtProvider();
        //JWT jwt = jwtProvider.decodeToken(token);

        //System.out.println(jwt);

    }
}
