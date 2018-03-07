package com.apitester.test.zuul.apigateway.xss;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CommentsResource {

    @Autowired
    private Comments comments;

    @RequestMapping(value="/commentThread", method = RequestMethod.GET)
    public String getAllComments(Model model) {
        model.addAttribute("commentsList", comments.getAllComments());
        return "commentThread";
    }

    @RequestMapping(value="/commentThread", method = RequestMethod.POST)
    public String addComment(@RequestParam String userName, @RequestParam String text) {
        comments.addComment(new Comment(userName, text));
        return "redirect:/commentThread";
    }

}