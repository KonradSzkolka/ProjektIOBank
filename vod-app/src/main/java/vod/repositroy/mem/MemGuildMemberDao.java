package vod.repositroy.mem;

import org.springframework.stereotype.Repository;
import vod.repositroy.GuildMemberDao;
import vod.model.GuildMember;
import java.util.List;
import java.util.Optional;

@Repository
public class MemGuildMemberDao implements GuildMemberDao {

    private final List<GuildMember> members = SampleData.sampleMembers();

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
