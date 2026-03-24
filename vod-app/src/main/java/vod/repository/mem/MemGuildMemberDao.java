package vod.repository.mem;

import vod.model.GuildMember;
import vod.repository.GuildMemberDao;

import java.util.List;
import java.util.Optional;

// usuń albo zakomentuj adnotację
// @Repository
public class MemGuildMemberDao implements GuildMemberDao {

    private final List<GuildMember> members = List.of(); // może być nawet tak

    @Override
    public List<GuildMember> findAll() {
        return members;
    }

    @Override
    public Optional<GuildMember> findByAccountName(String accountName) {
        return members.stream()
                .filter(m -> m.accountName.equals(accountName))
                .findFirst();
    }
}
