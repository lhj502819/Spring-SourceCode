package tinking.in.spring.ioc.overview.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import tinking.in.spring.ioc.overview.dependeency.lookup.annotation.Super;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

import java.util.Map;

/**
 * {@link org.springframework.context.ApplicationContext} 作为IoC 容器
 *
 * @author lihongjian
 * @since 2020/10/26
 */
public class AnnotationApplicationConetxtAsIoCContainer {

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类 AnnotationApplicationConetxtAsIoCContainer 作为配置类
                    applicationContext.register(AnnotationApplicationConetxtAsIoCContainer.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找集合对象
        lookUpByAnnotaion(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setAge(24);
        user.setName("lhj");
        return user;
    }

    private static void lookUpByAnnotaion(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的User的对象："+users);
        }
    }
}
