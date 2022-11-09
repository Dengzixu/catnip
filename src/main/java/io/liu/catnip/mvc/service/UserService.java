package io.liu.catnip.mvc.service;

import io.liu.catnip.entity.dto.PasswordDTO;

public interface UserService {
    /**
     * 通过密码注册用户
     *
     * @param passwordDTO 传递数据
     */
    void registerByPassword(PasswordDTO passwordDTO);

    /**
     * 通过密码进行身份认证
     *
     * @param passwordDTO 传递数据
     * @return JWToken
     */
    String authByPassword(PasswordDTO passwordDTO);
}
