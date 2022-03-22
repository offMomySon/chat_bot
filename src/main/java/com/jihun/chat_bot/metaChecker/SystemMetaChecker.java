package com.jihun.chat_bot.metaChecker;

import java.util.Set;

public class SystemMetaChecker {


    public String createMetaFailMessage(String _cmd) {
        String[] cmd = _cmd.substring(1).split(" ");
        String firstCmd = cmd[1];

        Set<String> usableFileCmd = Set.of("file", "f", "exit", "e", "update", "u");

        if(usableFileCmd.contains(firstCmd)){
            return null;
        }

        return "error: `"+ cmd[1] +"`는 올바르지 않은 메타 데이터 입니다. 다음의 메타 데이터중 하나를 선택해주세요. [u|update], [f|file], [e|exit]";
    }


}
