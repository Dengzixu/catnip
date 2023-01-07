package io.liu.catnip.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ArticleDTO(@NotBlank(message = "文章标题不能为空")
                         String title,
                         @NotBlank(message = "正文不能为空")
                         String content,
                         @NotNull
                         @JsonProperty(value = "category_id")
                         Long categoryID) {
}
