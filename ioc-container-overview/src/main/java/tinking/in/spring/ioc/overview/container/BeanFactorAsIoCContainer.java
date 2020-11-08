package tinking.in.spring.ioc.overview.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import tinking.in.spring.ioc.overview.dependeency.lookup.annotation.Super;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

import java.util.Map;

/**
 * {@link org.springframework.beans.factory.BeanFactory} 作为IoC 容器
 *
 * @author lihongjian
 * @since 2020/10/26
 */
public class BeanFactorAsIoCContainer {

    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件 classPath路径
        String location = "classpath:/META-INF/dependency-lookup.xml";
        //加载配置
        int beanDefinitionConut = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义的加载数量："+beanDefinitionConut);
        //依赖查找集合
        lookUpByAnnotaion(beanFactory);
    }

    private static void lookUpByAnnotaion(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找到的所有的标注@Super的对象："+users);
        }
    }
}
