package io.liu.catnip.mvc.mapper;

import io.liu.catnip.entity.DO.ArticleDO;
import io.liu.catnip.mvc.mapper.provider.WikiArticleMapperProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WikiArticleMapper {
    @InsertProvider(type = WikiArticleMapperProvider.class, method = "createArticleSql")
    void createArticle(Long userID, String title, String content);

    @SelectProvider(type = WikiArticleMapperProvider.class, method = "getArticleSql")
    ArticleDO getArticle(String articleID);
}
