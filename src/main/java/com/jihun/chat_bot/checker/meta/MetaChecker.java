package com.jihun.chat_bot.checker.meta;

public interface MetaChecker {
    String createErrorMessage();

    boolean isMatch(String[] meta);
}
