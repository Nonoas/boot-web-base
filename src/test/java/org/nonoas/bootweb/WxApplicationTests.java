package org.nonoas.bootweb;

import org.nonoas.bootweb.config.WxAppConfig;
import org.nonoas.bootweb.utils.OkHttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WxApplicationTests {

    @Resource
    WxAppConfig config;
    @Resource
    private OkHttpUtil okHttpUtil;

    @Test
    void contextLoads() {
        Map<String, String> map = new HashMap<>();
        map.put("appid", config.getAppid());
        map.put("secret", config.getSecret());
        map.put("js_code", "033Ysm000kEGAN1loU100acFnF1Ysm0k");
        map.put("grant_type", "authorization_code");
        String get = okHttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", map);
        System.out.println(get);
    }
}
