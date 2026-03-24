package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.GuildMember;
import vod.repository.GuildMemberDao;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class DataGuildMemberDao implements GuildMemberDao {

    private final GuildMemberRepository repo;

    @Override
    public List<GuildMember> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<GuildMember> findByAccountName(String accountName) {
        return repo.findByAccountName(accountName);
    }
}
