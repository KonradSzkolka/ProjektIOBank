package vod.repositroy;

import vod.model.GuildTransaction;

import java.util.List;

public interface GuildTransactionDao {
    List<GuildTransaction> findAll();
    List<GuildTransaction> findByMember(String accountName);
}
