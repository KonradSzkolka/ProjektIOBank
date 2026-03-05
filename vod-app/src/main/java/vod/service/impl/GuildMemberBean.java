package vod.service.impl;

import org.springframework.stereotype.Service;
import vod.model.GuildMember;
import vod.repositroy.GuildMemberDao;
import vod.service.GuildMemberService;

import java.util.List;
import java.util.Optional;

@Service
public class GuildMemberBean implements GuildMemberService {

    private final GuildMemberDao memberDao;

    public GuildMemberBean(GuildMemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public List<GuildMember> getAllMembers() {
        return memberDao.findAll();
    }

    @Override
    public Optional<GuildMember> findByAccountName(String accountName) {
        return memberDao.findByAccountName(accountName);
    }
}
