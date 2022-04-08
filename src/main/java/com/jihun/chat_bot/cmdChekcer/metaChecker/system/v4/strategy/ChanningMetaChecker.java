package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.strategy;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result.MetaCheckResult;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.MetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.MetaProvider;
import lombok.NonNull;

import java.util.Objects;

public class ChanningMetaChecker {
    private final MetaChecker metaChecker;
    private final ChanningMetaChecker nextChanningMetaChecker;

    public ChanningMetaChecker(@NonNull MetaChecker metaChecker, ChanningMetaChecker nextChanningMetaChecker) {
        this.metaChecker = metaChecker;
        this.nextChanningMetaChecker = nextChanningMetaChecker;
    }

    public static ChanningMetaChecker last(MetaChecker metaChecker) {
        return new ChanningMetaChecker(metaChecker, null);
    }

    public MetaCheckResult next(MetaProvider metaProvider) {
        Meta meta = metaProvider.peek();
        System.out.println("peek: " + meta);

        MetaCheckResult metaCheckResult = this.metaChecker.check(meta);
        System.out.println("metaChecker= "+metaChecker.getClass().getSimpleName()+", result: " + metaCheckResult.isValid());

        if (doesNotSkip(metaCheckResult)) {
            metaProvider.poll();
        }

        if (shouldDoNext(metaCheckResult)) {
            return nextChanningMetaChecker.next(metaProvider);
        }

        return metaCheckResult;
    }

    private boolean doesNotSkip(MetaCheckResult metaCheckResult) {
        return metaCheckResult.isValid() && !metaCheckResult.getMeta().isAnyOneMatch();
    }

    private boolean shouldDoNext(MetaCheckResult metaCheckResult) {
        return metaCheckResult.isValid() && Objects.nonNull(nextChanningMetaChecker);
    }
}
