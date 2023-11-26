# explain 介绍
使用explain可以模拟优化器执行sql查询语句，从而知道mysql是如何处理你的sql语句的，
分析你的查询语句或表结构的性能瓶颈。

## 通过explain可以分析以下结果
1:表的读取顺序
2:数据读取操作的操作类型
3：哪些索引可能被使用
4：哪些索引被实际使用
5：表之间的引用关系
6：每张表有多少行被优化器查询

## explain 查询出的各个字段含义？

### 1 id
select 查询的序列号，包含一组数字，表示查询中执行select子句或者操作表的顺序。
id的结果共有三种，
id相同，执行顺序由上而下。
id不同，如果是子查询，id的序号会递增，id值越大优先级越高，越先被执行
id相同不同，同时存在：**id相同的由上往下执行，id值越大，优先级越高**，
如果table列显示<derived2>表示当前表是id为2的表的衍生表

### 2 select_type
常见的有以下几种：
simple， 
Primary，
subquery，
derived，
union，
union result

SIMPLE: 简单的select查询，查询中不包含子查询或者union 

PRIMARY： 查询中若包含任何复杂的子部分，最外层则被标记为primary 

SUBQUERY：在select或where列表中包含子查询 

DERIVED：**在from列表中包含的子查询被标记为derived（衍生）**，mysql会递归执行这些子查询，
         **把结果放在临时表中** 

UNION: 若第二个select出现在union之后，则被标记为union：若union包含在from子句的子查询中，
       外层select将被标记为：derived 

UNION RESULT：从union表获取查询结果的select 



### 3 table
指当前执行的表

### 4 type
表示查询使用了哪种类型，type包含的类型包括，
从好到次依次是
system > const > eq_ref > ref > range > index > all
一般最少达到range级别，最好达到ref。

system: 表只有一行数据（等于系统表），这是const类型的特列，很少出现

const：表示通过一次索引就找到了，出现场景用主键索引或者唯一索引情况，因只匹配一行数据，所以很快，
        如：将逐渐置于where列表中，mysql就能将查询转换成一个常量

eq_ref: 主键或者唯一索引扫描

ref：非唯一索引扫描，本质也是索引访问，返回匹配的单独值的行

range：检索一定范围的行，使用索引如between，<,> in等查询

index： full index scan（全索引扫描），遍历索引树的全部

all： 全表扫描

## 5 possible_key和key
possible_key：表示可能 用到的索引，一个或多个。查询涉及的字段上若存在索引，则列出，
但不一定被查询实际使用（因为查询优化器会选择最优的索引进行查询如主键，所以其他索引可能没被用到）
key：实际使用的索引，如果显示为null，则没有使用索引

**注：如果使用了索引覆盖（select后要查询的字段刚好和创建的索引字段完全想通过），
则该索引仅出现在key列表**

## 6 key_len
索引使用的字节数，越短越好，表示最大可能长度，并非实际长度，根据表定义计算得到的

## 7 ref
显示索引的哪一列被使用，如果是常数最好

## 8 rows
根据统计信息及索引选用情况，估算出找到数据所需要读取的行数，越少越好

## 9 Extra
包含不适合在其他列中显示但十分重要的额外信息

### 9.1 Using filesort（九死一生）
说明会使用一个外部的索引排序，而不是按照表内的索引顺序进行读取。
mysql中无法利用索引完成的排序称为“文件排序”
### 9.2 Using temporary（十死无生）
排序时候使用了临时表，常见于order by和分组查询group by
。。。。 9.8待完善

**参考文档**：https://blog.csdn.net/why15732625998/article/details/80388236
