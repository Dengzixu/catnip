package io.liu.catnip.mvc.service;

import io.liu.catnip.entity.DO.ArticleDO;
import io.liu.catnip.entity.DO.CategoryDO;
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
    List<ArticleDO> listAllArticle();

    /**
     * 获取文章
     *
     * @param articleID 文章 ID
     */
    ArticleDO queryArticle(String articleID);

    /**
     * 根据 用户ID 获取文章列表
     *
     * @param userID 用户 ID
     * @return List<ArticleDO>
     */
    List<ArticleDO> listArticleByUserID(String userID);

    /**
     * 获取所有的文章分类
     *
     * @return List<CategoryDO>
     */
    List<CategoryDO> listCategory();
}
