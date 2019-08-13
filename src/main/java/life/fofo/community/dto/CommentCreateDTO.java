package life.fofo.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019/6/2.
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String content;
}
