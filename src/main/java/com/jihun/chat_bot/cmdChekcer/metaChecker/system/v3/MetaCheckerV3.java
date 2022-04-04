package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.Set;

public interface MetaCheckerV3 {
    MetaCheckType check();

    MetaErrorMsg createMetaErrorMsg();

    Set<String> getCheckMetas();

    String getMeta();
}
