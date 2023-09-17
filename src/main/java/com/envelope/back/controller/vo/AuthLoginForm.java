package com.envelope.back.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginForm {

    private String username;

    private String password;

    // private String qrcode;
}
