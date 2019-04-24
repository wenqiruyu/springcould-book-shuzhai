package com.book.shuzhai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private Jedis jedis;

    /**
     * 将string类型的redis设置时效为15分钟
     * @param stringKey
     * @param stringValue
     */
    @PostMapping("/redis/string")
    public void toSaveString(@RequestParam String stringKey,@RequestParam Long time,@RequestParam String stringValue){
        ValueOperations<String, String> string = stringRedisTemplate.opsForValue();
        string.set(stringKey, stringValue, time, TimeUnit.MINUTES);
    }

    @GetMapping("/redis/string")
    public String toGetString(@RequestParam String stringKey){
        ValueOperations<String, String> string = stringRedisTemplate.opsForValue();
        return string.get(stringKey);
    }

    // 存储list集合
    @PostMapping("/redis/list")
    public void toSaveList(@RequestParam String key, @RequestParam Collection<Object> collection){
        ListOperations list = redisTemplate.opsForList();
        list.rightPushAll(key, collection);
    }

    // 获取list集合
    @GetMapping("/redis/list")
    public Collection toGetList(@RequestParam String key){
        ListOperations list = redisTemplate.opsForList();
        return list.range(key, 0, -1);
    }

    // 存储json形式的list
    @PostMapping("/redis/json/list")
    public void toSaveJsonList(@RequestParam String key, @RequestParam String value){
        ValueOperations<String, String> string = stringRedisTemplate.opsForValue();
        string.set(key, value);

    }

    // 获取json形式的list
    @GetMapping("/redis/json/list")
    public String toGetJsonList(@RequestParam String key){
        ValueOperations<String, String> string = stringRedisTemplate.opsForValue();
        // 还需进行对json字符传的转换
//        JSON.parseArray(string.get(key), Product.class);
        return string.get(key);
    }

//    @PostMapping("/jedis/hash")
//    public void toSaveHash(String key, String field, String value){
//        jedis.hset(key, field, value);
//    }
//
//    @GetMapping("/jedis/hash")
//    public List<String> toGetHash(String key, String fields){
//        List<String> stringList = jedis.hmget(key, fields);
//        return stringList;
//    }

    @PostMapping("/redis/hash")
    public void toSaveHash(@RequestParam String key, @RequestParam Object hashKey, @RequestParam Object hashValue){
        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        hash.put(key, hashKey, hashValue);
    }

    @GetMapping("/redis/hash")
    public Object toGetHash(@RequestParam String key, @RequestParam Object hashKey){
        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 删除给定的哈希hashKeys
     * @param key
     * @param hashKey
     * @return
     */
    @DeleteMapping("/redis/hash")
    public Long toDeleteHash(@RequestParam String key, @RequestParam Object hashKey){
        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        return hash.delete(key, hashKey);
    }

    /**
     * 新增一个zset
     * @param key
     * @param zsetKey
     * @param score
     * @return
     */
    @PostMapping("/redis/zset")
    public Boolean toSaveZset(@RequestParam String key, @RequestParam String zsetKey, @RequestParam double score){
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        return zSet.add(key, zsetKey, score);
    }

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     * @param key
     * @param zsetKey
     * @return
     */
    @GetMapping("/redis/zset/increase")
    public Long toGetZsetRankIncrease(@RequestParam String key, @RequestParam String zsetKey){
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        return zSet.rank(key, zsetKey);
    }

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从大到小)顺序排列
     * @param key
     * @param zsetKey
     * @return
     */
    @GetMapping("/redis/zset/decrease")
    public Long toGetZsetRankDecrease(@RequestParam String key, @RequestParam String zsetKey){
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        return zSet.reverseRank(key, zsetKey);
    }

    /**
     * 获得所有元素的递增排序
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     * rangeWithScores从小到大排序
     * @param key
     * @return
     */
    @GetMapping("/redis/zset/range/increase")
    public Set<String> getZsetRange(@RequestParam String key){
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        return zSet.range(key, 0, -1);
    }

    /**
     * 添加元素的值，并进行返回修改后的值
     * @param key
     * @param zsetKey
     * @param score
     * @return
     */
    @PutMapping("/redis/zset")
    public Double toUpdateZset(@RequestParam String key, @RequestParam String zsetKey, @RequestParam double score){
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        return zSet.incrementScore(key, zsetKey, score);
    }

    /**
     * 根据有序数列的键删除元素
     * @param key
     * @param zsetKey
     * @return
     */
    @DeleteMapping("/redis/zset")
    public Long toDeleteZset(@RequestParam String key, @RequestParam String zsetKey){
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        return zSet.remove(key, zsetKey);
    }
}
