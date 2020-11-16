package kr.co.springtest.service;

import kr.co.springtest.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// Service역할을 하는 Bean이라는 의미(Component와 기능상 동일하다)
public class TestService {

    @Autowired
    private TestDao dao;

    public String testMethod(TestDao testDao){

        String str = dao.testDaoMethod();

        return str;
    }
}
