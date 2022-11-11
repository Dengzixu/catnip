package io.liu.catnip.mvc.mapper;

import io.liu.catnip.mvc.mapper.provider.WikiArticleMapperProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WikiArticleMapper {
    @InsertProvider(type = WikiArticleMapperProvider.class, method = "createArticleSql")
    void createArticle(Long userID, String title, String content);
}
