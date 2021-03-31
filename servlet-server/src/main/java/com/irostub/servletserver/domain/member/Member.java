package com.irostub.servletserver.domain.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String username;
    private int age;
}
