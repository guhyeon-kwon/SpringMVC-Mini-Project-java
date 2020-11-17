package kr.co.springtest.dao;

import kr.co.springtest.beans.BoardInfoBean;
import kr.co.springtest.mapper.TopMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopMenuDao {
    @Autowired
    private TopMenuMapper topMenuMapper;

    public List<BoardInfoBean> getTopMenuList(){
        List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
        return topMenuList;
    }

}
