package com.bizorder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "telegram_bots")
public class TelegramBot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "botId")
    private Integer botId;

    @Column(name = "bot_token")
    private String bot_token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
    private Seller seller;

    // Default constructor
    public TelegramBot() {
    }

    // Constructor
    public TelegramBot(String bot_token, Seller seller) {
        this.bot_token = bot_token;
        this.seller = seller;
    }

    // Getters and Setters
    public Integer getBotId() {
        return botId;
    }

    public void setBotId(Integer botId) {
        this.botId = botId;
    }

    public String getBotToken() {
        return bot_token;
    }

    public void setBotToken(String bot_token) {
        this.bot_token = bot_token;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
