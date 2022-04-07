package com.jihun.chat_bot.checker.cmd;

public class SystemCmdChecker extends BasicCmdChecker {
    private static final String possibleCmd = "system";

    public SystemCmdChecker(String cmd) {
        super(cmd);
    }

    @Override
    public boolean isNotSupport() {
        return !possibleCmd.equalsIgnoreCase(cmd);
    }
}
