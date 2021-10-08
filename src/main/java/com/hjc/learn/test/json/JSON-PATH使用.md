###一、JSONPATH使用需要的包
<dependency>
    <groupId>com.jayway.jsonpath</groupId>
    <artifactId>json-path</artifactId>
    <version>2.4.0</version>
</dependency>
 
###二、使用说明
1、JSONPath是xpath在json的应用
2、JSONPath 是参照xpath表达式来解析xml文档的方式，json数据结构通常是匿名的并且不一定需要有根元素。
3、JSONPath 用一个抽象的名字$来表示最外层对象
4、JSONPath 允许使用通配符 * 表示所以的子元素名和数组索引


###三、JSONPATH表达式语法
JSONPath 表达式可以使用.符号解析Json：.store.book[0].title 或者使用[]符号 ['store']['book'][0]['title']
1、JsonPath 表达式可以用 . 或者 [] 表示：
```$java
$.store.book[0].title
或者
$['store']['book'][0]['title']
```
| 操作符 | 描述 | 
| :----: | :----: |
| $ | 要查询的根元素。这开始了所有的路径表达式。 |
| @ | 由筛选器谓词处理的当前节点。|
| * | 通配符。在任何需要名称或数字的地方可用。|
| .. |深度扫描。在任何需要名称的地方都可用|
| ['<name>' (, '<name>')] | 用括号表示的child或children |
| [<number> (, <number>)] | 数组索引或索引 |
| [start:end] | 数组切片操作符 | 
| [?(<expression>)] | 筛选器表达式。表达式必须计算为布尔值。 |