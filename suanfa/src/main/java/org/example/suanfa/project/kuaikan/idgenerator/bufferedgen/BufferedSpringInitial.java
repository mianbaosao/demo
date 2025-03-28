package org.example.suanfa.project.kuaikan.idgenerator.bufferedgen;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author chenkai_1
 * @time 2019-09-04 20:33
 */
@Component
public class BufferedSpringInitial implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        BufferedIdGeneratorClient.getInstance();
    }
}
