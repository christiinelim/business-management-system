package com.bizorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bizorder.model.TelegramBot;

public interface TelegramBotRepository extends JpaRepository <TelegramBot, Integer>{
    
}
