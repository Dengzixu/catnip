package io.liu.catnip.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record PasswordDTO(@NotBlank(message = "手机号不能为空")
                          @Pattern(regexp = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[0-35-9]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[0-35-9]\\d{2}|6[2567]\\d{2}|4[579]\\d{2})\\d{6}$", message = "手机号格式不正确")
                          String phone,
                          @NotBlank(message = "密码不能为空")
                          String password) {
}
