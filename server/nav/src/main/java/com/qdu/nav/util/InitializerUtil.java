package com.qdu.nav.util;

import org.springframework.stereotype.Component;

/**
 * @ClassName: InitializerUtil
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Component
public class InitializerUtil {
    private TokenSettings tokenSettings;
    public InitializerUtil(TokenSettings tokenSettings){
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
