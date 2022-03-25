package com.jihun.chat_bot.metaChecker;

import java.util.Set;
import org.apache.commons.lang3.math.NumberUtils;

public class CalMetaChecker implements MetaChecker {
    private static final Set<String> operators = Set.of("+", "-", "*", "/");
    private static final int metaLength = 3;

    @Override
    public boolean isMatch(String[] meta) {
        if (meta.length != metaLength) {
            return false;
        }

        String leftOperand = meta[0];
        String operator = meta[1];
        String rightOperand = meta[2];

        if (NumberUtils.isParsable(leftOperand) && NumberUtils.isParsable(rightOperand) &&
            operators.contains(operator)) {
            return true;
        }

        return false;
    }
}
