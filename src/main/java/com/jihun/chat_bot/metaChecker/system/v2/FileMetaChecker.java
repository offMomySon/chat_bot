package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.MetaCheckType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_SUCCESS;

public class FileMetaChecker {
    public static final FileMetaChecker EMPTY = new FileMetaChecker(List.of("EMPTY"));

    private final static Set<String> MATCHER = Set.of("f", "file");
    private final static int META_POSITION = 0;
    private static final int META_LENGTH = 2;

    private final List<String> metas;

    public FileMetaChecker(List<String> _metas) {
        if (Objects.isNull(_metas)) {
            throw new RuntimeException("입력 받은 metas 가 null 입니다.");
        }

        List<String> metas = _metas.stream()
            .filter(m -> Objects.nonNull(m) && !m.isEmpty() && !m.isBlank())
            .collect(Collectors.toList());

        if (Objects.isNull(metas)) {
            throw new RuntimeException("metas 가 null 입니다.");
        }
        if (metas.isEmpty()) {
            throw new RuntimeException("metas 이 empty 입니다.");
        }

        this.metas = Collections.unmodifiableList(metas);
    }

    private boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isCheckable() {
        return !isEmpty();
    }

    public MetaCheckType check() {
        if (metas.size() != META_LENGTH) {
            return MATCH_FAIL_TOTALLY;
        }

        if (isMeta()) {
            return MATCH_SUCCESS;
        }

        if (isMetaPartlyMatched()) {
            return MATCH_FAIL_PARTLY_MATCHED;
        }

        return MATCH_FAIL_TOTALLY;
    }

    private boolean isMeta() {
        return MATCHER.contains(metas.get(META_POSITION));
    }

    private boolean isMetaPartlyMatched() {
        String meta = metas.get(META_POSITION);

        return MATCHER.stream().anyMatch(m -> m.charAt(0) == meta.charAt(0));
    }

    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
