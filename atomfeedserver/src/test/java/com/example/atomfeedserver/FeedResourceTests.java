package com.example.atomfeedserver;

import org.ict4h.atomfeed.server.service.EventFeedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class FeedResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventFeedService eventFeedService;

    @Test
    public void eventPublication() throws Exception {
        mockMvc.perform(post("/feeds")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Event 1\", \"url\":\"http://resource-server.net/resources/1\",\"category\":\"resource\",\"content\":\"{\\\"username\\\":\\\"john\\\", \\\"role\\\":\\\"editor\\\"}\"}"))
                .andDo(print())
                .andExpect(status().isCreated());

        eventFeedService.getRecentFeed(URI.create("http://resource-server.net/resources/1"), "resource");
    }
}
