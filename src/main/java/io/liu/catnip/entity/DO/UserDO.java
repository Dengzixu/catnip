package io.liu.catnip.entity.DO;

public record UserDO(Long id,
                     String username,
                     String phone,
                     Integer status,
                     String createTime,
                     String modifyTime) {
}
