package io.liu.catnip.mvc.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

public class WikiArticleMapperProvider {
    public static final String CATNIP_WIKI_ARTICLE_TABLE_NAME = "catnip_wiki_article";

    public static final String[] ALL_COLUMNS = {"id", "user_id", "title", "content", "status", "create_time", "modify_time"};
    // 用户表 & 文章表 联合列
    public static final String[] USER_AND_ARTICLE_COLUMNS = {"catnip_wiki_article.id AS id", "catnip_wiki_article.user_id AS user_id",
            "username", "title", "content", "catnip_wiki_article.status AS status",
            "catnip_wiki_article.create_time AS create_time", "catnip_wiki_article.modify_time AS modify_time"};

    public String createArticleSql(Long userID, String title, String content) {
        return new SQL() {{
            INSERT_INTO(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            VALUES("user_id", "#{userID}");
            VALUES("title", "#{title}");
            VALUES("content", "#{content}");
            VALUES("status", "0");
        }}.toString();
    }

    public String getAllArticleSql() {
        return new SQL() {{
            SELECT(USER_AND_ARTICLE_COLUMNS);
            FROM(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            INNER_JOIN("catnip_user ON catnip_wiki_article.user_id = catnip_user.id");
        }}.toString();
    }

    public String getArticleSql(String articleID) {
        return new SQL() {{
            SELECT(USER_AND_ARTICLE_COLUMNS);
            FROM(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            INNER_JOIN("catnip_user ON catnip_wiki_article.user_id = catnip_user.id");
            WHERE("catnip_wiki_article.id = #{articleID}");
        }}.toString();
    }

    public String getArticleByUserIDSql(String userID) {
        return new SQL() {{
            SELECT(USER_AND_ARTICLE_COLUMNS);
            FROM(CATNIP_WIKI_ARTICLE_TABLE_NAME);
            INNER_JOIN("catnip_user ON catnip_wiki_article.user_id = catnip_user.id");
            WHERE("catnip_wiki_article.user_id = #{userID}");
        }}.toString();
    }
}
