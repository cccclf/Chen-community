package life.chen.community.controller;

import life.chen.community.dto.PaginationDTO;
import life.chen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        //传递两个参数page，size
                        //page代表页码，size代表每一页的分页数（也就是每一页中的数量）
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search) {
        //跳转到index页面之前，将数据数据存入
        //查询之前需要构造一个DTO，因为DTO不属于任何一个mapper（应该就是创建了实现类把，而实现类本身可以实现多个接口）
        PaginationDTO pagination = questionService.list(search,page, size);//将参数自动传递到service
        model.addAttribute("pagination", pagination);
        model.addAttribute("search",search);
        return "index";
    }

}
