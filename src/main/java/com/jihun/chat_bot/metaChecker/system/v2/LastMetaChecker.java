package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.message.MetaErrorMsg;
import java.util.List;

public abstract class LastMetaChecker extends AbstractMetaChecker {

    @Override
    public MetaErrorMsg createErrorMsg(List<String> metas) {
        return MetaErrorMsg.EMPTY;
    }
}
