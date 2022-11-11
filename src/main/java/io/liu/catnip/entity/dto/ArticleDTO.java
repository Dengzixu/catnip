package io.liu.catnip.entity.dto;

import javax.validation.constraints.NotBlank;

public record ArticleDTO(@NotBlank(message = "文章标题不能为空")
                         String title,
                         @NotBlank(message = "正文不能为空")
                         String content) {
}
