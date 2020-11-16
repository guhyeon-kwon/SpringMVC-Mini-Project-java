package kr.co.springtest.dao;

import org.springframework.stereotype.Repository;

@Repository
// Dao역할을 하는 Bean이라는 의미(Component와 기능상 동일하다)
public class TestDao {
    public String testDaoMethod(){
        return "문자열";
    }
}
