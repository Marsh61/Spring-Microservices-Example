package com.apitester.test.zuul.apigateway.xss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Comments {

    private List<Comment> comments = new ArrayList<>(Arrays.asList(
            new Comment("Brandon Marshall", "Welcome to this secure comment thread")
    ));

    public List<Comment> getAllComments() {
        return this.comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}