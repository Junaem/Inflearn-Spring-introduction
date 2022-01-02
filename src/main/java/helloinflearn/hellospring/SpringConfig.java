package helloinflearn.hellospring;

import helloinflearn.hellospring.repository.MemberRepository;
import helloinflearn.hellospring.repository.MemoryMemberRepository;
import helloinflearn.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
