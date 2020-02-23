package life.chen.community.controller;

import life.chen.community.dto.AccessTokenDTO;
import life.chen.community.dto.GithubUser;
import life.chen.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
    2020.2.22学习内容-1
这一讲的内容：
    1、由得到的code去调GitHub accessToken的api；
    2、通过accessToken的api获取到了accessToken；
    3、通过GitHub user的api携带accessToken获取到了user信息，同时打印user信息
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("0e649891a46584546778");
        accessTokenDTO.setClient_secret("db77b06f61d1deb2dbb1d1a44193109111f45b37");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
