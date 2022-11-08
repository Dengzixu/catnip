package io.liu.catnip.exception.user;

import io.liu.catnip.exception.BusinessException;

public class PhoneAlreadyUsedException extends BusinessException {

    public PhoneAlreadyUsedException() {
        super("此手机号已被使用", 403);
    }
}
