package com.ooadproject.capstone_project_sharing_platform.service;

import com.ooadproject.capstone_project_sharing_platform.dto.request.FeedbackRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    void submitFeedback(FeedbackRequest request);
    List<FeedbackResponse> viewAllSubjectFeedback();
    List<FeedbackResponse> viewSubjectFeedback(Long subjectId);
}
