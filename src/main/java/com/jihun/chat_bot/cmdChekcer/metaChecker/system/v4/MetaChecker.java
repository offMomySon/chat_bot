package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import lombok.NonNull;

public interface MetaChecker {
    MetaCheckResult check(@NonNull Meta meta);
}
