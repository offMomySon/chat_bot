package com.jihun.chat_bot.checker.meta.system.v3;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import com.jihun.chat_bot.checker.meta.result.MetaCheckResult;
import java.util.Set;

public interface MetaCheckerV3 {
    MetaCheckType check();

    MetaCheckResult createMetaErrorMsg();

    Set<String> getCheckMetas();

    String getMeta();
}
