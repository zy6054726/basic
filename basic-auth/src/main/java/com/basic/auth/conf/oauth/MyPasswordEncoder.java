package com.basic.auth.conf.oauth;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.basic.commons.ConstantUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.zhang
 */
@Service
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return SecureUtil.md5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
//        return StrUtil.equals(s, SecureUtil.md5(charSequence.toString()));
        return StrUtil.equals(s, SecureUtil.md5(charSequence.toString()));
    }
}
