package kr.co.springtest.interceptor;

import kr.co.springtest.beans.BoardInfoBean;
import kr.co.springtest.service.TopMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TopMenuInterceptor implements HandlerInterceptor {

    private TopMenuService topMenuService;

    public TopMenuInterceptor(TopMenuService topMenuService) {
        this.topMenuService = topMenuService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
        request.setAttribute("topMenuList", topMenuList);

        return true;
    }
}






















