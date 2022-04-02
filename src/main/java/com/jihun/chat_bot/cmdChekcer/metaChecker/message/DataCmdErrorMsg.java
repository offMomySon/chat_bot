package com.jihun.chat_bot.cmdChekcer.metaChecker.message;

import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

public class DataCmdErrorMsg {
    private static final String FORMAT = "error: `%s`는 올바르지 않은 메타 데이터 입니다. \n" +
        "다음의 메타 데이터중 하나를 선택해주세요. \n" +
        "[%s]";
    private static final String DELIMITER = "[";
    private static final String PREFIX = "|";
    private static final String SUFFIX = "]";

    public static String create(@NonNull String errorMeta, @NonNull List<String> _usableMetas) {
        String usableMetas = _usableMetas.stream().collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));

        return create(errorMeta, usableMetas);
    }

    public static String create(@NonNull String errorMeta, @NonNull String advice) {

        return String.format(FORMAT, errorMeta, advice);
    }
}
