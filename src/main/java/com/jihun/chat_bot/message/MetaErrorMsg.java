package com.jihun.chat_bot.message;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class MetaErrorMsg {
    private static final String format = "error: `%s`는 올바르지 않은 메타 데이터 입니다. \n다음의 메타 데이터중 하나를 선택해주세요. \n %s";

    public static final MetaErrorMsg EMPTY = new MetaErrorMsg("EMPTY");

    private final String value;

    protected MetaErrorMsg(String value) {
        if (Objects.isNull(value)) {
            throw new RuntimeException("value 가 null 입니다.");
        }
        this.value = value;
    }

    public static MetaErrorMsg create(String meta, Set<String> _advice) {
        _advice = validateAdvices(meta, _advice);
        String advice = convertAdvices(_advice);

        return new MetaErrorMsg(String.format(format, meta, advice));
    }

    @NotNull
    private static String convertAdvices(Set<String> _advice) {
        return _advice.stream().collect(Collectors.joining("|", "[", "]"));
    }

    @NotNull
    private static Set<String> validateAdvices(String meta, Set<String> _advice) {
        if (Objects.isNull(meta)) {
            throw new RuntimeException("value 가 null 입니다.");
        }
        if (Objects.isNull(_advice)) {
            throw new RuntimeException("_advice 가 null 입니다.");
        }
        _advice = _advice.stream()
            .filter(a -> Objects.nonNull(a) && !a.isEmpty() && !a.isBlank()).collect(
                Collectors.toSet());

        if (_advice.isEmpty()) {
            throw new RuntimeException("_advice 가 비어 있습니다.");
        }
        return _advice;
    }

    @Override
    public String toString() {
        return "MetaErrorMsg{" +
            "value='" + value + '\'' +
            '}';
    }
}
