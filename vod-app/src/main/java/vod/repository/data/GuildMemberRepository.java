package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.GuildMember;

import java.util.Optional;

public interface GuildMemberRepository extends JpaRepository<GuildMember, Long> {

    Optional<GuildMember> findByAccountName(String accountName);
}
