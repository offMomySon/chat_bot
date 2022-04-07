package com.jihun.chat_bot.checker.cmd;

import com.jihun.chat_bot.message.CmdErrorMessage;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeCmdChecker implements CmdChecker {
    private final List<CmdChecker> cmdCheckers;

    public CompositeCmdChecker(List<CmdChecker> cmdCheckers) {
        this.cmdCheckers = validateCmdChecker(cmdCheckers);
    }

    private List<CmdChecker> validateCmdChecker(List<CmdChecker> cmdCheckers) {
        if (Objects.isNull(cmdCheckers)) {
            throw new RuntimeException("error checker 가 null 입니다.");
        }

        cmdCheckers = Collections.unmodifiableList(
            cmdCheckers.stream()
                .filter(it -> Objects.nonNull(it)).collect(Collectors.toList())
        );

        if (cmdCheckers.isEmpty()) {
            throw new RuntimeException("cmdCheckers 가 비었습니다.");
        }

        return cmdCheckers;
    }

    @Override
    public CmdErrorMessage createErrorMessage() {
        if (!isNotSupport()) {
            throw new RuntimeException("사용 가능한 cmd 가 있습니다.");
        }

        return cmdCheckers.stream()
            .filter(it -> isNotSupport())
            .findFirst()
            .map(it -> it.createErrorMessage())
            .orElseThrow(() -> new RuntimeException("에러 메세지 생성에 실패했습니다."));
    }

    @Override
    public boolean isNotSupport() {
        return !cmdCheckers.stream()
            .anyMatch(it -> it.isNotSupport() == false);
    }
}
