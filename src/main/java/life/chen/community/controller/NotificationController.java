package life.chen.community.controller;

import life.chen.community.dto.NotificationDTO;
import life.chen.community.dto.PaginationDTO;
import life.chen.community.enums.NotificationTypeEnum;
import life.chen.community.model.Notification;
import life.chen.community.model.User;
import life.chen.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id,user);

        if (NotificationTypeEnum.REPLAY_COMMENT.getType() == notificationDTO.getType()
            || NotificationTypeEnum.REPLAY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }
    }
}
