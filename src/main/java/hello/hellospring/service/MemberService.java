package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // JPA를 사용하기 위해서는 Transactional이 필요하다.
public class MemberService {

    /**
    private final MemberRepository memberRepository = new MemoryMemberRepository();
     아래는 같은 인스턴스를 쓰게 변경한 것
     **/

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        /**
        // 같은 이름이 있는 중복 회원 x
        Optional<Member> result = memberRepository.findByName(member.getName()); // ctrl + alt + v (Window)

        // result에 값이 있으면
        result.ifPresent(member1 -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

         아래와 같이 변경 가능
        **/

        //Aop 사용 전
//        long start = System.currentTimeMillis();
//
//        try {
//            validateDuplicateMember(member); // 중복 회원 검증
//
//            memberRepository.save(member);
//            return member.getId();
//
//        } finally{
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }

        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();

    }

    // shift + alt + ctrl + T : 메서드 뽑아내기
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
