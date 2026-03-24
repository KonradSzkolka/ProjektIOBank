//package vod.repository.mem;
//
//import vod.model.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class SampleData {
//
//    public static List<GuildMember> sampleMembers() {
//        GuildMember m1 = new GuildMember("Korcyk.1234", "Leader");
//        GuildMember m2 = new GuildMember("GuildMate.5678", "Member");
//        return List.of(m1, m2);
//    }
//
//    public static List<GuildTransaction> sampleTransactions(List<GuildMember> members) {
//        GuildMember m1 = members.get(0);
//        GuildMember m2 = members.get(1);
//
//        GuildTransaction t1 = new GuildTransaction(
//                1L, m1, null, 0,
//                1000,
//                LocalDateTime.now().minusDays(3),
//                TransactionType.DEPOSIT
//        );
//
//        GuildTransaction t2 = new GuildTransaction(
//                2L, m2, null, 0,
//                500,
//                LocalDateTime.now().minusDays(2),
//                TransactionType.DEPOSIT
//        );
//
//        GuildTransaction t3 = new GuildTransaction(
//                3L, m1, null, 0,
//                200,
//                LocalDateTime.now().minusDays(1),
//                TransactionType.WITHDRAW
//        );
//
//        return List.of(t1, t2, t3);
//    }
//}
