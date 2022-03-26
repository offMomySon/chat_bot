package com.jihun.chat_bot.metaChecker;

public interface MetaChecker {
    String createErrorMessage();

    boolean isMatch(String[] meta);
}
