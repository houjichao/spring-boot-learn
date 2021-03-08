# Java反应式框架Reactor中的Mono和Flux
## 1.前言
最近写关于响应式编程的东西有点多，很多同学反映对Flux和Mono
这两个Reactor中的概念有点懵逼。
但是目前Java响应式编程中我们对这两个对象的接触又最多，
诸如Spring WebFlux、RSocket、R2DBC。我开始也对这两个对象头疼，
所以今天我们就简单来探讨一下它们。

## 2. 响应流的特点
要搞清楚这两个概念，必须说一下响应流规范。它是响应式编程的基石。他具有以下特点：

* 响应流必须是无阻塞的。
* 响应流必须是一个数据流。
* 它必须可以异步执行。
* 并且它也应该能够处理背压。
背压是反应流中的一个重要概念，可以理解为，生产者可以感受到消费者反馈的消费压力，并根据压力进行动态调整生产速率。

形象点可以按照下面理解：
![alt 背压](https://img2020.cnblogs.com/other/1739473/202009/1739473-20200928222606736-422622749.png)

## 3. Publisher

由于响应流的特点，我们不能再返回一个简单的POJO对象来表示结果了。
必须返回一个类似Java中的Future的概念，在有结果可用时通知消费者进行消费响应。

Reactive Stream规范中这种被定义为Publisher<T> ，Publisher<T>是一个可以提供0-N个序列元素的提供者，
并根据其订阅者Subscriber<? super T>的需求推送元素。一个Publisher<T>可以支持多个订阅者，
并可以根据订阅者的逻辑进行推送序列元素。
下面这个Excel计算就能说明一些Publisher<T>的特点。
![alt publisher](https://img2020.cnblogs.com/other/1739473/202009/1739473-20200928222607141-761150947.gif)

A1-A9就可以看做Publisher<T>及其提供的元素序列。A10-A13分别是求和函数SUM(A1:A9)、
平均函数AVERAGE(A1:A9)、最大值函数MAX(A1:A9)、最小值函数MIN(A1:A9)，可以看作订阅者Subscriber。
假如说我们没有A10-A13，那么A1-A9就没有实际意义，它们并不产生计算。
这也是响应式的一个重要特点：当没有订阅时发布者什么也不做。

而Flux和Mono都是Publisher<T>在Reactor 3实现。
Publisher<T>提供了subscribe方法，允许消费者在有结果可用时进行消费。
如果没有消费者Publisher<T>不会做任何事情，他根据消费情况进行响应。 
Publisher<T>可能返回零或者多个，甚至可能是无限的，为了更加清晰表示期待的结果就引入了两个实现模型Mono和Flux。

## 4. Flux
Flux 是一个发出(emit)0-N个元素组成的异步序列的Publisher<T>,可以被onComplete信号或者onError信号所终止。
在响应流规范中存在三种给下游消费者调用的方法 onNext, onComplete, 和onError。下面这张图表示了Flux的抽象模型：
![alt flux](https://img2020.cnblogs.com/other/1739473/202009/1739473-20200928222607378-323495194.png)
以上的的讲解对于初次接触反应式编程的依然是难以理解的，所以这里有一个循序渐进的理解过程。

有些类比并不是很妥当，但是对于你循序渐进的理解这些新概念还是有帮助的。
### 传统数据处理
我们在平常是这么写的：
```
public List<ClientUser> allUsers() {
    return Arrays.asList(new ClientUser("felord.cn", "reactive"),
            new ClientUser("Felordcn", "Reactor"));
}
```
我们通过迭代返回值List来get这些元素进行再处理（消费），这种方式有点类似厨师做了很多菜，
吃不吃在于食客。需要食客主动去来吃就行了（pull的方式），至于喜欢吃什么不喜欢吃什么自己随意，怎么吃也自己随意。
### 流式数据处理
在Java 8中我们可以改写为流的表示：
```
public Stream<ClientUser> allUsers() {
    return  Stream.of(new ClientUser("felord.cn", "reactive"),
            new ClientUser("Felordcn", "Reactor"));
}
```
依然是厨师做了很多菜，但是这种就更加高级了一些，提供了菜品的搭配方式（不包含具体细节），
食客可以按照说明根据自己的习惯搭配着去吃，一但开始概不退换，吃完为止，过期不候。
### 反应式数据处理
在Reactor中我们又可以改写为Flux表示：
```
public Flux<ClientUser> allUsers(){
    return Flux.just(new ClientUser("felord.cn", "reactive"),
            new ClientUser("Felordcn", "Reactor"));
}
```
这时候食客只需要订餐就行了，做好了自然就呈上来，
而且可以随时根据食客的饭量进行调整。如果没有食客订餐那么厨师就什么都不用做。
当然不止有这么点特性，不过对于方便我们理解来说这就够了。

## 5. Mono
Mono 是一个发出(emit)0-1个元素的Publisher<T>,可以被onComplete信号或者onError信号所终止。
![alt mono](https://img2020.cnblogs.com/other/1739473/202009/1739473-20200928222607947-717859343.png)
这里就不翻译了，整体和Flux差不多，只不过这里只会发出0-1个元素。
也就是说不是有就是没有。象Flux一样，我们来看看Mono的演化过程以帮助理解。
### 传统数据处理
```
public ClientUser currentUser () {
    return isAuthenticated ? new ClientUser("felord.cn", "reactive") : null;
}
```
直接返回符合条件的对象或者null。
### Optional的处理方式
```
public Optional<ClientUser> currentUser () {
    return isAuthenticated ? Optional.of(new ClientUser("felord.cn", "reactive"))
            : Optional.empty();
}
```
这个Optional我觉得就有反应式的那种味儿了，当然它并不是反应式。
当我们不从返回值Optional取其中具体的对象时，我们不清楚里面到底有没有，但是Optional是一定客观存在的,不会出现NPE问题。

### 反应式数据处理
```
public Mono<ClientUser> currentUser () {
    return isAuthenticated ? Mono.just(new ClientUser("felord.cn", "reactive"))
            : Mono.empty();
}
```
和Optional有点类似的机制，当然Mono不是为了解决NPE问题的，它是为了处理响应流中单个值（也可能是Void）而存在的。
## 6. 总结
Flux和Mono是Java反应式中的重要概念，但是很多同学包括我在开始都难以理解它们。
这其实是规定了两种流式范式，这种范式让数据具有一些新的特性，比如基于发布订阅的事件驱动，异步流、背压等等。
另外数据是推送（Push）给消费者的以区别于平时我们的拉（Pull）模式。
同时我们可以像Stream Api一样使用类似map、flatmap等操作符（operator）来操作它们。
对Flux和Mono这两个概念需要花一些时间去理解它们，不能操之过急。
