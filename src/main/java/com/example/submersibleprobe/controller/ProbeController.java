package com.example.submersibleprobe.controller;

import com.example.submersibleprobe.service.ProbeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probe")
public class ProbeController {
    private final ProbeService probeService;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/move")
    public String move(@RequestBody String commands) {
        return probeService.executeCommands(commands);
    }

    @GetMapping("/status")
    public String status() {
        return probeService.getCurrentPosition();
    }

    @GetMapping("/visited")
    public Object visitedCoordinates() {
        return probeService.getVisitedCoordinates();
    }
}
