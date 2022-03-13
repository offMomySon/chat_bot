package com.jihun.chat_bot.cmdChekcer;

public interface CmdErrorChecker {
    String createErrorMessage(String cmd);
    Boolean isNotSupport(String cmd);
}
