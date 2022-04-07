package com.jihun.chat_bot.checker.cmd;

import com.jihun.chat_bot.message.CmdErrorMessage;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public abstract class BasicCmdChecker implements CmdChecker {
    protected final String cmd;

    protected BasicCmdChecker(String cmd) {
        this.cmd = validateCmd(cmd);
    }

    private String validateCmd(String cmd) {
        if (Objects.isNull(cmd)) {
            throw new RuntimeException("cmd 가 null 입니다.");
        }
        if (StringUtils.isEmpty(cmd)) {
            throw new RuntimeException("cmd 가 empty 입니다.");
        }
        if (StringUtils.isBlank(cmd)) {
            throw new RuntimeException("cmd 가 blank 입니다.");
        }
        return cmd;
    }

    @Override
    public CmdErrorMessage createErrorMessage() {
        if (!isNotSupport()) {
            throw new RuntimeException("가능한 명령어 입니다.");
        }
        return new CmdErrorMessage(cmd);
    }
}
