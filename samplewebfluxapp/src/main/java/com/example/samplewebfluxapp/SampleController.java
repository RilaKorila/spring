package com.example.samplewebfluxapp;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Controller
public class SampleController {

    @Bean
    public RouterFunction<ServerResponse> router(){
        return route(GET("/f/hello"), this::hello)
                .andRoute(GET("/f/flux2"), this::flux2);
    }

    Mono<ServerResponse> flux2(ServerRequest res){
        Map map = new HashMap();
        map.put("msg", "わーーー！");

        // 追加したい要素をmapに全部突っ込んでまとめて渡す
        return ok().contentType(MediaType.TEXT_HTML).render("flux", map);
    }

    Mono<ServerResponse> hello(ServerRequest req){
        return ok().body(Mono.just("Hello, 関数型のrouterだよー"), String.class);
    }

    @RequestMapping("/f/flux")
    Mono<Rendering> flux(){
        // 引数で
        return Mono.just(Rendering.view("flux")
                        .modelAttribute("msg", "わーーーーい！")
                        .build());
    }

    @RequestMapping("/f/flux3")
    Mono<Rendering> flux3(Model model){
        model.addAttribute("msg", "modelも使えるみたいだよ");
        return Mono.just(Rendering.view("flux").build());
    }
}
