//package com.jihun.chat_bot.checker.meta.system.v3;
//
//import com.jihun.chat_bot.checker.meta.MetaCheckType;
//import com.jihun.chat_bot.checker.meta.result.MetaCheckResult;
//import java.util.Objects;
//import java.util.Set;
//
//public abstract class ChainMetaCheckerV3 implements MetaCheckerV3 {
//    public static final MetaCheckerV3 EMPTY = new MetaCheckerV3() {
//        @Override
//        public MetaCheckType check() {
//            return null;
//        }
//
//        @Override
//        public MetaCheckResult createMetaErrorMsg() {
//            return null;
//        }
//
//        @Override
//        public Set<String> getCheckMetas() {
//            return null;
//        }
//
//        @Override
//        public String getMeta() {
//            return null;
//        }
//    };
//
//    protected final String meta;
//    protected final MetaCheckerV3 next;
//
//    public ChainMetaCheckerV3(String meta, MetaCheckerV3 next) {
//        this.meta = validateMeta(meta);
//        this.next = validateMetaChecker(next);
//    }
//
//    private MetaCheckerV3 validateMetaChecker(MetaCheckerV3 next) {
//        if (Objects.isNull(next)) {
//            throw new RuntimeException("next metaChecker 가 null 입니다.");
//        }
//        return next;
//    }
//
//    private String validateMeta(String meta) {
//        if (Objects.isNull(meta)) {
//            throw new RuntimeException("meta 가 null 입니다.");
//        }
//        if (meta.isEmpty()) {
//            throw new RuntimeException("meta 가 empty 입니다.");
//        }
//        if (meta.isBlank()) {
//            throw new RuntimeException("meta 가 blank 입니다.");
//        }
//        return meta;
//    }
//
//    @Override
//    public String getMeta() {
//        return meta;
//    }
//
//    @Override
//    public MetaCheckResult createMetaErrorMsg() {
//        if (next == EMPTY) {
//            return MetaCheckResult.EMPTY;
//        }
//
//        MetaCheckType check = check();
//
//        if (check == MetaCheckType.MATCH_FAIL_PARTLY_MATCHED) {
//            return MetaCheckResult.create(meta, getCheckMetas());
//        }
//        if (check == MetaCheckType.MATCH_FAIL_TOTALLY) {
//            return MetaCheckResult.create(meta, getCheckMetas());
//        }
//        return next.createMetaErrorMsg();
//    }
//}
