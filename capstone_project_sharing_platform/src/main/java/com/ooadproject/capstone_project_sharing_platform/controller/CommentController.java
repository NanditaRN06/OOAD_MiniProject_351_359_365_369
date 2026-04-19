package com.ooadproject.capstone_project_sharing_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.capstone_project_sharing_platform.service.CommentService;
import com.ooadproject.capstone_project_sharing_platform.dto.request.CommentRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.CommentResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String addComment(@ModelAttribute CommentRequest request) {

        commentService.addComment(request);

        Long projectId = request.getProjectId();
        String email = request.getEmail();

        if (projectId == null || email == null) {
            return "redirect:/projects";
        }

        return "redirect:/projects/" + projectId + "?email=" + email;
    }

    @GetMapping("/project/{projectId}")
    @ResponseBody
    public List<CommentResponse> getComments(@PathVariable Long projectId) {
        return commentService.getCommentsByProject(projectId);
    }
   @PostMapping("/delete")
public String deleteComment(@RequestParam Long commentId,
                            @RequestParam Long projectId,
                            @RequestParam String email) {

    commentService.deleteComment(commentId, email);

    return "redirect:/projects/" + projectId + "?email=" + email;
}
}