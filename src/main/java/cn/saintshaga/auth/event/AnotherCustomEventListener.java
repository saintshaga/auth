package cn.saintshaga.auth.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by huang on 18-11-15.
 */
@Slf4j
@Component
public class AnotherCustomEventListener implements ApplicationListener<CustomEvent>, Ordered {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.info("Another: I have receive message {} from {}", event.getMessage(), event.getSource().getClass());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
