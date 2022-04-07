package com.jihun.chat_bot.message;

import java.util.Objects;

public class CmdErrorMessage {
    private static final String format = "warning: `%s` 명령어는 존재하지 않는 명령어 입니다.";

    private final String value;

    public CmdErrorMessage(String value) {
        this.value = validateValue(value);
    }

    private String validateValue(String value) {
        if (Objects.isNull(value)) {
            throw new RuntimeException("value 가 null 입니다.");
        }
        return value;
    }


}
