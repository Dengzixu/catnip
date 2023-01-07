package io.liu.catnip.entity.DO;

public record ArticleDO(Long id,
                        Long userID,
                        String username,
                        String title,
                        String content,
                        Long categoryID,
                        String categoryName,
                        Integer status,
                        String createTime,
                        String modifyTime) {
}
