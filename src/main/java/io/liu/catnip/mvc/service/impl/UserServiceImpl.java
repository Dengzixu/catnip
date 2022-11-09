package io.liu.catnip.mvc.service.impl;

import io.liu.catnip.Utils.JWTUtils;
import io.liu.catnip.Utils.Password;
import io.liu.catnip.entity.DO.UserDO;
import io.liu.catnip.entity.dto.PasswordDTO;
import io.liu.catnip.exception.user.PhoneAlreadyUsedException;
import io.liu.catnip.exception.user.UserNotFoundException;
import io.liu.catnip.mvc.mapper.UserMapper;
import io.liu.catnip.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final JWTUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, JWTUtils jwtUtils) {
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
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

    @Override
    public String authByPassword(PasswordDTO passwordDTO) {
        // 密码加密
        String encryptPassword = Password.encrypt(passwordDTO.password(), "password");

        // 判断用户是否存在
        UserDO queriedUser = userMapper.queryAndValid(passwordDTO.phone(), encryptPassword);
        if (queriedUser == null) {
            throw new UserNotFoundException();
        }

        // 生成 JWToken 了
        String token = jwtUtils.encode(queriedUser.id());

        return token;
    }

}
