package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import lombok.NonNull;

public class ExcludeMetaChecker implements MetaChecker {
    private final MetaChecker metaChecker;

    public ExcludeMetaChecker(@NonNull MetaChecker metaChecker) {
        this.metaChecker = metaChecker;
    }

    @Override
    public MetaCheckResult check(@NonNull Meta meta) {
        MetaCheckResult metaCheckResult = metaChecker.check(meta);
        if (metaCheckResult.isValid() ) {
            if (metaChecker instanceof AbstractMetaChecker) {
                return MetaCheckResult.from(((AbstractMetaChecker)metaChecker).getMeta());
            }

            return MetaCheckResult.from(Meta.from("unknown"));
        }

        return MetaCheckResult.valid();
    }
}
