package io.liu.catnip.entity.DO;

public record UserDO(Long id,
                     String username,
                     String phone,
                     String createTime,
                     String modifyTime) {
}
