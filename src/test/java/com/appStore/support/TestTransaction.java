package com.appStore.support;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 */
@ContextConfiguration("classpath:applicationContext-test.xml")
@PropertySource("classpath:test.properties")
@ActiveProfiles("test")
@TransactionConfiguration
@Transactional("jdbcTransactionManager")
public abstract class TestTransaction extends AbstractTransactionalJUnit4SpringContextTests {


    /**
     * 获取代理的目标对象，如果BEAN被Spring AOP代理过。
     *
     * <p>
     *     解决在单元测试的过程中，因为某些方法附加了事务或者非事务性的注解，导致测试失败。
     * </p>
     *
     * <p>
     *     比如{@code SubjectJdbcDAO#selectSubjectByID}上附加了{@code @MSSQLJdbcNoTransactional}注解让方法在
     *     无事务环境中运行。而在单元测试中继承{@link TestTransaction}的
     *     单元测试类在类或者方法级别上附加{@link org.springframework.test.context.jdbc.Sql}注解初始化数据环境时，
     *     如果不绕过{@code @MSSQLJdbcNoTransactional}注解，那么是查不到数据的。
     * </p>
     *
     * @param proxy BEAN的代理类。
     * @return BEAN目标类。
     */
    protected <T> T getTargetObject(Object proxy, Class<T> targetClass) {
        try {
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                return (T) ((Advised) proxy).getTargetSource().getTarget();
            } else {
                return (T) proxy; // expected to be cglib proxy then, which is simply a specialized class
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 格式输出List<Map<String, Object>>的字符串。
     * @param list
     * @return
     */
    protected String printListMap(List<Map<String, Object>> list){
        String mapStr = "";
        for(Map<String, Object> map : list){

            for(Map.Entry<String, Object> item : map.entrySet()){
                mapStr += item.getKey() + ":" + item.getValue() + ",";
            }

            mapStr = mapStr.substring(0, mapStr.length() - 1) + "\n";

        }

        return mapStr;
    }
}
