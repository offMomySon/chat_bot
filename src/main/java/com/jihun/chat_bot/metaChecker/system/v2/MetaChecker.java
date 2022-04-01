package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.MetaCheckType;
import com.jihun.chat_bot.metaChecker.message.MetaErrorMsg;
import java.util.List;
import java.util.Set;

public interface MetaChecker {
    MetaCheckType check(List<String> metas);

    Set<String> getPossibleMeta();

    MetaErrorMsg createErrorMsg(List<String> metas);
}
