package vod.repository;

import vod.model.GuildMember;

import java.util.List;
import java.util.Optional;

public interface GuildMemberDao {
    List<GuildMember> findAll();
    Optional<GuildMember> findByAccountName(String accountName);
}
