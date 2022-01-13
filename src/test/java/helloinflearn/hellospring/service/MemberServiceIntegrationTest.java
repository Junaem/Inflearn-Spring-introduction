package helloinflearn.hellospring.service;

import helloinflearn.hellospring.domain.Member;
import helloinflearn.hellospring.repository.MemberRepository;
import helloinflearn.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // db에 데이터를 넣고 나서 롤백을 시켜주기 때문에 테스트가 db에 영향을 미치지 않음
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given 사용하는 데이터
        Member member = new Member();
        member.setName("spring");

        //when 테스트하는 상황
        Long saveId = memberService.join(member);

        //then 나오는 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");
        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        // 람다식이 실행될 때 IllegalSE 예외가 터지면 ㅇㅋ
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}