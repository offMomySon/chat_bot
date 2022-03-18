package com.jihun.chat_bot.message;

public abstract class Message {
    private final String type;
    private final String content;

    protected Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String create(){
        return type + " :: " + content;
    }
}
