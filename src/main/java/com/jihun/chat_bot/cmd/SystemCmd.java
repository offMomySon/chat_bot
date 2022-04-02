package com.jihun.chat_bot.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SystemCmd {
    private static final int FORE_FRONT_META = 0;
    private static final String NOT_EXIST_META = "";

    private final List<String> values;

    public SystemCmd(List<String> _value) {
        if (Objects.isNull(_value)) {
            throw new RuntimeException("_value 가 null 입니다.");
        }

        this.values = Collections.unmodifiableList(_value.stream()
                                                       .filter(m -> Objects.nonNull(m) && !m.isBlank() && !m.isEmpty())
                                                       .collect(Collectors.toList()));
    }

    
}
