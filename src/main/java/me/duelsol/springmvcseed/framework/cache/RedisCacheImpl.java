package me.duelsol.springmvcseed.framework.cache;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/27
 * Time: 20:15
 */
@Component
final class RedisCacheImpl implements Cache {

    private String name;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheImpl.class);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (key == null) {
            return null;
        }
        Object result = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] value = connection.get(key.toString().getBytes());
            if (value == null) {
                return null;
            }
            return SerializationUtils.deserialize(value);
        });
        return result == null ? null : new SimpleValueWrapper(result);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        ValueWrapper wrapper = get(key);
        if (wrapper != null) {
            Object value = wrapper.get();
            if (value != null && type != null && type.isInstance(value)) {
                return (T) value;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        Object value = get(key, valueLoader.getClass());
        if (value != null) {
            return (T) value;
        }
        try {
            ValueWrapper wrapper = putIfAbsent(key, valueLoader.call());
            if (wrapper != null) {
                value = wrapper.get();
                if (value != null && valueLoader.getClass().isInstance(value)) {
                    return (T) value;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Spring缓存实现类get时报错，key=" + key.toString() + ",valueLoader=" + valueLoader.getClass().getName(), e);
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        putIfAbsent(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (key == null) {
            return null;
        }
        Object result = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] resultValue = SerializationUtils.serialize((Serializable) value);
            connection.set(key.toString().getBytes(), resultValue);
            if (resultValue == null) {
                return null;
            }
            return resultValue;
        });
        return result == null ? null : new SimpleValueWrapper(result);
    }

    @Override
    public void evict(Object key) {
        if (key == null) {
            return;
        }
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(key.toString().getBytes()));
    }

    @Override
    public void clear() {
        redisTemplate.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "SUCCESS";
        });
    }

    public void setName(String name) {
        this.name = name;
    }

}
