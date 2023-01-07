package io.liu.catnip.mvc.mapper;

import io.liu.catnip.entity.DO.ArticleDO;
import io.liu.catnip.entity.DO.CategoryDO;
import io.liu.catnip.mvc.mapper.provider.WikiArticleMapperProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface WikiArticleMapper {
    @InsertProvider(type = WikiArticleMapperProvider.class, method = "createArticleSql")
    void createArticle(Long userID, String title, String content, Long categoryID);

    @SelectProvider(type = WikiArticleMapperProvider.class, method = "getAllArticleSql")
    List<ArticleDO> getAllArticle();

    @SelectProvider(type = WikiArticleMapperProvider.class, method = "getArticleSql")
    ArticleDO getArticle(String articleID);

    @SelectProvider(type = WikiArticleMapperProvider.class, method = "getArticleByUserIDSql")
    List<ArticleDO> getArticleByUserID(String userID);

    @SelectProvider(type = WikiArticleMapperProvider.class, method = "listCategorySql")
    List<CategoryDO> listCategory();
}
