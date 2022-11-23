package io.liu.catnip.mvc.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserMapperProvider {
    public static final String USER_TABLE_NAME = "catnip_user";

    public static final String[] ALL_COLUMNS = {"id", "username", "phone", "status", "create_time", "modify_time"};

    public String createUserByEmailSql(String username, String encryptPassword, String phone) {
        return new SQL() {{
            INSERT_INTO(USER_TABLE_NAME);
            VALUES("username", "#{username}");
            VALUES("password", "#{encryptPassword}");
            VALUES("phone", "#{phone}");
        }}.toString();
    }

    public String queryUserByPhoneSql(String phone) {
        return new SQL() {{
            SELECT(ALL_COLUMNS);
            FROM(USER_TABLE_NAME);
            WHERE("phone = #{phone}");
        }}.toString();
    }

    public String queryAndValidSql(String phone, String encryptPassword) {
        return new SQL() {{
            SELECT(ALL_COLUMNS);
            FROM(USER_TABLE_NAME);
            WHERE("phone = #{phone}");
            WHERE("password = #{encryptPassword}");
        }}.toString();
    }
}
