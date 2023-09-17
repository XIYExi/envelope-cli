package com.envelope.back.security;


import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;


import java.time.LocalDateTime;
import java.util.Collection;


// ip 和 登录时间是可选项！在Auth其余信息初始化之后通过链式编程补充！
@Accessors(chain = true)
public class AuthUser extends User {
    // 上述的User并不是自己定义的pojo中的！是spring security自己封装的User类！
    // 本质是实现了两个接口： UserDetails, CredentialsContainer

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 登录ip
     */
    private String loginIp;


    /**
     * 登录时间
     */
    private LocalDateTime loginTime;


    public AuthUser(String username, String password,  Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
