package com.basic.auth.conf.oauth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.basic.client.user.UserClient;
import com.basic.commons.ReturnResult;
import com.basic.user.manager.vo.BasicManagerUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Objects;

/**
 * @author: Mr.zhang
 * @Date: 2020/12/4 23:05
 */
@Service
@Slf4j
public class MyUserService implements UserDetailsService {

    @Resource
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ReturnResult returnResult = userClient.findByManagerUserName(s);
        if (Objects.isNull(returnResult.getObj())) {
            log.error("未发现该用户数据：{}", s);
            throw new UsernameNotFoundException("the user is not found");
        }
        BasicManagerUserVo basicManagerUserVo = Convert.convert(BasicManagerUserVo.class, returnResult.getObj());
        return new org.springframework.security.core.userdetails.User(s, basicManagerUserVo.getPassword(),
                CollUtil.newArrayList());
//        }
    }

    /**
     * 后台管理员角色设置
     *
     * @param manager
     * @return
     */
//    private Collection<? extends GrantedAuthority> getAuthorities(Manager manager) {
////        final List<GrantedAuthority> authorities = CollectionUtil.newArrayList();
////       Object obj = baseRoleService.findById(manager.getId()).getObj();
////       log.info("获取角色：{}",obj);
////        if (!Objects.isNull(obj)) {
////            List roleVos = Convert.convert(ArrayList.class, obj);
////            roleVos.forEach(roleVo -> {
////                RoleVo r = Convert.convert(RoleVo.class, roleVo);
////                if (!StrUtil.isEmpty(r.getRoleEnglish())) {
////                    authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getRoleEnglish()));
////                }
////            });
////        }
////       Object objs = basePermissionsService.selectByUserName(manager.getUsername()).getObj();
////        log.info("获取权限：{}",objs);
////        if (!Objects.isNull(objs)) {
////            List permissionsVos = Convert.convert(List.class, objs);
////            permissionsVos.forEach(permissionsVo -> {
////                PermissionsVo p = Convert.convert(PermissionsVo.class, permissionsVo);
////                if (!StrUtil.isEmpty(p.getPermissionsEnglish())) {
////                    if(!Objects.isNull(p.getPermissionsVo())){
////                        p.getPermissionsVo().forEach(permissionsVos1 -> {
////                            if(!StrUtil.isEmpty(permissionsVos1.getPermissionsEnglish())){
////                                authorities.add(new SimpleGrantedAuthority(p.getPermissionsEnglish()+"_"+permissionsVos1.getPermissionsEnglish()));
////                            }
////                        });
////                    }
////                }
////            });
////        }
////        log.info("获取authorities：{}",authorities);
////        if (CollectionUtil.isEmpty(authorities)) {
////            authorities.add(new SimpleGrantedAuthority("admin"));
////        }
////        return authorities;
//        return null;
//    }
}
