package life.majiang.community.model;

import lombok.Data;

@Data
public class User {
    private int id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 账户ID
     */
    private String accountId;
    /**
     * token令牌
     */
    private String token;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 修改时间
     */
    private Long gmtModified;
    /**
     * 图片路径
     */
    private String avatarUrl;
}
