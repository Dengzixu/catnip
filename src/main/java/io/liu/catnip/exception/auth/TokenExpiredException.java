package io.liu.catnip.exception.auth;

import io.liu.catnip.exception.BusinessException;

public class TokenExpiredException extends BusinessException {
    public TokenExpiredException() {
        super("用户身份过期，请重新登录", 403);
    }
}
