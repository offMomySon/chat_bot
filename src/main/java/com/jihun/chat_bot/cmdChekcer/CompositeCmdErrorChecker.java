package com.jihun.chat_bot.cmdChekcer;

import java.util.Collections;
import java.util.List;

public class CompositeCmdErrorChecker implements CmdErrorChecker {
    private final List<CmdErrorChecker> errorCheckers;

    public CompositeCmdErrorChecker(List<CmdErrorChecker> errorCheckers) {
        this.errorCheckers = Collections.unmodifiableList(errorCheckers);
    }

    @Override
    public String createErrorMessage(String cmd) {
        return errorCheckers.stream()
            .filter(it -> isNotSupport(cmd))
            .findFirst()
            .map(it -> it.createErrorMessage(cmd))
            .orElseThrow(() -> new RuntimeException("에러 메세지 생성에 실패했습니다."));
    }

    @Override
    public Boolean isNotSupport(String cmd) {
        return errorCheckers.stream()
            .filter(it -> it.isNotSupport(cmd))
            .findAny()
            .isPresent();
    }
}
