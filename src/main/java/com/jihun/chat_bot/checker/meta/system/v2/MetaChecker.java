package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import com.jihun.chat_bot.message.MetaErrorMsg;
import java.util.Set;

public interface MetaChecker {
    MetaCheckType check();

    Set<String> getPossibleMeta();

    MetaErrorMsg createErrorMsg();
}
