package com.example.atomfeedclient;

import lombok.AllArgsConstructor;
import org.ict4h.atomfeed.client.service.FeedClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeds")
@AllArgsConstructor
public class FeedProcessingResource {

    private final FeedClient feedClient;

    @PostMapping("/success")
    public ResponseEntity processEvents() {
        feedClient.processEvents();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/error")
    public ResponseEntity processFailedEvents() {
        feedClient.processFailedEvents();
        return ResponseEntity.ok().build();
    }
}
