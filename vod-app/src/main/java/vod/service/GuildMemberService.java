package vod.service;

import vod.model.GuildMember;

import java.util.List;
import java.util.Optional;

public interface GuildMemberService {
    List<GuildMember> getAllMembers();
    Optional<GuildMember> findByAccountName(String accountName);
}
