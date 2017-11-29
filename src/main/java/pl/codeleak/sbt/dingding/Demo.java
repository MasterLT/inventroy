package pl.codeleak.sbt.dingding;

import com.google.common.collect.Sets;
import pl.codeleak.sbt.entity.OaCompanyuser;

import java.util.Set;

public class Demo {

    public static void main(String[] args) throws Exception {
        Set<OaCompanyuser> set= Sets.newHashSet();
        for(int i=0;i<100;i++){
            OaCompanyuser user=new OaCompanyuser();
            user.setWorkcode(i/10+"");
            set.add(user);
        }
        System.out.println(set.size());
    }
}
