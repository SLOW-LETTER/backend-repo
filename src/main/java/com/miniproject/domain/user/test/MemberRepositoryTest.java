//package com.miniproject.domain.user.test;
//
//import com.miniproject.domain.user.entity.User;
//import com.miniproject.domain.user.repository.UserRepository;
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@SpringBootTest
//@Log
//public class MemberRepositoryTest {
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void insertTest() {
//        for(int i=0; i<100; i++) {
//            User user = new User();
//            user.setUid("user" + i);
//            user.setUpw("pw" + i);
//            user.setUemail("hihi@" + i);
//            if(i <= 80) {
//                role.setRoleName("BASIC");
//            }else if(i <= 90) {
//                role.setRoleName("MANAGER");
//            }else {
//                role.setRoleName("ADMIN");
//            }
//            user.setRoles(Arrays.asList(role));
//            userRepository.save(user);
//        }
//    }
//
//    @Test
//    public void testMember() {
//        Optional<Member> result = Optional.ofNullable(memberRepository.findOne(85L));
//        result.ifPresent(member -> log.info("member " + member));
//    }
//}