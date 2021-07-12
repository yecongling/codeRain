package cn.ycl.common.utils;

import cn.ycl.common.utils.spring.SprintUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n 资源文件
 */
public class MessageUtils {

    public static String message(String code, Object... args){
        MessageSource messageSource = SprintUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
