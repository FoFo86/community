package life.fofo.community.controller;

import life.fofo.community.dto.CommentDTO;
import life.fofo.community.dto.QuestionDTO;
import life.fofo.community.enums.CommentTypeEnum;
import life.fofo.community.service.CommentService;
import life.fofo.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/question/{id}")
    public String question(Model model, @PathVariable(name = "id") Long id){
        QuestionDTO questionDTO = questionService.getById(id);

        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //累加阅读量
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
