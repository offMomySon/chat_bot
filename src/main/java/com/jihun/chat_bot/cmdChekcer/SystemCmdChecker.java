package com.jihun.chat_bot.cmdChekcer;

import com.jihun.chat_bot.message.CmdErrorMessage;

public class SystemCmdChecker implements CmdErrorChecker {
    private static final String possibleCmd = "system";

    @Override
    public String createErrorMessage(String cmd) {
        if(!isNotSupport(cmd)){
            throw new RuntimeException("가능한 명령어 입니다.");
        }

        return new CmdErrorMessage(cmd).create();
    }

    @Override
    public Boolean isNotSupport(String cmd) {
        return !possibleCmd.equalsIgnoreCase(cmd);
    }
}
