package com.jihun.chat_bot.checker.cmd;

public interface CmdErrorChecker {
    String createErrorMessage(String cmd);

    Boolean isNotSupport(String cmd);
}
