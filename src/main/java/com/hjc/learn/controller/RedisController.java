package com.hjc.learn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author houjichao
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "redis操作相关")
@Slf4j
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "执行lua脚本")
    @GetMapping(value = "/lua")
    public void luaTest() {

        // 注意，如果是return 1,DefaultRedisScript<Long> 泛型为Long
        // Integer会有问题
        /**
         *     public static ReturnType fromJavaType(@Nullable Class<?> javaType) {
         *         if (javaType == null) {
         *             return STATUS;
         *         } else if (javaType.isAssignableFrom(List.class)) {
         *             return MULTI;
         *         } else if (javaType.isAssignableFrom(Boolean.class)) {
         *             return BOOLEAN;
         *         } else {
         *             return javaType.isAssignableFrom(Long.class) ? INTEGER : VALUE;
         *         }
         *     }
         */
        String lua = "redis.call ('set' , KEYS[1] , ARGV[1]) \n"
                + "redis.call ('set' , KEYS[2] , ARGV[2]) \n "
                + " local str1 = redis.call ('get' , KEYS [1]) \n "
                + " local str2 = redis.call ('get' , KEYS [2]) \n "
                + " if str1 == str2 then \n "
                + " return 1 \n "
                + " end \n "
                + " return 0 \n ";

        DefaultRedisScript<Long> rs = new DefaultRedisScript<>(lua, Long.class);

        // 指定传递参数序列化方式
        // 这里如果用这种参数序列化方式，也会有问题 redisTemplate.getDefaultSerializer();
        RedisSerializer argsSerializer = redisTemplate.getStringSerializer();
        //指定返回结果序列化方式
        RedisSerializer<String> resultSerializer = redisTemplate.getStringSerializer();
        //执行Lua脚本
        String value1 = "93";
        String value2 = "95";
        List<String> keyList = Arrays.asList("houjichao", "gaoxin");
        List<String> valueList = Arrays.asList("1993", "1995");
        Long result = (Long) redisTemplate.execute(rs, argsSerializer, resultSerializer, keyList, value1, value2);
        Object gaoxin = redisTemplate.opsForValue().get("gaoxin");
        if (gaoxin != null) {
            System.out.println(gaoxin.toString());
        }
        /**
         * 这里如果用integer接也会有问题
         *
         *     protected <T> CommandOutput<K, V, T> newScriptOutput(RedisCodec<K, V> codec, ScriptOutputType type) {
         *         switch(type) {
         *         case BOOLEAN:
         *             return new BooleanOutput(codec);
         *         case INTEGER:
         *             return new IntegerOutput(codec);
         *         case STATUS:
         *             return new StatusOutput(codec);
         *         case MULTI:
         *             return new NestedMultiOutput(codec);
         *         case VALUE:
         *             return new ValueOutput(codec);
         *         default:
         *             throw new RedisException("Unsupported script output type");
         *         }
         *     }
         *
         * public class IntegerOutput<K, V> extends CommandOutput<K, V, Long> {
         *     public IntegerOutput(RedisCodec<K, V> codec) {
         *         super(codec, (Object)null);
         *     }
         *
         *     public void set(long integer) {
         *         this.output = integer;
         *     }
         *
         *     public void set(ByteBuffer bytes) {
         *         this.output = null;
         *     }
         * }
         */
        Long result1 = (Long) redisTemplate.execute(
                (RedisConnection connection) -> connection.eval(
                        lua.getBytes(),
                        ReturnType.INTEGER,
                        //用于指定键名的个数
                        2,
                        "houjichao".getBytes(),
                        "gaoxin".getBytes(),
                        "1993".getBytes(),
                        "1995".getBytes())
        );
        ReturnType returnType = ReturnType.fromJavaType(Long.class);
        System.out.println(result);
        System.out.println("result1 "+result1);
    }
}
