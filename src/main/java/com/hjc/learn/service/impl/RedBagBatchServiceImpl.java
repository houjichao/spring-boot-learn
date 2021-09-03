/*
package com.hjc.learn.service.impl;

import com.hjc.learn.service.RedBagBatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

*/
/**
 * @author houjichao
 *//*

@Service
@Slf4j
public class RedBagBatchServiceImpl implements ApplicationListener<ContextRefreshedEvent>, RedBagBatchService {
    */
/**
     * 红包雨lua脚本script load的sha1值
     *//*

    private String redBagScriptSha1 = "";
    private static final String LUA_SCRIPT_PATH = "/lua_script/";

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            log.info("初始化LUA脚本");
            initRedBagScriptSha1();
            log.info("成功初始化LUA脚本");
        } catch (Exception e) {
            log.error("初始化lua脚本出错", e);
        }
    }

    */
/**
     * 读取抢红包Lua脚本
     *//*

    private String initRedBagScriptSha1() {
        if (StringUtils.isBlank(redBagScriptSha1)) {
            synchronized (redBagScriptSha1) {
                if (StringUtils.isBlank(redBagScriptSha1)) {
                    try {
                        // 读取资源文件内容，并scriptLoad到Redis，记录sha值
                        //readResource(LUA_SCRIPT_PATH + "/RedBagBatchGrab.lua");
                        String scriptText = "";
                        RedisSerializer argsSerializer = redisTemplate.getStringSerializer();
                        //指定返回结果序列化方式
                        RedisSerializer<String> resultSerializer = redisTemplate.getStringSerializer();
                        List<String> keyList = Arrays.asList("111", "222");
                        DefaultRedisScript<Long> rs = new DefaultRedisScript<>(scriptText, Long.class);
                        redBagScriptSha1 = (String) redisTemplate.execute(rs,argsSerializer,resultSerializer,keyList);
                    } catch (Exception e) {
                        log.error("初始化LUA脚本出错 - " + e.getMessage(), e);
                        throw new RuntimeException("初始化LUA脚本出错 - " + e.getMessage());
                    }
                }
            }
        }
        return redBagScriptSha1;
    }

    */
/**
     * 抢红包的方法
     *//*

    @Override
    public BigDecimal grabRedBag(Long userId, Long redBagId) {

        // 判断用户是否在黑名单、红包雨时间是否已失效等业务逻辑的判断
        // ..............

        // 抢红包lua脚本使用keys，需要与脚本中顺序保持一致
        List<String> luaKey = new ArrayList<>();
        luaKey.add("receiveLimitKey"); // KEYS[1]
        luaKey.add("redBagBatchKey"); // KEYS[2]
        luaKey.add("redBagUserKey"); // KEYS[3]

        // 抢红包lua脚本使用args，需要与脚本中顺序保持一致
        List<String> luaArgs = new ArrayList<>();
        luaArgs.add(userId.toString());  // ARGV[1]

        //redisTemplate.execute(this.redBagScriptSha1, luaKey, luaArgs);

        Object luaResult = "";

        // 没抢到返回0
        if (luaResult == null || new BigDecimal(luaResult.toString()).compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // 抢到，记日志并返回结果
        BigDecimal result = new BigDecimal(luaResult.toString());
        return result;
    }
}*/
