package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.List;

public class FileMetaChecker extends MetaChecker {
    @Override
    protected Collection<String> getMetas() {
        return List.of("f", "file");
    }
}
