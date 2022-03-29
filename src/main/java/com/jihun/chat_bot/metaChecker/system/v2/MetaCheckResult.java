package com.jihun.chat_bot.metaChecker.system.v2;

import java.util.Set;

public class MetaCheckResult {
    private final MetaCheckType metaCheckType;
    private final Set adviceMeta;

    public MetaCheckResult(MetaCheckType metaCheckType, Set adviceMeta) {
        if (metaCheckType == null) {
            throw new RuntimeException("metaCheckType 이 null 입니다.");
        }
        if (adviceMeta == null) {
            throw new RuntimeException("adviceMeta 가 null 입니다.");
        }

        this.metaCheckType = metaCheckType;
        this.adviceMeta = adviceMeta;
    }

    public MetaCheckType getMetaCheckType() {
        return metaCheckType;
    }

    public Set getAdviceMeta() {
        return adviceMeta;
    }
}
