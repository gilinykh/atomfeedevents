package com.example.atomfeedserver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.syndication.feed.atom.Feed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ict4h.atomfeed.server.repository.AllEventRecords;
import org.ict4h.atomfeed.server.repository.AllEventRecordsQueue;
import org.ict4h.atomfeed.server.service.Event;
import org.ict4h.atomfeed.server.service.EventFeedService;
import org.ict4h.atomfeed.server.service.EventService;
import org.ict4h.atomfeed.server.service.publisher.EventRecordsPublishingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/feeds")
@AllArgsConstructor
public class FeedResource {

    private final EventService eventService;
    private final EventFeedService eventFeedService;
    private final AllEventRecords allEventRecords;
    private final AllEventRecordsQueue allEventRecordsQueue;

    @GetMapping(produces = {MediaType.APPLICATION_ATOM_XML_VALUE})
    public ResponseEntity fetch(@RequestParam("category") String category) {
        Feed feed = eventFeedService.getRecentFeed(URI.create("http://accounts.genezix.com/"), category);
        return ResponseEntity.ok(feed);
    }

    @PostMapping(consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity notify(@RequestBody Entry entry) throws URISyntaxException {
        Event event = new Event(
                UUID.randomUUID().toString(),
                entry.getTitle(),
                LocalDateTime.now(),
                entry.getUrl(),
                entry.getContent(),
                entry.getCategory());
        eventService.notify(event);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity publish() {
        EventRecordsPublishingService.publish(allEventRecords, allEventRecordsQueue);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Getter
    static class Entry {
        private final String title;
        private final String content;
        private final String url;
        private final String category;

        @JsonCreator
        public Entry(String title, String content, String url, String category) {
            this.title = title;
            this.content = content;
            this.url = url;
            this.category = category;
        }
    }
}
