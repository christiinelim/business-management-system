package com.bizorder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.response.ResponseHandler;

@RestController
@RequestMapping("/telegrambot")
public class TelegramBotController {
    
    @GetMapping("{botId}")
    public ResponseEntity<Object> getBot(@PathVariable("botId") Integer botId) {
        try {
            return ResponseHandler.responseBuilder("Requested telegram bot details retrieved", HttpStatus.OK, getBot(botId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting telegram bot details: " + e.getMessage());
        }
    }
}
