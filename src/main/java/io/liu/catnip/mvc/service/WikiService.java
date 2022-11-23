package io.liu.catnip.mvc.service;

import io.liu.catnip.entity.DO.ArticleDO;
import io.liu.catnip.entity.bo.ArticleBO;
import io.liu.catnip.entity.dto.ArticleDTO;

import java.util.List;

public interface WikiService {
    /**
     * 创建文章
     *
     * @param articleDTO ArticleDTO
     * @param userID     用户 ID
     */
    void createArticle(ArticleDTO articleDTO, Long userID);

    /**
     * 获取所有文章
     *
     * @return List<ArticleBO>
     */
    List<ArticleBO> getAllArticle();

    /**
     * 获取文章
     *
     * @param articleID 文章 ID
     */
    ArticleBO getArticle(String articleID);
}
