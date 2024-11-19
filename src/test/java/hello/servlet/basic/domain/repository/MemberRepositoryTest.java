package hello.servlet.basic.domain.repository;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member("hello", 20);

        // when
        Member saved = memberRepository.save(member);

        // then
        Member found=memberRepository.findById(saved.getId());
        Assertions.assertThat(found).isEqualTo(member);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> members = memberRepository.findAll();

        // then
        Assertions.assertThat(members.size()).isEqualTo(2);
        Assertions.assertThat(members).contains(member1, member2);
    }
}