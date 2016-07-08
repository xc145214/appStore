package com.appStore.support;

import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 定义测试套件的公共行为。
 *
 * <p>
 *     <ul>
 *         公共行为：
 *         <li>applicationContext-test.xml作为全局ApplicationContext的配置加载。</li>
 *         <li>test.properties作为全局Properties配置加载。</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     对每个包都有一个测试套件，如果当前有子包，那就通过SuiteClasses组件将子包中的测试套件
 *     加入进来；如果当前没有子包，那就将当前路径所有测试类加入进来。目的时统一组织测试
 *     行为。
 * </p>
 *
 * <p>
 *     打开ApplicationContext配置中的test profile，如果有的话。
 * </p>
 *
 * <p>
 *     如果要在测试子类上自定义{@link ContextConfiguration}，一定要
 *     带上value/locations/classes/initializers中的一个。如果要使用默认不加参数的{@link ContextConfiguration}，
 *     不能继承此类，并且不能继承{@link AbstractJUnit4SpringContextTests}/{@link org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests}
 *     等，所有的注解需要自己添加(包含{@link org.springframework.test.context.TestExecutionListeners}等)。
 * </p>
 *
 *
 */
@ContextConfiguration("classpath:applicationContext-test.xml")
@PropertySource("classpath:test.properties")
@ActiveProfiles({"test", "monitor"})
public abstract class TestNoTransaction extends AbstractJUnit4SpringContextTests {
}
