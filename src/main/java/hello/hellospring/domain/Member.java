package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 알아서 생성
//    private Long id;
//
////    @Column(name = "username") // db에 컬럼명이 username 이라면.
//    private String name;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
