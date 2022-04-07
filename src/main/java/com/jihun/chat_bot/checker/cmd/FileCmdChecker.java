package com.jihun.chat_bot.checker.cmd;

public class FileCmdChecker extends BasicCmdChecker {
    private static final String possibleCmd = "file";

    public FileCmdChecker(String cmd) {
        super(cmd);
    }

    @Override
    public boolean isNotSupport() {
        return !possibleCmd.equalsIgnoreCase(cmd);
    }
}
