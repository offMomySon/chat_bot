//package com.jihun.chat_bot.checker.meta.system.v3;
//
//import com.jihun.chat_bot.checker.meta.MetaCheckType;
//import java.util.Set;
//
//public class TextMetaCheckerV3 extends ChainMetaCheckerV3 {
//    private static final Set<String> MATCHER = Set.of("TXT");
//
//    public TextMetaCheckerV3(String meta, MetaCheckerV3 next) {
//        super(meta, next);
//    }
//
//    @Override
//    public MetaCheckType check() {
//        return MetaCheckType.MATCH_SUCCESS;
//    }
//
//    @Override
//    public Set<String> getCheckMetas() {
//        return MATCHER;
//    }
//}
