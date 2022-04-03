package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.Set;

public interface MetaChecker {
    MetaCheckType check();

    Set<String> getPossibleMeta();

    MetaErrorMsg createErrorMsg();
}
