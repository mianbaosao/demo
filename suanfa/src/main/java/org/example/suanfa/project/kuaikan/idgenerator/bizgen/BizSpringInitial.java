package org.example.suanfa.project.kuaikan.idgenerator.bizgen;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author chenkai_1
 * @time 2019-09-04 20:29
 */
@Component
public class BizSpringInitial implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        BizIdGeneratorClient.getInstance();
    }
}
