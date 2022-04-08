package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result.MetaCheckResult;
import lombok.NonNull;

public abstract class MetaChecker {
    public MetaCheckResult check(@NonNull Meta meta) {
        if (getMeta().doesNotContain(meta)) {
            return MetaCheckResult.invalid(getMeta());
        }

        return MetaCheckResult.valid(meta);
    }

    protected abstract Meta getMeta();
}
