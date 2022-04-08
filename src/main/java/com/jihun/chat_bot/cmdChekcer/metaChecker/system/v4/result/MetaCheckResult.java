package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import org.jetbrains.annotations.NotNull;

public class MetaCheckResult {
    private final Meta meta;
    private final boolean isValid;

    public MetaCheckResult(@NotNull Meta meta, boolean isValid) {
        this.meta = meta;
        this.isValid = isValid;
    }

    public static MetaCheckResult invalid(Meta meta) {
        return new MetaCheckResult(meta, false);
    }

    public static MetaCheckResult valid(Meta meta) {
        return new MetaCheckResult(meta, true);
    }

    public Meta getMeta() {
        return this.meta;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isInvalid() {
        return !isValid();
    }
}
