package io.liu.catnip.mvc.service;

import io.liu.catnip.entity.dto.PasswordRegisterDTO;

public interface UserService {
    /**
     * 通过密码注册用户
     * @param passwordRegisterDTO 传递数据
     */
    void registerByPassword(PasswordRegisterDTO passwordRegisterDTO);

}
