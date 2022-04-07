package com.jihun.chat_bot.checker.cmd;

public class CalCmdChecker extends BasicCmdChecker {
    private static final String possibleCmd = "cal";

    public CalCmdChecker(String cmd) {
        super(cmd);
    }

    @Override
    public boolean isNotSupport() {
        return !possibleCmd.equalsIgnoreCase(cmd);
    }
}
