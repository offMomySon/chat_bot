package com.jihun.chat_bot.checker.meta.cal;

import com.jihun.chat_bot.message.DataCmdErrorMsg;
import com.jihun.chat_bot.message.DataFormatErrorMsg;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.NonNull;
import org.apache.commons.lang3.math.NumberUtils;

public class CalMetaChecker {
    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/");
    private static final int META_LENGTH = 3;

    private final List<String> metas;

    public CalMetaChecker(@NonNull List<String> metas) {
        this.metas = metas;
    }

    public String createErrorMessage() {
        if (isNotMatch()) {
            throw new RuntimeException("정상적인 cal meta date 입니다.");
        }

        String leftOperand = metas.get(0);
        String operator = metas.get(1);
        String rightOperand = metas.get(2);

        if (!NumberUtils.isParsable(leftOperand)) {
            return DataFormatErrorMsg.create(leftOperand);
        }
        if (!NumberUtils.isParsable(rightOperand)) {
            return DataFormatErrorMsg.create(rightOperand);
        }

        return DataCmdErrorMsg.create(operator, new ArrayList<>(OPERATORS));
    }

    public boolean isNotMatch() {
        if (metas.size() != META_LENGTH) {
            return true;
        }

        String leftOperand = metas.get(0);
        String operator = metas.get(1);
        String rightOperand = metas.get(2);

        if (!NumberUtils.isParsable(leftOperand)) {
            return true;
        }
        if (!NumberUtils.isParsable(rightOperand)) {
            return true;
        }
        if (!OPERATORS.contains(operator)) {
            return true;
        }

        return false;
    }
}
