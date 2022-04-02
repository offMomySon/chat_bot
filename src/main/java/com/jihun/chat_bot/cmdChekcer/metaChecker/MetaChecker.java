package com.jihun.chat_bot.cmdChekcer.metaChecker;

public interface MetaChecker {
    String createErrorMessage();

    boolean isMatch(String[] meta);
}
