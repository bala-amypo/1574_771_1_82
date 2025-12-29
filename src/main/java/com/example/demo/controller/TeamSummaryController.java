package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService teamSummaryService;

    public TeamSummaryController(TeamSummaryService teamSummaryService) {
        this.teamSummaryService = teamSummaryService;
    }

    @PostMapping("/generate")
    public ResponseEntity<TeamSummaryRecord> generate(
            @RequestBody Map<String, String> payload) {

        String teamName = payload.get("teamName");
        LocalDate summaryDate = LocalDate.parse(payload.get("summaryDate"));

        TeamSummaryRecord summary =
                teamSummaryService.generateSummary(teamName, summaryDate);

        return ResponseEntity.status(HttpStatus.CREATED).body(summary);
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<TeamSummaryRecord>> byTeam(
            @PathVariable String teamName) {

        return ResponseEntity.ok(
                teamSummaryService.getSummariesByTeam(teamName)
        );
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryRecord>> getAll() {
        return ResponseEntity.ok(
                teamSummaryService.getAllSummaries()
        );
    }
}
