package org.lizhishu.love.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
    private static ApplicationContext applicationContext;

    public SpringContextHolder() {
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        logger.info("spring 容器注入对象  ApplicationContext=" + ctx);
        applicationContext = ctx;
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            logger.error("ApplicationContext 未被注入，请确认是否spring注释解析<context:component-scan>");
            throw new RuntimeException("ApplicationContext 未被注入，请确认是否spring注释解析<context:component-scan>");
        } else {
            return applicationContext;
        }
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return getApplicationContext().getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return getApplicationContext().getBean(name, requiredType);
    }

    public static <T> List<T> getAutowiredClasses(Class<T> clazz) {
        List<T> list = new ArrayList();
        Map<String, T> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(getApplicationContext(), clazz, true, false);
        if (matchingBeans != null && matchingBeans.size() >= 1) {
            list.addAll(matchingBeans.values());
            return list;
        } else {
            return null;
        }
    }

    public static <T> T getAutowiredClass(Class<T> clazz) {
        List<T> list = getAutowiredClasses(clazz);
        return list != null && list.size() >= 1 ? list.get(0) : null;
    }


}
