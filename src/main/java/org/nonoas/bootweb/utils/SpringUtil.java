package org.nonoas.bootweb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 用于主动获取IOC容器中的Bean
 * 在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象
 *
 * @author : Nonoas
 * @time : 2021-04-24 16:04
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private final Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == SpringUtil.applicationContext) {
            SpringUtil.applicationContext = applicationContext;
        }
        logger.info("SpringContext加载成功,值：{}", applicationContext);
    }

    /**
     * 获取applicationContext
     *
     * @return ApplicationContext
     */
    @SuppressWarnings("unused")
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean
     *
     * @param name name
     * @return Object | null
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @param clazz class对象
     * @param <T>   返回对象类型
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  name
     * @param clazz class对象
     * @param <T>   返回类型
     * @return Bean对象
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

}
