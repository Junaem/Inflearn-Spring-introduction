package helloinflearn.hellospring.service;

import helloinflearn.hellospring.domain.Member;
import helloinflearn.hellospring.repository.MemberRepository;
import helloinflearn.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름 중복 막기 위해서는
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        memberRepository.save(member);
        return member.getId();
    }


}
