package io.liu.catnip.mvc.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

public class WikiArticleMapperProvider {
    public static final String CATNIP_WIKI_ARTICLE_TABLE_NAME = "catnip_wiki_article";

    public static final String[] ALL_COLUMNS = {"id", "user_id", "title", "content", "status", "create_time", "modify_time"};

    public String createArticleSql(Long userID, String title, String content) {
        return new SQL() {{
            INSERT_INTO(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            VALUES("user_id", "#{userID}");
            VALUES("title", "#{title}");
            VALUES("content", "#{content}");
        }}.toString();
    }

    public String getAllArticleSql() {
        return new SQL() {{
            SELECT(ALL_COLUMNS);
            FROM(CATNIP_WIKI_ARTICLE_TABLE_NAME);
        }}.toString();
    }

    public String getArticleSql(String articleID) {
        return new SQL() {{
            SELECT(ALL_COLUMNS);
            FROM(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            WHERE("id = #{articleID}");
        }}.toString();
    }

    public String getArticleByUserIDSql(String userID) {
        return new SQL() {{
            SELECT(ALL_COLUMNS);
            FROM(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            WHERE("user_id = #{userID}");
        }}.toString();
    }
}
