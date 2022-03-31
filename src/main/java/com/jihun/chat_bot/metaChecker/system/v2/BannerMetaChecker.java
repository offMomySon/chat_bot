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

public class BannerMetaChecker {
    public static final BannerMetaChecker EMPTY = new BannerMetaChecker(List.of("EMPTY"));

    private static final Set<String> MATCHER = Set.of("b", "banner");
    private static final int META_POSITION = 0;
    private static final int MAX_META_LENGTH = 2;

    private final List<String> metas;

    public BannerMetaChecker(List<String> _metas) {
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
        if (metas.size() > MAX_META_LENGTH) {
            return MATCH_FAIL_TOTALLY;
        }

        if (isTwoLengthMeta()) {
            return MATCH_SUCCESS;
        }

        if (isOneLengthMeta()) {
            return MATCH_SUCCESS;
        }

        if (isMetaPartlyMatched()) {
            return MATCH_FAIL_PARTLY_MATCHED;
        }

        return MATCH_FAIL_TOTALLY;
    }

    private boolean isOneLengthMeta() {
        return metas.size() == 1;
    }

    private boolean isTwoLengthMeta() {
        return metas.size() == 2 && MATCHER.contains(metas.get(META_POSITION));
    }

    private boolean isMetaPartlyMatched() {
        String meta = metas.get(META_POSITION);

        return MATCHER.stream().anyMatch(m -> m.charAt(0) == meta.charAt(0));
    }

    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
