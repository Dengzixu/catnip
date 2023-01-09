package io.liu.catnip.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArticleVO(Long id,
                        @JsonProperty(value = "user_id")
                        Long userID,
                        String username,
                        String title,
                        String content,
                        @JsonProperty(value = "category_id")
                        Long categoryID,
                        @JsonProperty(value = "category_name")
                        String categoryName,
                        Integer status,
                        @JsonProperty(value = "create_time")
                        String createTime,
                        @JsonProperty(value = "modify_time")
                        String modifyTime) {
}
