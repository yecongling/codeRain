# codeRain
开发分支

基于若依系统（仅使用其中框架和登录注册流程-目前），改造成SSM架构

目前遇到的问题解决：
1、2021年7月21日遇到问题：
    SSM shiro ehcache中配置时通过自定义去加载cacheManager时提示
    net.sf.ehcache.CacheException: Another CacheManager with same name 'coderain' already exists in the same VM
    说明上下文加载了两次coderain ，还未找到为什么两次加载 
    晚上23:54已找到问题，是java里面初始化了一次，且XML中又去配置了一次bean，导致
2、2021年07月22日 遇到问题 AsyncManager类无法初始化，估计是内部调用SpringUtils.getBean("scheduledExecutorService");时，getBean方法中获取不到对应的bean时出现空指针异常，导致报错，引起该类无法初始化   
    07月22日12:50找到问题，beanFactory 未设置到值，加了@Component注解没有扫描该包下的文件，导致类未初始化，就未初始化beanFactory变量
3、2021年7月22日晚上遇到问题 No SecurityManager accessible to the calling code 
    This is an invalid application configuration.    估计原因是对应的bean的初始化时机不对，引用不起
    2021年7月26日晚解决上述问题，原因是：在初始化sessionManager时调用了SpringSessionValidationScheduler这个bean，而这个bean类中使用了set的方式注入sessionManager，导致循环引用，虽然加了@lazy注解，但是使用set方式依旧会在没有初始化sessionManager时去获取这个bean，改用成员属性注入即可


2021年9月15日   添加系统菜单功能，添加页面，整体完成30%
