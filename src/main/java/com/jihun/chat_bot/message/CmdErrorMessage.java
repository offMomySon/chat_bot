package com.jihun.chat_bot.message;

public class CmdErrorMessage {
    private static final String postfix = "명령어는 존재하지 않는 명령어 입니다.";
    private static final String type = "warning";
    private final String cmd;

    public CmdErrorMessage(String cmd) {
        this.cmd = cmd;
    }

    public String create(){
        return createErrMsg();
    }

    private String createErrMsg(){
        return type + " :: " + cmd + " " +  postfix;
    }
}
