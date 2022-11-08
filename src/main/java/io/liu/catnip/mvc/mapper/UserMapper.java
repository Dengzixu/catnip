package io.liu.catnip.mvc.mapper;

import io.liu.catnip.entity.DO.UserDO;
import io.liu.catnip.mvc.mapper.provider.UserMapperProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @InsertProvider(type = UserMapperProvider.class, method = "createUserByEmailSql")
    void createUser(String username, String encryptPassword, String phone);

    @SelectProvider(type = UserMapperProvider.class, method = "queryUserByPhoneSql")
    UserDO queryUserByPhone(String phone);
}
