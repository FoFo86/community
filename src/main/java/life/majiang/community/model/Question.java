package life.majiang.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    /**
     * 标题时间
     */
    private String title;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 修改时间
     */
    private Long gmtModified;
    /**
     * 作为user表中id的外键
     */
    private Integer creator;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 浏览数
     */
    private Integer viewCount;
    /**
     * 收藏数
     */
    private Integer likeCount;
    /**
     * 标签时间
     */
    private String tag;
    /**
     * 描述
     */
    private String description;
}