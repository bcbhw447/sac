package jp.ne.sac.common.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * ActionBeanGenerator.
 * <p>
 * BeanNameGeneratorを継承したActionBeanGeneratorクラス。<br>
 *
 * @author m_hamaoka
 * @version 1.0
 * @since 2014/07/14
 */
public class Struts2ActionBeanNameGenerator implements BeanNameGenerator {

    /** Logging. */
    private Logger log = LoggerFactory.getLogger(Struts2ActionBeanNameGenerator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

        String className = definition.getBeanClassName();

        log.debug("* generateBeanName : " + className);

        return className;
    }

}
