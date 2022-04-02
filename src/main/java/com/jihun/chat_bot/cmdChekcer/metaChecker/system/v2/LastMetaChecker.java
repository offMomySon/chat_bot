package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.List;

public abstract class LastMetaChecker extends AbstractMetaChecker {

    @Override
    public MetaErrorMsg createErrorMsg(List<String> metas) {
        return MetaErrorMsg.EMPTY;
    }
}
