package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMetaChecker implements MetaChecker {
    protected final List<MetaChecker> nextMetaCheckers;
    protected Queue<String> queue;
//    protected final String meta;

    protected AbstractMetaChecker(List<MetaChecker> nextMetaCheckers, Queue<String> meta) {
        this.nextMetaCheckers = validateMetaCheckers(nextMetaCheckers);
        this.queue = meta;
    }
//
//    private String validateMeta(String meta) {
//        if (Objects.isNull(meta)) {
//            throw new RuntimeException("meta 가 null 입니다.");
//        }
//        if (meta.isBlank() || meta.isEmpty()) {
//            throw new RuntimeException("meta 가 존재하지 않습니다.");
//        }
//
//        return meta;
//    }

    private List<MetaChecker> validateMetaCheckers(List<MetaChecker> nextMetaCheckers) {
        if (Objects.isNull(nextMetaCheckers)) {
            throw new RuntimeException("nextMetaCheckers 가 null 입니다.");
        }

        return Collections.unmodifiableList(
            nextMetaCheckers.stream().filter(Objects::nonNull).collect(Collectors.toList()));
    }

    protected abstract boolean isMetaPartlyMatched();

    @Override
    public MetaErrorMsg createErrorMsg() {
        if (nextMetaCheckers.isEmpty()) {
            return MetaErrorMsg.EMPTY;
        }

        Set<String> partlyMatchedMetas = new HashSet<>();
        Set<String> totallyFailedMetas = new HashSet<>();

        for (MetaChecker metaChecker : nextMetaCheckers) {
            MetaCheckType check = metaChecker.check();

            if (check == MetaCheckType.MATCH_SUCCESS) {
                return metaChecker.createErrorMsg();
            }

            if (check == MetaCheckType.MATCH_FAIL_PARTLY_MATCHED) {
                partlyMatchedMetas.addAll(metaChecker.getPossibleMeta());
            }

            if (check == MetaCheckType.MATCH_FAIL_TOTALLY) {
                totallyFailedMetas.addAll(metaChecker.getPossibleMeta());
            }
        }

        String poll = queue.poll();
        if (partlyMatchedMetas.isEmpty()) {

            return MetaErrorMsg.create(poll, totallyFailedMetas);
        }

        return MetaErrorMsg.create(poll, partlyMatchedMetas);
    }
}
