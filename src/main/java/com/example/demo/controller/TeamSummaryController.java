package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService service;

    public TeamSummaryController(TeamSummaryService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<TeamSummaryRecord> generate(
            @RequestParam String teamName,
            @RequestParam LocalDate summaryDate) {

        return new ResponseEntity<>(
                service.generateSummary(teamName, summaryDate),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<TeamSummaryRecord>> byTeam(
            @PathVariable String teamName) {
        return ResponseEntity.ok(service.getSummariesByTeam(teamName));
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryRecord>> getAll() {
        return ResponseEntity.ok(service.getAllSummaries());
    }
}
