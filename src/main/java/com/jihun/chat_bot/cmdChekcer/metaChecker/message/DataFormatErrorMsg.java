package com.jihun.chat_bot.cmdChekcer.metaChecker.message;

public class DataFormatErrorMsg {
    private static final String ERROR_FORMAT = "`%s`는 올바르지 않는 포맷입니다.\n다음과 같이 포맷 양식을 지켜주세요.\n`숫자 데이터만 가능합니다.`";

    public static String create(String data) {
        return String.format(ERROR_FORMAT, data);
    }
}
