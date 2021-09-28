package com.example.assessment.Model;

public class ModelChat {
    String title,description, sender;

    public ModelChat(String title, String description, String sender) {
        this.title = title;
        this.description = description;
        this.sender = sender;
    }

    public ModelChat() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

