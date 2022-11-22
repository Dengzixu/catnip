package io.liu.catnip.entity.DO;

public record ArticleDO(Long id,
                        Long userID,
                        String title,
                        String content,
                        String createTime,
                        String modifyTime) {
}
