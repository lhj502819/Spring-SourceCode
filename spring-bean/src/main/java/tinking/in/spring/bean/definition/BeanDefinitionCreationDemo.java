package tinking.in.spring.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

/**
 * {@link BeanDefinition}构建示例
 *
 * @author lihongjian
 * @since 2020/11/8
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("age",24)
                .addPropertyValue("name","lihongjian");
        //获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition并非Bean的终态，可以自定义修改

        //2.通过AbstractBeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过MutablePropertyValues批量操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        mutablePropertyValues.addPropertyValue("age",24);
//        mutablePropertyValues.addPropertyValue("name","lihongjian");
        mutablePropertyValues.add("age",24)
                .add("name","lihongjian");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
