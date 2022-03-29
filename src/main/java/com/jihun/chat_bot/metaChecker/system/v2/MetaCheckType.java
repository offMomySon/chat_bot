package com.jihun.chat_bot.metaChecker.system.v2;

public enum MetaCheckType {
    MATCH_FAIL_TOTALLY("meta cmd totally fail"),
    MATCH_FAIL_PARTLY_MATCHED("meta cmd partly fail"),
    MATCH_SUCCESS("meta cmd match success");

    private final String type;

    MetaCheckType(String type) {
        this.type = type;
    }
}
