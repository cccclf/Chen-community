package life.chen.community.cache;

import life.chen.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOs = new ArrayList<>();
        
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","html","java","node","python","c++","c","golang","objective-c","typescript","shell","swift","c#","sass","bash","less"));
        tagDTOs.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel","spring","spring boot","express","django","flask","yii","ruby-on-rails","tornado","koa","struts"));
        tagDTOs.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("Linux","docker","apache","ubuntu","centos","缓存 tomcat","unix","windows-server"));
        tagDTOs.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","sql","oracle","sqlserver"));
        tagDTOs.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","visual","eclipse","ide","xcode","svn"));
        tagDTOs.add(tool);


        return tagDTOs;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining());
        return invalid;

    }
}
