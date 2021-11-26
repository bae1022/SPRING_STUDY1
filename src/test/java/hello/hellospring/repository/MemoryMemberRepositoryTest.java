package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // 굳이 퍼블릭으로 할 필요 없음 / 모든 테스트는 순서를 보장할 수 없다. 의존 관계가 없어야 한다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 일종의 콜백 메소드
    public void afterEach(){
        repository.clearStore(); // 테스트 실행이 끝날때마다 repository 지움 (순서가 상관이 없게 된다.)
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result); // static으로 해서 Assertions.assertThat 으로 쓰지 않아도 된다.

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //get()으로 꺼내면 optional 한 번 까서 넣을 수 있음

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
