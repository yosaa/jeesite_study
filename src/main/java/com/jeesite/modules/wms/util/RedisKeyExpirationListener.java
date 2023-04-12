package com.jeesite.modules.wms.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 监听 Redis key 过期事件
 *
 * @Author Lizhou
 */
@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 拿到key
        log.info("监听Redis key过期，key：{}，channel：{}", message.toString(), new String(pattern));
        if(message.toString().equals("yosaaaa")){
            log.error("yosaaaa过期处理");
        }
    }
}
