### 关于Reactor的介绍
Reactor是Spring中的一个子项目是一个基于java的响应式编程框架，
此框架是 Pivotal 公司（开发 Spring 等技术的公司）开发的，
实现了 Reactive Programming（反应式编程即响应式编程） 思想，
符合 Reactive Streams 规范（Reactive Streams 是由 Netflix、TypeSafe、Pivotal 等公司发起的）的一项技术。
其名字有反应堆之意，反映了其背后的强大的性能。 

Spring 5 对应的Reactor框架的版本为3.1.0。
（由于Spring5实现了很多关于函数式编程的东西，所以jdk版本至少得1.8）

### 关于反应式编程的思想：
反应式编程框架主要采用了观察者模式，而SpringReactor的核心则是对观察者模式的一种衍伸。关于观察者模式的架构中被观察者(Observable)和观察者(Subscriber)处在不同的线程环境中时，由于者各自的工作量不一样，导致它们产生事件和处理事件的速度不一样，这时就出现了两种情况：

* 1.被观察者产生事件慢一些，观察者处理事件很快。那么观察者就会等着被观察者发送事件，
（好比观察者在等米下锅，程序等待，这没有问题）。
* 2.被观察者产生事件的速度很快，而观察者处理很慢。那就出问题了，如果不作处理的话，
事件会堆积起来，最终挤爆你的内存，导致程序崩溃。
（好比被观察者生产的大米没人吃，堆积最后就会烂掉）。
为了方便下面理解Mono和Flux，也可以理解为Publisher
（发布者也可以理解为被观察者）主动推送数据给Subscriber（订阅者也可以叫观察者），
如果Publisher发布消息太快，超过了Subscriber的处理速度，如何处理。
这时就出现了Backpressure（背压-----指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略）

### Reactor的主要类：
在Reactor中，经常使用的类并不多，主要有以下两个：

* Mono 实现了 org.reactivestreams.Publisher 接口，代表0到1个元素的发布者（Publisher）。
* Flux 同样实现了 org.reactivestreams.Publisher 接口，代表0到N个元素的发布者（Subscriber）。
可能会使用到的类：

Scheduler 表示背后驱动反应式流的调度器，通常由各种线程池实现。

### 关于Reactive Streams、Spring Reactor 和 Spring Flux（Web Flux）之间的关系
Reactive Streams 是规范，Reactor 实现了 Reactive Streams。
Web Flux 以 Reactor 为基础，实现 Web 领域的反应式编程框架

### 关于Mono和Flux
Mono和Flux都是Publisher（发布者）。

其实，对于大部分业务开发人员来说，当编写反应式代码时，我们通常只会接触到 Publisher 这个接口，
对应到 Reactor 便是 Mono 和 Flux。对于 Subscriber 和 Subcription 这两个接口，
Reactor 必然也有相应的实现。但是，这些都是 Web Flux 和 Spring Data Reactive 这样的框架用到的。
如果不开发中间件，通常开发人员是不会接触到的。

比如，在 Web Flux，你的方法只需返回 Mono 或 Flux 即可。
你的代码基本也只和 Mono 或 Flux 打交道。而 Web Flux 则会实现 Subscriber ，
onNext 时将业务开发人员编写的 Mono 或 Flux 转换为 HTTP Response 返回给客户端。