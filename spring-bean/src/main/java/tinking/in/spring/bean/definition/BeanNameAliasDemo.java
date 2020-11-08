package tinking.in.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

/**
 * {Spring Bean别名}示例
 *
 * @author lihongjian
 * @since 2020/11/8
 */
public class BeanNameAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-bean-definition.xml");
        User lihongjianUser = beanFactory.getBean("lihongjian-user",User.class);
        User user = beanFactory.getBean("user",User.class);
        System.out.println("lihongjian-user是否等于user："+(lihongjianUser == user));
    }
}
