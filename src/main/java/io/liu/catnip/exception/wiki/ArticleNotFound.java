package io.liu.catnip.exception.wiki;

import io.liu.catnip.exception.BusinessException;

public class ArticleNotFound extends BusinessException {
    public ArticleNotFound() {
        super("文章不存在", 404);
    }
}
