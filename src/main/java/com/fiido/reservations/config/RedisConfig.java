package com.fiido.reservations.config;

import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class RedisConfig {

  private String serverAddress = "redis://127.0.0.1:6379";

  @Bean
  public RedissonClient redissonClient() {
    var config = new Config();
    config.useSingleServer()
        .setAddress(serverAddress);

    return Redisson.create(config);
  }

  @Bean
  @Autowired
  public CacheManager cacheManager(RedissonClient redissonClient) {
    var configs = Map.of(
        "hotels", new CacheConfig());
    return new RedissonSpringCacheManager(redissonClient, configs);
  }

  @CacheEvict(cacheNames = {
      "hotels"
  }, allEntries = true)
  @Scheduled(cron = "0 0 0 * * ?")
  @Async
  public void deleteCache() {
    log.info("Cleaning cache...");
  }
}
