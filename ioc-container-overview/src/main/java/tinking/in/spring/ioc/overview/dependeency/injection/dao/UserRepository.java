package tinking.in.spring.ioc.overview.dependeency.injection.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import tinking.in.spring.ioc.overview.dependeency.lookup.domain.User;

import java.util.Collection;

/**
 * 用户仓储
 *
 * @author lihongjian
 * @since 2020/10/22
 */
public class UserRepository {

    Collection<User> users;

    BeanFactory beanFactory;

    ObjectFactory<ApplicationContext> userObjectFactory;

    public ObjectFactory<ApplicationContext> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<ApplicationContext> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
