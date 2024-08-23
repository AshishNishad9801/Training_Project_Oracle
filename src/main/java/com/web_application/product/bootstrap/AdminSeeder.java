package com.web_application.product.bootstrap;

import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.web_application.product.dtos.RegisterUserDto;
import com.web_application.product.entity.Role;
import com.web_application.product.entity.RoleEnum;
import com.web_application.product.entity.User;
import com.web_application.product.repository.RoleRepository;
import com.web_application.product.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Order(2)
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent>{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createAdmin();
        this.createUsers();
    }

    private void createAdmin() {
        RegisterUserDto userdto = new RegisterUserDto();
        userdto.setEmail("admin1@admin.com");
        userdto.setName("admin1");
        userdto.setPassword("qwerty");
        Optional <Role> optionalrole = roleRepository.findByName(RoleEnum.ADMIN);
        Optional <User> optionaluser = userRepository.findByEmail(userdto.getEmail());        if (optionalrole.isEmpty() || optionaluser.isPresent())
        return;
        User user = new User();
        user.setEmail(userdto.getEmail());
        user.setName(userdto.getName());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setRole(optionalrole.get());
        userRepository.save(user);
    }

    // create two users
    private void createUsers() {
        RegisterUserDto userdto = new RegisterUserDto();
        userdto.setEmail("user1@user.com");
        userdto.setName("user1");
        userdto.setPassword("qwerty");
        Optional <Role> optionalrole = roleRepository.findByName(RoleEnum.USER);
        Optional <User> optionaluser = userRepository.findByEmail(userdto.getEmail());
        if (optionalrole.isEmpty() || optionaluser.isPresent())
        return;
        User user = new User();
        user.setEmail(userdto.getEmail());
        user.setName(userdto.getName());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setRole(optionalrole.get());
        userRepository.save(user);
        userdto.setEmail("user2@user.com");
        userdto.setName("user2");
        userdto.setPassword("qwerty");
        optionalrole = roleRepository.findByName(RoleEnum.USER);
        optionaluser = userRepository.findByEmail(userdto.getEmail());
        if (optionalrole.isEmpty () || optionaluser.isPresent())
        return;
        user = new User();
        user.setEmail(userdto.getEmail());
        user.setName(userdto.getName());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setRole(optionalrole.get());
        userRepository.save(user);
    }

}
