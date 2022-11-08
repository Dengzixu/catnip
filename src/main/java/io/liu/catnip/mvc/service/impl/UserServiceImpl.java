package io.liu.catnip.mvc.service.impl;

import io.liu.catnip.Utils.Password;
import io.liu.catnip.entity.dto.PasswordDTO;
import io.liu.catnip.exception.user.PhoneAlreadyUsedException;
import io.liu.catnip.mvc.mapper.UserMapper;
import io.liu.catnip.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void registerByPassword(PasswordDTO passwordDTO) {
        // 密码加密
        String encryptPassword = Password.encrypt(passwordDTO.password(), "password");

        // 判断手机号是否被使用
        if (userMapper.queryUserByPhone(passwordDTO.phone()) != null) {
            throw new PhoneAlreadyUsedException();
        }

        // 取手机号后四位作为用户名
        String username = "用户 " + passwordDTO.phone().substring(passwordDTO.phone().length() - 4);


        // 写入数据库
        userMapper.createUser(username, encryptPassword, passwordDTO.phone());
    }

}
