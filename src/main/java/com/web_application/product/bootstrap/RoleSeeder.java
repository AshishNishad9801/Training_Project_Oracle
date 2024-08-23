package com.web_application.product.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.web_application.product.entity.Role;
import com.web_application.product.entity.RoleEnum;
import com.web_application.product.repository.RoleRepository;

import lombok.AllArgsConstructor;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;
@Component
@AllArgsConstructor
@Order(1)
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent>{
    // @Autowired
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       this.loadRoles();
    }

    private void loadRoles(){
        RoleEnum[] rolenames = new RoleEnum[] {RoleEnum.USER,RoleEnum.ADMIN};
        Map <RoleEnum,String> roledescriptionmap = Map.of(
            RoleEnum.USER,"user",
            RoleEnum.ADMIN,"admin"
        );

        Arrays.stream(rolenames).forEach((rolename) -> {
            Optional<Role> role = roleRepository.findByName(rolename);
            role.ifPresentOrElse(System.out::println, () -> {
                Role newRole = new Role();
                newRole.setName(rolename);
                newRole.setDescription(roledescriptionmap.get(rolename));
                roleRepository.save(newRole);
            });
        });
    }



}
