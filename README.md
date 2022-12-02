### 多数据源框架

#### 基础使用

- 获取客户端对象

> 以下任意选择一种, 当然你提供的参数越多自然获取到的客户端您可操作的API也就越为丰富

```
// 该方法无需额外操作 获取到客户端对象之后直接操作API
IClient client = Yone.client(Integer dataSourceTypeCode, Class<T> genericType, Properties properties);

// 该方法无需额外操作 获取到客户端对象之后直接操作API
IClient client = Yone.client(Integer dataSourceTypeCode, Properties properties);

// 该方法获取到的客户端对象需要调用 IClient实例的build(Properties properties) 方法完成必要的初始化操作
IClient client = Yone.client(Integer dataSourceTypeCode, Class<T> genericType);
```

- 调用客户端相对应的API

> 获取到的`IClient`已经是初始化且拥有操作和调用API的能力

```
// 测试连接
boolean connection = client.testConnection();
```


