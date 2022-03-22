package com.jihun.chat_bot.metaChecker;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.math.NumberUtils;

public class DateMetaChecker {
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Set<String> metaCommand = Set.of("c", "cal", "calculate");
    private static final Set<String> dayOperator = Set.of("+", "-");

    public boolean isMatch(String[] meta) {
        if (isSimpleMeta(meta)) {
            return true;
        }

        if (!metaCommand.contains(meta[0])) {
            return false;
        }

        if (isSpecificDateMeta(meta)) {
            return true;
        }

        if (isCurrentDateMeta(meta)) {
            return true;
        }

        return false;
    }

    private boolean isSimpleMeta(String[] meta) {
        if (!(meta.length == 1)) {
            return false;
        }

        return DATE_PATTERN.matcher(meta[0]).matches();
    }

    private boolean isSpecificDateMeta(String[] meta) {
        if (!(3 <= meta.length || meta.length <= 4)) {
            return false;
        }

        if (DATE_PATTERN.matcher(meta[1]).matches()) {
            return operateChecker(meta[2], Arrays.copyOfRange(meta, 3, meta.length));
        }

        return false;
    }

    private boolean isCurrentDateMeta(String[] meta) {
        if (!(2 <= meta.length && meta.length <= 3)) {
            return false;
        }

        return operateChecker(meta[1], Arrays.copyOfRange(meta, 2, meta.length)) ? true : false;
    }

    private boolean operateChecker(String operator, String[] leftMeta) {
        if (!dayOperator.contains(operator)) {
            return false;
        }

        if (leftMeta.length == 0) {
            return true;
        }
        String operand = leftMeta[0];
        if (leftMeta.length == 1 && NumberUtils.isCreatable(operand)) {
            return true;
        }

        return false;
    }
}
