package tinking.in.spring.bean.definition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解Definition示例
 *
 * @author lihongjian
 * @since 2020/11/8
 */
@Import(AnnotationDefinitionDemo.Config.class) //3.通过@Import方式
public class AnnotationDefinitionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        applicationContext.register(AnnotationDefinitionDemo.class);

        //通过BeanDefinition 注册 API的方式注册
        //1.命名Bean的方式
        registerUserBeanDefinition(applicationContext,"lhj-user");
        //2.通过非命名Bean的方式
        registerUserBeanDefinition(applicationContext);
        //启动应用上下文
        applicationContext.refresh();
        //Spring的Bean不会重复注册
        System.out.println("config 的 所有Bean：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 的 所有Bean：" + applicationContext.getBeansOfType(User.class));
        //关闭应用上下文
        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("age", 24)
                .addPropertyValue("name", "李宏健");

        //判断如果beanName存在时
        if (StringUtils.hasText(beanName)) {
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }else {
            //非命名Bean的注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry){
        registerUserBeanDefinition(registry,null);
    }


    //2.通过@Componet方式
    @Component//定义当前类为Spring Bean(组件)
    public static class Config {
        /**
         * 1.通过@Bean方式
         * 基于Java注解方式，定义了一个Bean
         *
         * @return
         */
        @Bean(name = {"user", "lihongjian-user"})
        public User user() {
            User user = new User();
            user.setAge(24);
            user.setName("lihongjian");
            return user;
        }
    }
}
