package io.liu.catnip.entity.DO;

public record ArticleDO(Long id,
                        Long userID,
                        String title,
                        String content,
                        Integer status,
                        String createTime,
                        String modifyTime) {
}
