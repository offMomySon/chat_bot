package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import lombok.NonNull;

public abstract class MetaChecker {
    public MetaCheckResult check(@NonNull Meta meta) {
        if (getMeta().doesNotContain(meta)) {
            return MetaCheckResult.from(getMeta());
        }

        return MetaCheckResult.valid();
    }

    protected abstract Meta getMeta();
}
