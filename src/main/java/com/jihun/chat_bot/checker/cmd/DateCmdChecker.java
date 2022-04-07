package com.jihun.chat_bot.checker.cmd;

public class DateCmdChecker extends BasicCmdChecker {
    private static final String possibleCmd = "date";

    public DateCmdChecker(String cmd) {
        super(cmd);
    }

    @Override
    public boolean isNotSupport() {
        return !possibleCmd.equalsIgnoreCase(cmd);
    }
}
