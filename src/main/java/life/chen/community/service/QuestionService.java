package life.chen.community.service;

import life.chen.community.dto.QuestionDTO;
import life.chen.community.mapper.QuestionMapper;
import life.chen.community.mapper.UserMapper;
import life.chen.community.model.Question;
import life.chen.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
2020.3.4：学习内容
起到一个组装的作用，习惯性称中间层为"service"
类似接口和实现类？一个类可以实现多个接口？？
 */
//重新写了一个service
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public List<QuestionDTO> list() {
        //查询Question
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //同时循环查询user，把user对象赋值到question上去
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //spring提供的一个工具类,快速将question对象的属性拷贝到questionDTO对象中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
