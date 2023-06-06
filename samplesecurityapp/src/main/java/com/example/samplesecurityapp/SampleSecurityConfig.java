package com.example.samplesecurityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SampleSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // CSRF対策
        http.csrf().disable();
        // リクエストの制御
        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers("/").permitAll() // アクセス許可
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/img/**").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated(); // 認証を求める
        });
        // フォームログインの設定
        http.formLogin(form -> {
            form.defaultSuccessUrl("/secret"); // ログイン成功時にアクセスするURL
        });
        // HttpFilterChainの作成
        // Beanの中なので、returnで返したインスタンスがそのままBeanとして登録され使用できる
        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);

        // ユーザ登録 (一回のみ実行)
         users.createUser(makeUser("rila", "1111", "USER"));
         users.createUser(makeUser("millet", "2222", "USER"));
         users.createUser(makeUser("admin", "admin", "ADMIN"));

        return users;
    }

    private UserDetails makeUser(String user, String pass, String role){
        return User.withUsername(user)
                .password(
                        PasswordEncoderFactories
                                .createDelegatingPasswordEncoder()
                                .encode(pass))
                .roles(role)
                .build();
    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        String username = "user";
//        String password = "pass";
//
//        UserDetails user = User.withUsername(username)
//                .password( // パスワードのencoding
//                        PasswordEncoderFactories
//                                .createDelegatingPasswordEncoder()
//                                .encode(password)
//                )
//                .roles("USER") // ロール: 管理者 or 一般ユーザー
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
