# server-instance-in-memory-cache
# 服务器实例内存存储

##使用场景
适合于数据量不大，同时被频繁从数据库读取，并且变化频度很小的数据。比如，首页的焦点图、元数据相关的列表，比如城市、城区等。

##简单说明
1.示例使用统一的DataStorage存储任何类型的内存对象，提供泛型和非泛型方式的get方法。泛型类型的方法用于减少suppressWarning的数量，和调用处的优雅。

2.数据重新加载实现定时任务和消息的通知的方式。示例中消息通知采用redis的sub/pub消息机制。

3.示例同时是一个很好的springMVC+redis+maven（包含profile）的模板，方便在该基础上快速的搭建起更大的工程。

4.其中的测试直接用springMVC的controller给出的链接方式，便于直接在浏览器进行getData和reload，查看更新的效果。
