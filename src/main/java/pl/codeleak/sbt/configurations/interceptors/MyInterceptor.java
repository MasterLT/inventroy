package pl.codeleak.sbt.configurations.interceptors;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by foo on 2017/2/8.
 */
public class MyInterceptor implements HandlerInterceptor {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyInterceptor.class);


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") != null) {
            return true;
        } else {
            response.sendRedirect("/admin");
            return false;
        }
    }
}
