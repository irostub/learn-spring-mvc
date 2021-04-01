package com.irostub.servletserver.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    //동시성 문제 해결을 위해서라면, concurrentHashMap 을 사용할 것
    private Map<Long, Member> store = new HashMap<>();
    private long sequence = 0L;

    //마찬가지로 동시성을 위해서라면 lazy initialization singleton 전략보단 holder initialization singleton 패턴을 사용할 것
    private static MemberRepository memberRepository;

    private MemberRepository(){

    }

    public static MemberRepository getInstance(){
        if(memberRepository == null){
            memberRepository = new MemberRepository();
        }
        return memberRepository;
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
