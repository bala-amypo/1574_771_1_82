package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService teamSummaryService;

    public TeamSummaryController(TeamSummaryService teamSummaryService) {
        this.teamSummaryService = teamSummaryService;
    }

    @PostMapping("/generate")
    public TeamSummaryRecord generate(@RequestParam String teamName,
                                      @RequestParam LocalDate summaryDate) {
        return teamSummaryService.generateSummary(teamName, summaryDate);
    }

    @GetMapping("/team/{teamName}")
    public List<TeamSummaryRecord> byTeam(@PathVariable String teamName) {
        return teamSummaryService.getSummariesByTeam(teamName);
    }

    @GetMapping
    public List<TeamSummaryRecord> getAll() {
        return teamSummaryService.getAllSummaries();
    }
}
