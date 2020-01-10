import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author jjxiek
 * @since 2019/11/22 16:40
 */
public class CaffeineSimpleDemo {
    @Test
    public void fun1() throws InterruptedException {
        // 设置缓存容器
        Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS) //设置写入过期时间
            .expireAfterAccess(1,TimeUnit.SECONDS) //设置读取过期时间
            .maximumSize(10)
            .build();
        cache.put("text","hello");
        System.out.println(cache.getIfPresent("text"));

        Thread.sleep(2000);
        System.out.println(cache.getIfPresent("text"));
    }
}
