package io.liu.catnip.mvc.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

public class WikiArticleMapperProvider {
    public static final String CATNIP_WIKI_ARTICLE_TABLE_NAME = "catnip_wiki_article";

    public String createArticleSql(Long userID, String title, String content) {
        return new SQL() {{
            INSERT_INTO(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            VALUES("user_id", "#{userID}");
            VALUES("title", "#{title}");
            VALUES("content", "#{content}");
        }}.toString();
    }
}
