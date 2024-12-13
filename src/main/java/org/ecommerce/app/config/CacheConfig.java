package org.ecommerce.app.config;


import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        instance.getConfig().addMapConfig(new MapConfig("orders").setTimeToLiveSeconds(300));
        return instance;
    }

}
