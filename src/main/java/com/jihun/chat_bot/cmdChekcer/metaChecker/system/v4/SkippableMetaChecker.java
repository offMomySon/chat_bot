package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result.MetaCheckResult;
import lombok.NonNull;

public class SkippableMetaChecker extends MetaChecker {
    private static final Meta META = Meta.anyOneMatch();

    private final MetaChecker metaChecker;

    public SkippableMetaChecker(@NonNull MetaChecker metaChecker) {
        this.metaChecker = metaChecker;
    }

    @Override
    public MetaCheckResult check(@NonNull Meta meta) {
        MetaCheckResult checkResult = metaChecker.check(meta);
        if (checkResult.isValid()) {
            return checkResult;
        }

        return MetaCheckResult.valid(getMeta());
    }

    @Override
    protected Meta getMeta() {
        return META;
    }
}
