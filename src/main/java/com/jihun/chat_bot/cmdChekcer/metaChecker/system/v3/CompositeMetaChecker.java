package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_SUCCESS;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3.ChainMetaCheckerV3.EMPTY;

public class CompositeMetaChecker implements MetaCheckerV3 {
    private final List<MetaCheckerV3> metaCheckers;
    private MetaCheckerV3 successChecker = EMPTY;

    private CompositeMetaChecker(List<MetaCheckerV3> metaCheckers) {
        this.metaCheckers = validateMetaCheckers(metaCheckers);
    }

    private List<MetaCheckerV3> validateMetaCheckers(List<MetaCheckerV3> metaCheckers) {
        if (Objects.isNull(metaCheckers)) {
            throw new RuntimeException("metaChecker 가 null 입니다.");
        }

        metaCheckers = Collections.unmodifiableList(
            metaCheckers.stream()
                .filter(ch -> Objects.nonNull(ch))
                .collect(Collectors.toList())
        );

        if (metaCheckers.isEmpty()) {
            throw new RuntimeException("metaCheckers 가 존재하지 않습니다.");
        }

        return metaCheckers;
    }

    @Override
    public MetaCheckType check() {
        boolean isPartlyMatched = false;

        for (MetaCheckerV3 checker : metaCheckers) {
            MetaCheckType metaCheckType = checker.check();

            if (metaCheckType == MATCH_SUCCESS) {
                successChecker = checker;
                return MATCH_SUCCESS;
            }

            if (metaCheckType == MATCH_FAIL_PARTLY_MATCHED) {
                isPartlyMatched = true;
            }
        }

        if (isPartlyMatched) {
            return MATCH_FAIL_PARTLY_MATCHED;
        }

        return MATCH_FAIL_TOTALLY;
    }

    @Override
    public MetaErrorMsg createMetaErrorMsg() {
        MetaCheckType type = check();

        if (type == MATCH_SUCCESS) {
            if (successChecker == EMPTY) {
                throw new RuntimeException("할당된 success checker 가 존재 하지 않습니다.");
            }
            return successChecker.createMetaErrorMsg();
        }

        if (type == MATCH_FAIL_PARTLY_MATCHED) {
            return MetaErrorMsg.create(getMeta(), getPartlyMatchedCheckMetas());
        }

        return MetaErrorMsg.create(getMeta(), getTotallyFailedCheckMetas());
    }

    private Set<String> getPartlyMatchedCheckMetas() {
        return metaCheckers.stream()
            .filter(ch -> ch.check() == MATCH_FAIL_PARTLY_MATCHED)
            .map(ch -> ch.getCheckMetas())
            .flatMap(metas -> metas.stream())
            .collect(Collectors.toSet());
    }

    private Set<String> getTotallyFailedCheckMetas() {
        return metaCheckers.stream()
            .filter(ch -> ch.check() == MATCH_FAIL_TOTALLY)
            .map(ch -> ch.getCheckMetas())
            .flatMap(metas -> metas.stream())
            .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getCheckMetas() {
        return null;
    }

    @Override
    public String getMeta() {
        return metaCheckers.stream()
            .map(ch -> ch.getMeta())
            .filter(meta -> Objects.nonNull(meta) && !StringUtils.isEmpty(meta) && !StringUtils.isBlank(meta))
            .findAny()
            .orElseThrow(() -> new RuntimeException("composited 하위의 checker 가 가지고 있는 meta 가 존재하지 않습니다."));
    }

    public static class Builder {
        private final List<MetaCheckerV3> metaCheckerV3s = new ArrayList<>();

        public Builder nextChecker(MetaCheckerV3 next) {
            metaCheckerV3s.add(next);
            return this;
        }

        public CompositeMetaChecker build() {
            return new CompositeMetaChecker(metaCheckerV3s);
        }
    }
}
