package com.jihun.chat_bot.checker.cmd;

import com.jihun.chat_bot.message.CmdErrorMessage;

public interface CmdChecker {
    CmdErrorMessage createErrorMessage();

    boolean isNotSupport();
}
