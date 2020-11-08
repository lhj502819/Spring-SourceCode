package tinking.in.spring.ioc.overview.dependeency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tinking.in.spring.ioc.overview.dependeency.lookup.annotation.Super;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

import java.util.Map;

/**
 * 依赖查找示例
 * 1、通过名称查找
 * 2、通过类型查找
 *
 * @author lihongjian
 * @since 2020/10/21
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        /**
         * 实时查找
         */
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup.xml");
        //实时查找
        lookUpInrealTime(beanFactory);
        //延迟查找 并非懒加载
        lookUpInLazy(beanFactory);
        //按照类型查找
        lookUpByType(beanFactory);
        //按照类型查找集合对象
        lookUpCollectionByType(beanFactory);
        //按照注解查找
        lookUpByAnnotaion(beanFactory);
    }

    private static void lookUpByAnnotaion(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找到的所有的标注@Super的对象："+users);
        }
    }

    private static void lookUpCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的User对象："+users);
        }
    }

    private static void lookUpByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("按照类型查找："+user);
    }

    public static void lookUpInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找："+user);
    }

    public static void lookUpInrealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找："+user.toString());
    }
}
