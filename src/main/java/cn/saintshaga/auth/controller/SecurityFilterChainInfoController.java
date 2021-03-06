package cn.saintshaga.auth.controller;

import cn.saintshaga.forneus.property.JavaSetting;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.Map;
import java.util.Set;

/**
 * Created by huang on 18-10-15.
 */
@RestController
@Slf4j
@RequestMapping("/systemInfo")
public class SecurityFilterChainInfoController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    @Resource(name="springSecurityFilterChain")
    private FilterChainProxy filterChainProxy;

    @Value("${hue.native.auth.mode:null}")
    private String host;

    @Autowired
    private JavaSetting javaSetting;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
//        this.filterChainProxy = (FilterChainProxy) applicationContext.getBean("springSecurityFilterChain");
//        log.info(getAllBeansInContext().toString());
    }

    @RequestMapping("/contexts")
    @ResponseBody
    public Map<Integer, String> showBeansInContext() {
        return getAllBeansInContext();
    }

    private Map<Integer, String> getAllBeansInContext() {
        Map<Integer, String> results = Maps.newLinkedHashMap();
        int i = 0;
        for(String name : applicationContext.getBeanDefinitionNames()) {
            results.put(++i, name);
        }
        results.put(++i, host);
        return results;
    }

    @RequestMapping("/filterChain")
    @ResponseBody
    public Map<Integer, Map<Integer, String>> getSecurityFilterInfo() {
        return this.getSecirotyFilterChains();
    }

    private Map<Integer, Map<Integer, String>> getSecirotyFilterChains() {
        Map<Integer, Map<Integer, String>> results = Maps.newLinkedHashMap();
        int i=0;
        for(SecurityFilterChain filterChain : filterChainProxy.getFilterChains()) {
            Map<Integer, String> filters = Maps.newLinkedHashMap();
            int j=0;
            for(Filter filter : filterChain.getFilters()) {
                filters.put(++j, filter.getClass().getName());
            }
            results.put(++i, filters);
        }
        return results;
    }

    @RequestMapping("/controllers")
    @ResponseBody
    public Set<String> getAllControllers() {
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(RestController.class);
        return controllers.keySet();
    }

    @RequestMapping("/javaSetting")
    @ResponseBody
    public String getJavaSetting() {
        return this.javaSetting.toString();
    }


}
