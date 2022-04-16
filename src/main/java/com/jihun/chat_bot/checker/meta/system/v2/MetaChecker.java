package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.result.MetaCheckResult;
import java.util.Set;

public interface MetaChecker {
    Set<String> getMeta();

    MetaCheckResult check();
}
