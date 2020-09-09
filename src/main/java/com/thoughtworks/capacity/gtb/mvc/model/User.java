package com.thoughtworks.capacity.gtb.mvc.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    Integer id;

    @NotNull(message = "用户名不为空")
    @Max(10)
    @Min(3)
    String username;

    @NotNull(message = "密码是不为空")
    @Max(12)
    @Min(5)
    String password;

    @Email
    String email;
}
