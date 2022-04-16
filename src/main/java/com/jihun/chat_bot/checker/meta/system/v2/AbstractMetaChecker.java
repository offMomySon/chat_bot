package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.result.MetaCheckResult;
import java.util.Objects;

public abstract class AbstractMetaChecker implements MetaChecker {
    protected final MetaChecker nextMetaChecker;
    protected final String meta;

    public AbstractMetaChecker(MetaChecker nextMetaChecker, String meta) {
        this.nextMetaChecker = nextMetaChecker;
        this.meta = validateMeta(meta);
    }

    private String validateMeta(String meta) {
        if (Objects.isNull(meta)) {
            throw new RuntimeException("meta 가 null 입니다.");
        }
        if (meta.isBlank() || meta.isEmpty()) {
            throw new RuntimeException("meta 가 존재하지 않습니다.");
        }
        return meta;
    }

    @Override
    public MetaCheckResult check() {
        if (lastChecker()) {
            return MetaCheckResult.success();
        }
        if (notContain()) {
            return MetaCheckResult.fail(getMeta());
        }

        return nextMetaChecker.check();
    }

    private boolean lastChecker() {
        return Objects.isNull(nextMetaChecker);
    }

    private boolean notContain() {
        return getMeta().contains(meta);
    }
}
