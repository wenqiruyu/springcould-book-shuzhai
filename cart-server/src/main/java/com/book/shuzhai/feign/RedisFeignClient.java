package com.book.shuzhai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@FeignClient("redis-server")
public interface RedisFeignClient {

    /**
     * 将string类型的redis设置时效为15分钟
     * @param stringKey
     * @param stringValue
     */
    @RequestMapping(value = "/redis/string", method = RequestMethod.POST)
    void toSaveString(@RequestParam("stringKey") String stringKey,@RequestParam("time") Long time,@RequestParam("stringValue") String stringValue);

    @RequestMapping(value = "/redis/string", method = RequestMethod.GET)
    String toGetString(@RequestParam("stringKey") String stringKey);

    @RequestMapping(value = "/redis/hash", method = RequestMethod.POST)
    void toSaveHash(@RequestParam("key") String key, @RequestParam("hashKey") Object hashKey, @RequestParam("hashValue") Object hashValue);

    @RequestMapping(value = "/redis/hash", method = RequestMethod.GET)
    Object toGetHash(@RequestParam("key") String key, @RequestParam("hashKey") Object hashKey);

    /**
     * 删除给定的哈希hashKeys
     * @param key
     * @param hashKey
     * @return
     */
    @RequestMapping(value = "/redis/hash", method = RequestMethod.DELETE)
    Long toDeleteHash(@RequestParam String key, @RequestParam Object hashKey);

    /**
     * 新增一个zset
     * @param key
     * @param zsetKey
     * @param score
     * @return
     */
    @RequestMapping(value = "/redis/zset", method = RequestMethod.POST)
    Boolean toSaveZset(@RequestParam("key") String key, @RequestParam("zsetKey") String zsetKey, @RequestParam("score") double score);

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     * @param key
     * @param zsetKey
     * @return
     */
    @RequestMapping(value = "/redis/zset/increase", method = RequestMethod.GET)
    Long toGetZsetRankIncrease(@RequestParam("key") String key, @RequestParam("zsetKey") String zsetKey);

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从大到小)顺序排列
     * @param key
     * @param zsetKey
     * @return
     */
    @RequestMapping(value = "/redis/zset/decrease", method = RequestMethod.GET)
    Long toGetZsetRankDecrease(@RequestParam("key") String key, @RequestParam("zsetKey") String zsetKey);

    /**
     * 获得所有元素的递增排序
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     * rangeWithScores从小到大排序
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/zset/range/increase", method = RequestMethod.GET)
    Set<String> getZsetRange(@RequestParam("key") String key);

    /**
     * 添加元素的值，并进行返回修改后的值
     * @param key
     * @param zsetKey
     * @param score
     * @return
     */
    @RequestMapping(value = "/redis/zset", method = RequestMethod.PUT)
    Double toUpdateZset(@RequestParam("key") String key, @RequestParam("zsetKey") String zsetKey, @RequestParam("score") double score);

    /**
     * 根据有序数列的键删除元素
     * @param key
     * @param zsetKey
     * @return
     */
    @RequestMapping(value = "/redis/zset", method = RequestMethod.DELETE)
    Long toDeleteZset(@RequestParam("key") String key, @RequestParam("zsetKey") String zsetKey);
}
