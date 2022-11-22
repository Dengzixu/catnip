package io.liu.catnip.entity.bo;

public record ArticleBO(Long id,
                        Long userID,
                        String title,
                        String content,
                        String create_time,
                        String modify_time) {
}
