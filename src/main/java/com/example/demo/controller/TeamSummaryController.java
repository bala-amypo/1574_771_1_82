package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/team-summary")
@CrossOrigin(origins = "*")

public class TeamSummaryController {

    private final TeamSummaryService summaryService;

    public TeamSummaryController(TeamSummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @PostMapping
    public ResponseEntity<TeamSummaryRecord> save(
            @RequestBody TeamSummaryRecord summary) {
        return ResponseEntity.ok(summaryService.saveSummary(summary));
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryRecord>> getAll() {
        return ResponseEntity.ok(summaryService.getAllSummaries());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<TeamSummaryRecord>> getByDate(
            @PathVariable LocalDate date) {
        return ResponseEntity.ok(summaryService.getSummaryByDate(date));
    }
}
