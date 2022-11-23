package io.liu.catnip.constant;

public class ArticleStatus {
    public static final int DEFAULT = 0b0000_0000_0000_0000_0000_0000;

    // 文章隐藏
    public static final int HIDDEN = 0b0000_0000_0000_0000_0000_0010;

    // 审核中
    public static final int EXAMINE = 0b0000_0000_0000_0000_0000_0100;

    // 已删除
    public static final int DELETED = 0b0100_0000_0000_0000_0000_0100;
}
