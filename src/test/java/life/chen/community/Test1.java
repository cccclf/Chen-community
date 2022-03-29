package life.chen.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author by chenlingfeng
 * @date 2021/6/29.
 */
public class Test1 {

    @Test
    public void test() {
        String s = "{\"LOCAL\": [\"1#3\"], \"PUBLIC_CLOUD\": [\"2#3\"], \"PRIVATE_CLOUD\": [\"3#3\"]}";
        Map<String, List<String>> map = JSONObject.parseObject(s, Map.class);
        System.out.println(map);
        List<String> list = map.get("LOCAL");
        System.out.println("list: " + list);
        System.out.println(list.get(0));
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;

    private int age;

    private List<String> usedName;

}