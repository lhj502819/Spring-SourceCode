package tinking.in.spring.ioc.overview.dependeency.lookup.domain;

import tinking.in.spring.ioc.overview.dependeency.lookup.annotation.Super;

/**
 * 超级用户
 *
 * @author lihongjian
 * @since 2020/10/21
 */
@Super
public class SuperUser extends User{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
