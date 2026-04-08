package com.ooadproject.capstone_project_sharing_platform.service.impl;

import com.ooadproject.capstone_project_sharing_platform.dto.request.FeedbackRequest;
import com.ooadproject.capstone_project_sharing_platform.dto.response.FeedbackResponse;
import com.ooadproject.capstone_project_sharing_platform.entity.Feedback;
import com.ooadproject.capstone_project_sharing_platform.entity.Student;
import com.ooadproject.capstone_project_sharing_platform.entity.Subject;
import com.ooadproject.capstone_project_sharing_platform.repository.FeedbackRepository;
import com.ooadproject.capstone_project_sharing_platform.repository.StudentRepository;
import com.ooadproject.capstone_project_sharing_platform.repository.SubjectRepository;
import com.ooadproject.capstone_project_sharing_platform.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void submitFeedback(FeedbackRequest request) {
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Student not found: " + request.getEmail()));
                
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found: " + request.getSubjectId()));

        Feedback feedback = Feedback.builder()
                .relevance(request.getRelevance())
                .gradingPattern(request.getGradingPattern())
                .resources(request.getResources())
                .difficulty(request.getDifficulty())
                .dateSubmitted(LocalDateTime.now())
                .student(student)
                .subject(subject)
                .build();
                
        feedbackRepository.save(feedback);
    }

    @Override
    public List<FeedbackResponse> viewAllSubjectFeedback() {
        return feedbackRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<FeedbackResponse> viewSubjectFeedback(Long subjectId) {
        return feedbackRepository.findBySubjectId(subjectId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    
    private FeedbackResponse mapToResponse(Feedback feedback) {
        return FeedbackResponse.builder()
                .feedbackId(feedback.getFeedbackId())
                .subjectId(feedback.getSubject().getId())
                .subjectName(feedback.getSubject().getName())
                .studentName(feedback.getStudent().getName())
                .relevance(feedback.getRelevance())
                .gradingPattern(feedback.getGradingPattern())
                .resources(feedback.getResources())
                .dateSubmitted(feedback.getDateSubmitted())
                .difficulty(feedback.getDifficulty())
                .build();
    }
}
