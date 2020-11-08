package tinking.in.spring.ioc.overview.dependeency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import tinking.in.spring.ioc.overview.dependeency.injection.dao.UserRepository;
import tinking.in.spring.ioc.overview.dependeency.lookup.annotation.Super;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

import java.util.Collection;
import java.util.Map;

/**
 * 依赖注入示例
 * 1、通过名称查找
 * 2、通过类型查找
 *
 * @author lihongjian
 * @since 2020/10/21
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        /**
         * 实时查找
         */
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection.xml");

        //依赖来源一：自定义Bean
        UserRepository userRepoistory = (UserRepository) beanFactory.getBean("userRepoistory");
        Collection<User> users = userRepoistory.getUsers();
        System.out.println(users);
        //依赖来源二：依赖注入（内建依赖）
        BeanFactory userBeanFactory = userRepoistory.getBeanFactory();
        System.out.println(userBeanFactory);
        System.out.println(beanFactory == userBeanFactory);

        ObjectFactory<ApplicationContext> userObjectFactory = userRepoistory.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject() == beanFactory);

        //依赖查找(错误)
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        //依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }

    private static void whoIsIocContainer(UserRepository userRepository, ApplicationContext applicationContext) {

        //ConfigurableApplicationContext <- ApplicationContext <-BeanFactory

        //ConfigurableApplicationContext#getBeanFactory  将BeanFactory组合进去

        //这个表达式为什么不会成立
        System.out.println(userRepository.getBeanFactory() == applicationContext);
    }

}
