package io.liu.catnip.mvc.service;

import io.liu.catnip.entity.dto.ArticleDTO;

public interface WikiService {
    void createArticle(ArticleDTO articleDTO, Long userID);
}
