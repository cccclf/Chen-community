package life.chen.community.advice;


import life.chen.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//2020.3.29学习内容
//实现了默认通用的异常处理
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","服务器爆满了，稍后再试试吧妹妹");
        }
        return new ModelAndView("error");
    }
}
