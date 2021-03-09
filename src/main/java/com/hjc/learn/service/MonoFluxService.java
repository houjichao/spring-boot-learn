package com.hjc.learn.service;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Mono Flux
 *
 * @author houjichao
 */
public class MonoFluxService {

    public static void main(String[] args) {

        /**
         * 通过 Flux 类的静态方法创建 Flux 序列
         */
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);

        Flux<String> stringFlux = Flux.just("Hello", "World");
        //stringFlux.subscribe(System.out::println);

        /**
         * subscribe 介绍
         *
         * subscribe()方法表示对数据流的订阅动作，subscribe()方法有多个重载的方法
         */
        //订阅方式一
        stringFlux.subscribe(val -> {
            System.out.println("val:{}" + val);
        }, error -> {
            System.out.println("error:{}" + error);
        }, () -> {
            System.out.println("Finished");
        }, subscription -> {
            subscription.request(1);
        });
        stringFlux.subscribe(val -> System.out.println("val:{}" + val), error -> System.out.println("error:{}" + error), () -> System.out.println("Finished"), subscription -> subscription.request(1));

        //订阅方式二
        stringFlux.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:{}" + s);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

    }
}
