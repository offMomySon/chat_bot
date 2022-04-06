package com.jihun.chat_bot.checker.cmd;

import com.jihun.chat_bot.message.CmdErrorMessage;

public class DateCmdChecker implements CmdErrorChecker {
    private static final String possibleCmd = "date";

    @Override
    public String createErrorMessage(String cmd) {
        if (!isNotSupport(cmd)) {
            throw new RuntimeException("가능한 명령어 입니다.");
        }

        return new CmdErrorMessage(cmd).create();
    }

    @Override
    public Boolean isNotSupport(String cmd) {
        return !possibleCmd.equalsIgnoreCase(cmd);
    }
}
