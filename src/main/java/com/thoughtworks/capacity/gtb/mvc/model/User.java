package com.thoughtworks.capacity.gtb.mvc.model;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    Integer id;

    @NotBlank(message = "用户名不为空")
    @Size(min = 3, max = 10, message = "用户名不合法")
    @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$",message = "用户名只能由字母、数字或下划线组成")
    String username;

    @NotBlank(message = "密码是不为空")
    @Size(min = 5, max = 12, message = "密码不合法")
    String password;

    @Email(message = "邮箱地址不合法")
    String email;
}
