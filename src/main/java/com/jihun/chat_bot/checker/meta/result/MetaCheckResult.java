package com.jihun.chat_bot.checker.meta.result;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MetaCheckResult {
    private static final String format = "error: `%s`는 올바르지 않은 메타 데이터 입니다. \n다음의 메타 데이터중 하나를 선택해주세요. \n %s";
    private static final int PARTIALLY_MATCH_LENGTH = 1;

    private final Set<String> adviceMeta;
    private final boolean isFail;

    private MetaCheckResult(Set<String> adviceMeta, boolean isFail) {
        this.adviceMeta = validateAdvice(adviceMeta);
        this.isFail = isFail;
    }

    private Set<String> validateAdvice(Set<String> adviceMeta) {
        if (Objects.isNull(adviceMeta)) {
            throw new RuntimeException("adviceMeta 가 null 입니다.");
        }

        return adviceMeta.stream()
            .filter(it -> Objects.nonNull(it) || it.isBlank() || it.isEmpty())
            .collect(Collectors.toSet());
    }

    public static MetaCheckResult fail(Set<String> adviceMeta) {
        return new MetaCheckResult(adviceMeta, true);
    }

    public static MetaCheckResult success() {
        return new MetaCheckResult(Collections.emptySet(), false);
    }

    private boolean isFail() {
        return isFail == true || !adviceMeta.isEmpty();
    }

    public boolean failed() {
        return isFail();
    }

    public String createErrorMessage(String givenMeta) {
        Set<String> partiallyMatchMeta = getPartiallyMatchMeta(givenMeta);
        if (!partiallyMatchMeta.isEmpty()) {
            return create(givenMeta, partiallyMatchMeta);
        }

        return create(givenMeta);
    }

    private Set<String> getPartiallyMatchMeta(String given) {
        return adviceMeta.stream()
            .filter(advice -> isPartiallyMatch(advice, given))
            .collect(Collectors.toSet());
    }

    private boolean isPartiallyMatch(String advice, String given) {
        for (int i = 0; i < PARTIALLY_MATCH_LENGTH; i++) {
            if (advice.charAt(i) != given.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private String create(String given) {
        return create(given, adviceMeta);
    }

    private String create(String given, Set<String> advice) {
        return String.format(format, given, decorate(advice));
    }

    private String decorate(Set<String> advice) {
        return advice.stream().collect(Collectors.joining("|", "[", "]"));
    }

    @Override
    public String toString() {
        return "MetaCheckResult{" +
            "adviceMeta=" + adviceMeta +
            ", isValid=" + isFail +
            '}';
    }
}
