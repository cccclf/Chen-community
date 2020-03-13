package life.chen.community.controller;

import life.chen.community.dto.QuestionDTO;
import life.chen.community.mapper.QuestionMapper;
import life.chen.community.model.Question;
import life.chen.community.model.User;
import life.chen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        //通过id查询，得到一个question对象
        QuestionDTO question = questionService.getById(id);
        //回显到页面
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }


    //提交掉表单后找到地址为"/publish"的，并且请求方法为PostMapping
    @PostMapping("/publish")
    public String doPublish(
            //21讲这里有变化
            //通过RequestParam去接收三个参数，
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model) {
        //同时首先放入model里面，为了能够回显到页面上去
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        //其次，验证参数是否为空，空的话提示信息，需要补充
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        //若参数参数内容不为空，则验证用户是否登录(利用拦截器)，用index的方法去实现
        User user = (User) request.getSession().getAttribute("user");
        //如果用户未登录，则返回错误信息到前面
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        //一切正常后，构建对象，插入数据库，实现功能
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
