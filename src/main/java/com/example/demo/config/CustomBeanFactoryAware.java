package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author liufei
 * @since 2019-11-01 15:40
 */
@Component
public class CustomBeanFactoryAware implements BeanFactoryAware, InitializingBean {
    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MethodValidationPostProcessor methodValidationPostProcessor =
                beanFactory.getBean(MethodValidationPostProcessor.class);
        System.out.println("methodValidationPostProcessor: " + methodValidationPostProcessor);
    }
}
