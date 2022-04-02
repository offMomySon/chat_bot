package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMetaChecker implements MetaChecker {
    protected final List<MetaChecker> nextMetaCheckers;
    protected static final int META_POSITION = 0;

    protected AbstractMetaChecker(List<MetaChecker> nextMetaCheckers) {
        this.nextMetaCheckers = validateMetaCheckers(nextMetaCheckers);
    }

    private List<MetaChecker> validateMetaCheckers(List<MetaChecker> nextMetaCheckers) {
        if (Objects.isNull(nextMetaCheckers)) {
            throw new RuntimeException("nextMetaCheckers 가 null 입니다.");
        }

        return Collections.unmodifiableList(
            nextMetaCheckers.stream().filter(Objects::nonNull).collect(Collectors.toList()));
    }

    protected abstract boolean isMetaPartlyMatched(List<String> metas);

    @Override
    public MetaErrorMsg createErrorMsg(List<String> metas) {
        if (Objects.isNull(metas)) {
            throw new RuntimeException("metas 가 null 입니다.");
        }

        if (nextMetaCheckers.isEmpty() && metas.size() == 1) {
            return MetaErrorMsg.EMPTY;
        }

        Set<String> partlyMatchedMetas = new HashSet<>();
        Set<String> totallyFailedMetas = new HashSet<>();

        for (MetaChecker metaChecker : nextMetaCheckers) {
            MetaCheckType check = metaChecker.check(metas.subList(1, metas.size()));

            if (check == MetaCheckType.MATCH_SUCCESS) {
                return metaChecker.createErrorMsg(metas.subList(1, metas.size()));
            }

            if (check == MetaCheckType.MATCH_FAIL_PARTLY_MATCHED) {
                partlyMatchedMetas.addAll(metaChecker.getPossibleMeta());
            }

            if (check == MetaCheckType.MATCH_FAIL_TOTALLY) {
                totallyFailedMetas.addAll(metaChecker.getPossibleMeta());
            }
        }

        if (partlyMatchedMetas.isEmpty()) {
            return MetaErrorMsg.create(metas.get(0), totallyFailedMetas);
        }

        return MetaErrorMsg.create(metas.get(0), partlyMatchedMetas);
    }
}
