package io.liu.catnip.exception.user;

import io.liu.catnip.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("用户不存在", 404);
    }
}
