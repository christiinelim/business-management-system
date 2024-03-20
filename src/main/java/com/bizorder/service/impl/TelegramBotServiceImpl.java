package com.bizorder.service.impl;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.TelegramBot;
import com.bizorder.repository.TelegramBotRepository;
import com.bizorder.service.TelegramBotService;

public class TelegramBotServiceImpl implements TelegramBotService {

    TelegramBotRepository telegramBotRepository;

    public TelegramBotServiceImpl(TelegramBotRepository telegramBotRepository){
        this.telegramBotRepository = telegramBotRepository;
    }

    @Override
    public TelegramBot getBot(Integer botId) {
        if (telegramBotRepository.findById(botId).isEmpty()) {
            throw new SearchNotFoundException("Requested telegram bot does not exist");
        } else {
            return telegramBotRepository.findById(botId).get();
        }
    }

    // @Override
    // public String createBot(TelegramBot telegramBot) {
        
    // }

    // @Override
    // public String updateBot(TelegramBot telegramBot, Integer botId) {

    // }

    // @Override
    // public String deleteBot(Integer botId) {

    // }
}
