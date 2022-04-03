package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.Objects;
import java.util.Set;

public abstract class ChainMetaCheckerV3 implements MetaCheckerV3 {
    public static final MetaCheckerV3 END = new MetaCheckerV3() {
        @Override
        public MetaCheckType check() {
            return null;
        }

        @Override
        public MetaErrorMsg createMetaErrorMsg() {
            return null;
        }

        @Override
        public Set<String> getPossibleMeta() {
            return null;
        }
    };

    protected final MetaCheckerV3 next;
    protected final String meta;

    protected ChainMetaCheckerV3(MetaCheckerV3 next, String meta) {
        this.next = validateMetaChecker(next);
        this.meta = validateMeta(meta);
    }

    private MetaCheckerV3 validateMetaChecker(MetaCheckerV3 next){
        if(Objects.isNull(next)){
            throw new RuntimeException("next metaChecker 가 null 입니다.");
        }
        return next;
    }

    private String validateMeta(String meta){
        if(Objects.isNull(meta)){
            throw new RuntimeException("meta 가 null 입니다.");
        }
        return meta;
    }

    @Override
    public MetaErrorMsg createMetaErrorMsg() {
        MetaCheckType check = check();
        if (check == MetaCheckType.MATCH_FAIL_PARTLY_MATCHED) {
            return MetaErrorMsg.create(meta, getPossibleMeta());
        }
        if (check == MetaCheckType.MATCH_FAIL_TOTALLY) {
            return MetaErrorMsg.create(meta, getPossibleMeta());
        }

        return next.createMetaErrorMsg();
    }
}
