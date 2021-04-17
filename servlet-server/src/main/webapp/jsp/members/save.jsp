<%@ page import="com.irostub.servletserver.domain.member.MemberRepository" %>
<%@ page import="com.irostub.servletserver.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    // request, response 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    System.out.println("member.getUsername() = " + member.getUsername());
    System.out.println("member.getAge() = " + member.getAge());
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age = <%=member.getAge()%></li>
</ul>
<a href="/jsp/members/members.jsp">맴버 리스트</a>
<a href="/index.html">메인</a>
</body>
</html>
