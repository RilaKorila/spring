package com.example.samplesecurityapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SampleSecurityConfig {
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
    public InMemoryUserDetailsManager userDetailsManager(){
        String username = "user";
        String password = "pass";

        UserDetails user = User.withUsername(username)
                .password( // パスワードのencoding
                        PasswordEncoderFactories
                                .createDelegatingPasswordEncoder()
                                .encode(password)
                )
                .roles("USER") // ロール: 管理者 or 一般ユーザー
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
