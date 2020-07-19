---
title: i-LogisticsSystem v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
highlight_theme: darkula
headingLevel: 2

---

# i-LogisticsSystem

> v1.0.0

# 客户管理/供应商管理

## DELETE 删除供应商

DELETE /clientele/suppliers/{supplierId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|supplierId|path|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*删除后的供货商列表*

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[Supplier](#schemasupplier)]|false|none|删除后的供货商列表|
|» Supplier|[Supplier](#schemasupplier)|false|none|供应商|
|»» supplierId|string|true|none|供应商编号|
|»» brandName|string|true|none|供应商品牌名|
|»» managerName|string|true|none|联系人姓名|
|»» tel|string|true|none|联系人电话|
|»» email|string|true|none|供应商邮箱|
|»» province|string|true|none|供应商总部所在省|
|»» city|string|true|none|供应商总部所在市|
|»» district|string|true|none|供应商总部所在区|
|»» addr|string|true|none|供应商总部详细地址|

## GET 查询供应商商品供应情况

GET /clientele/suppliers/{supplierId}/itemSupply

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|supplierId|path|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[ItemSupplyResp](#schemaitemsupplyresp)|

## GET 获取供应商列表

GET /clientele/suppliers

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|pageNum|query|string|false|当前页码|
|pageSize|query|string|false|每页数量|

> 返回示例

> 成功

```json
[
  {
    "supplierId": "SUP-H-001",
    "brandName": "且气认往",
    "tel": "18170572554",
    "email": "w.xdalw@qq.com",
    "province": "河南省",
    "city": "天津市",
    "diatrict": "sunt do",
    "addr": "aliquip amet dolore"
  },
  {
    "supplierId": "SUP-H-002",
    "brandName": "系非毛从每",
    "tel": "19831854924",
    "email": "o.yhkyblpu@qq.com",
    "province": "河南省",
    "city": "洛阳市",
    "diatrict": "deserunt ea Duis",
    "addr": "qui amet dolore"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[Supplier](#schemasupplier)]|false|none|[供应商]|
|» Supplier|[Supplier](#schemasupplier)|false|none|供应商|
|»» supplierId|string|true|none|供应商编号|
|»» brandName|string|true|none|供应商品牌名|
|»» managerName|string|true|none|联系人姓名|
|»» tel|string|true|none|联系人电话|
|»» email|string|true|none|供应商邮箱|
|»» province|string|true|none|供应商总部所在省|
|»» city|string|true|none|供应商总部所在市|
|»» district|string|true|none|供应商总部所在区|
|»» addr|string|true|none|供应商总部详细地址|

## POST 新增供应商

POST /suppliers

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "supplier": {
      "title": "Supplier",
      "description": "供应商",
      "type": "object",
      "required": [
        "supplierId",
        "brandName",
        "tel",
        "email",
        "province",
        "city",
        "district",
        "addr",
        "managerName"
      ],
      "properties": {
        "supplierId": {
          "type": "string",
          "description": "供应商编号"
        },
        "brandName": {
          "type": "string",
          "description": "供应商品牌名",
          "example": "李四"
        },
        "managerName": {
          "type": "string",
          "description": "联系人姓名"
        },
        "tel": {
          "type": "string",
          "description": "联系人电话",
          "example": "10000"
        },
        "email": {
          "type": "string",
          "description": "供应商邮箱"
        },
        "province": {
          "type": "string",
          "description": "供应商总部所在省"
        },
        "city": {
          "type": "string",
          "description": "供应商总部所在市"
        },
        "district": {
          "type": "string",
          "description": "供应商总部所在区"
        },
        "addr": {
          "type": "string",
          "description": "供应商总部详细地址"
        }
      }
    },
    "itemSupplyList": {
      "type": "array",
      "items": {
        "type": "string",
        "description": "供应商品Id"
      },
      "description": "供应商供应商品列表"
    }
  },
  "required": [
    "supplier",
    "itemSupplyList"
  ]
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[SupplierAddReq](#schemasupplieraddreq)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## PATCH 修改供应商信息

PATCH /clientele/supplier/{supplierId}

可以修改供应商的 经理姓名 联系方式 邮箱

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|supplierId|path|string|true|none|
|managerName|query|string|false|经理姓名|
|tel|query|string|false|经理联系电话|
|email|query|string|false|邮箱|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[Supplier](#schemasupplier)|

# 客户管理/买家管理

## GET 查询买家列表

GET /clientele/customers

可以根据限定条件：顾客姓名 所在省 城市 行政区 检索顾客列表

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|name|query|string|false|顾客姓名，可为空|
|district|query|string|false|顾客所在行政区，可为空|
|city|query|string|false|顾客所在城市，可为空|
|province|query|string|false|顾客所在省，可为空|
|pageNum|query|string|false|当前页码|
|pageSize|query|string|false|每页数量|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

*pageResult*

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» pageNum|integer|true|none|页码|
|» pageSize|integer|true|none|页大小|
|» totalSize|integer|true|none|总商品数|
|» totalPages|integer|true|none|总页数|
|» content|[[CustomerWithId](#schemacustomerwithid)]|true|none|查询得到的顾客列表|
|»» Customer|[CustomerWithId](#schemacustomerwithid)|false|none|客户|
|»»» customerId|integer(int64)|true|none|客户id|
|»»» name|string|true|none|客户姓名|
|»»» tel|string|true|none|客户邮箱|
|»»» email|string|true|none|客户电话|
|»»» addr|string|true|none|客户详细地址|
|»»» district|string|true|none|客户所在行政区区|
|»»» city|string|true|none|客户所在市|
|»»» province|string|true|none|所在省|

## GET customerId自动补全

GET /clientele/customerIds

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|infix|query|string|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 配置策略

## PUT 修改配送策略

PUT /settings/taskSettings

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "byTime": {
      "type": "boolean",
      "description": "是否按到收货地的路径时间分拣（默认按距离）"
    },
    "outStockRatio": {
      "type": "integer",
      "description": "缺货比例（缺货商品数量达到剩余可用数量的一定比例就开始新增调度，[50%,100%]）"
    },
    "transferNum": {
      "type": "integer",
      "description": "调货数量（小于缺货数量则按缺货数量）"
    },
    "transferSite": {
      "type": "string",
      "description": "调货主站出货地。距离优先（D），数量多者优先（N）"
    }
  },
  "required": [
    "byTime",
    "outStockRatio",
    "transferNum",
    "transferSite"
  ]
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|
|» byTime|body|boolean|true|是否按到收货地的路径时间分拣（默认按距离）|
|» outStockRatio|body|integer|true|缺货比例（缺货商品数量达到剩余可用数量的一定比例就开始新增调度，[50%,100%]）|
|» transferNum|body|integer|true|调货数量（小于缺货数量则按缺货数量）|
|» transferSite|body|string|true|调货主站出货地。距离优先（D），数量多者优先（N）|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 获取配送策略

GET /settings/taskSettings

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» byTime|boolean|true|none|是否按到收货地的路径时间分拣（默认按距离）|
|» outStockRatio|integer|true|none|缺货比例（缺货商品数量达到剩余可用数量的一定比例就开始新增调度，[50%,100%]）|
|» transferNum|integer|true|none|调货数量（小于缺货数量则按缺货数量）|
|» transferSite|string|true|none|调货主站出货地。距离优先（D），数量多者优先（N）|

## GET 获取出库分拣策略

GET /settings/siteoutSettings

> 出库时商品的分拣策略
> - 选项一：默认从单个库房出库，单个库房存货不足时剩余量重新分拣出库
> - 选项二：默认从单个库房出库，单个库房存货不足时按比例从多个库房分批出库
> - 选项三：默认从单个库房出库，到达一定值时按比例从多个库房分批出库

> 返回示例

> 成功

```json
{
  "threshold": 1000,
  "option": 3
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» option|integer|true|none|1：选项一；2：选项二；3：选项三|
|» threshold|integer|true|none|若选择选项三，该参数为阈值|

## PUT 修改出库分拣策略

PUT /settings/siteoutSettings

> 出库时商品的分拣策略
> - 选项一：默认从单个库房出库，单个库房存货不足时剩余量重新分拣出库
> - 选项二：默认从单个库房出库，单个库房存货不足时按比例从多个库房分批出库
> - 选项三：默认从单个库房出库，到达一定值时按比例从多个库房分批出库

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|option|query|string|true|1：选项一；2：选项二；3：选项三|
|threshold|query|string|false|若选择选项三，需提供该数据作为阈值|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 获取出入库审核策略

GET /settings/siteIOSettings

> 返回示例

> 成功

```json
{
  "siteOutTypeLimit": false,
  "totalPriceAmount": 0,
  "totalNum": 0,
  "enable": true,
  "categoryIdWhiteList": [],
  "totalNumLimit": true,
  "categoryIdLimit": false,
  "siteInTypeLimit": false,
  "totalPriceLimit": true,
  "siteOutTypeWhiteList": [],
  "siteInTypeWhiteList": []
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» enable|boolean|true|none|是否启用该配置，只有为true时提供的参数有效，为false时所有出入库单都需审核|
|» totalPriceLimit|boolean|true|none|是否启用入库单总金额 判断策略, 默认为true|
|» totalPriceAmount|number|true|none|入库单总金额大于等于多少时需要进行审核, 默认为0|
|» totalNumLimit|boolean|true|none|是否启用入库数量 判断策略，默认为true|
|» totalNum|integer|true|none|入库数量大于等于多少时需要进行审核，默认为0|
|» categoryIdLimit|boolean|true|none|是否启用大类审核白名单, 默认为false|
|» categoryIdWhiteList|[string]|true|none|若启用, 指定CategoryId不需要进行审核|
|» siteInTypeLimit|boolean|true|none|是否启用入库类型审核白名单，默认为false|
|» siteInTypeWhiteList|[integer]|true|none|若启用，指定类型的入库单不需要进行审核|
|» siteOutTypeLimit|boolean|true|none|是否启用出库类型审核白名单，默认为false|
|» siteOutTypeWhiteList|[integer]|true|none|若启用，指定类型的出库单不需要进行审核|

## PUT 修改出入库审核策略

PUT /settings/siteIOSettings

> Body 请求参数

```yaml
type: object
properties:
  enable:
    type: string
    description: 是否启用该配置，只有为true时提供的参数有效，为false时所有出入库单都需审核
  totalPriceLimit:
    type: string
    description: 是否启用入库单总金额 判断策略, 默认为true
  totalPriceAmount:
    type: string
    description: 入库单总金额大于等于多少时需要进行审核, 默认为0
  totalNumLimit:
    type: string
    description: 是否启用入库数量 判断策略，默认为true
  totalNum:
    type: string
    description: 入库数量大于等于多少时需要进行审核，默认为0
  categoryIdLimit:
    type: string
    description: 是否启用大类审核白名单, 默认为false
  categoryIdWhiteList:
    type: string
    description: 若启用, 指定CategoryId不需要进行审核
  siteInTypeLimit:
    type: string
    description: 是否启用入库类型审核白名单，默认为false
  siteInTypeWhiteList:
    type: string
    description: 若启用，指定类型的入库单不需要进行审核
  siteOutTypeLimit:
    type: string
    description: 是否启用出库类型审核白名单，默认为false
  siteOutTypeWhiteList:
    type: string
    description: 若启用，指定类型的出库单不需要进行审核
required:
  - enable

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|
|» enable|body|string|true|是否启用该配置，只有为true时提供的参数有效，为false时所有出入库单都需审核|
|» totalPriceLimit|body|string|false|是否启用入库单总金额 判断策略, 默认为true|
|» totalPriceAmount|body|string|false|入库单总金额大于等于多少时需要进行审核, 默认为0|
|» totalNumLimit|body|string|false|是否启用入库数量 判断策略，默认为true|
|» totalNum|body|string|false|入库数量大于等于多少时需要进行审核，默认为0|
|» categoryIdLimit|body|string|false|是否启用大类审核白名单, 默认为false|
|» categoryIdWhiteList|body|string|false|若启用, 指定CategoryId不需要进行审核|
|» siteInTypeLimit|body|string|false|是否启用入库类型审核白名单，默认为false|
|» siteInTypeWhiteList|body|string|false|若启用，指定类型的入库单不需要进行审核|
|» siteOutTypeLimit|body|string|false|是否启用出库类型审核白名单，默认为false|
|» siteOutTypeWhiteList|body|string|false|若启用，指定类型的出库单不需要进行审核|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 获取订单审核策略

GET /settings/orderSettings

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» enable|boolean|true|none|是否启用该配置，只有为true时提供的参数有效，为false时所有订单都需审核|
|» totalPriceLimit|boolean|true|none|是否启用订单总金额 判断策略, 默认为true|
|» totalPriceAmount|number|true|none|订单总金额大于等于多少时需要进行审核, 默认为0|
|» categoryIdLimit|boolean|true|none|是否启用大类审核白名单, 默认为false|
|» categoryIdWhiteList|[string]|true|none|若启用, 指定CategoryId不需要进行审核|
|» customerIdLimit|boolean|true|none|是否启用顾客白名单, 默认为false|
|» customerIdWhiteList|[string]|true|none|若启用, 指定customerId不需要进行审核|

## PUT 修改订单审核策略

PUT /settings/orderSettings

> Body 请求参数

```yaml
type: object
properties:
  enable:
    type: string
    description: 是否启用该配置，只有为true时提供的参数有效，为false时所有订单都需审核
  totalPriceLimit:
    type: string
    description: 是否启用订单总金额 判断策略, 默认为true
  totalPriceAmount:
    type: string
    description: 订单总金额大于等于多少时需要进行审核, 默认为0
  categoryIdLimit:
    type: string
    description: 是否启用大类审核白名单, 默认为false
  categoryIdWhiteList:
    type: string
    description: 若启用, 指定CategoryId不需要进行审核
  customerIdLimit:
    type: string
    description: 是否启用顾客白名单, 默认为false
  customerIdWhiteList:
    type: string
    description: 若启用, 指定customerId不需要进行审核
required:
  - enable

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|
|» enable|body|string|true|是否启用该配置，只有为true时提供的参数有效，为false时所有订单都需审核|
|» totalPriceLimit|body|string|false|是否启用订单总金额 判断策略, 默认为true|
|» totalPriceAmount|body|string|false|订单总金额大于等于多少时需要进行审核, 默认为0|
|» categoryIdLimit|body|string|false|是否启用大类审核白名单, 默认为false|
|» categoryIdWhiteList|body|string|false|若启用, 指定CategoryId不需要进行审核|
|» customerIdLimit|body|string|false|是否启用顾客白名单, 默认为false|
|» customerIdWhiteList|body|string|false|若启用, 指定customerId不需要进行审核|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 订单管理

## POST 新增订单

POST /orders

### 订单新增入口
- 用于手动新增与接受商城订单信息

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "order": {
      "title": "Order",
      "description": "订单",
      "type": "object",
      "required": [
        "orderId",
        "customerId",
        "payStatus",
        "processStatus",
        "billPro",
        "billCity",
        "billDistrict",
        "billAddr",
        "totalPrice",
        "billName"
      ],
      "properties": {
        "orderId": {
          "type": "integer",
          "description": "订单Id"
        },
        "customerId": {
          "type": "string",
          "example": "2020-07-05 12:00:00.000",
          "description": "买家id"
        },
        "payStatus": {
          "type": "string",
          "description": "付款状态: 已付款（P）未付款（W）"
        },
        "processStatus": {
          "type": "string",
          "description": "订单处理状态:未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close） "
        },
        "createDateTime": {
          "type": "string",
          "description": "订单创建时间"
        },
        "payDateTime": {
          "type": "string",
          "description": "订单付款时间"
        },
        "billName": {
          "type": "string",
          "description": "收件人姓名"
        },
        "billPro": {
          "type": "string",
          "example": "支付宝",
          "description": "收件所在省"
        },
        "billCity": {
          "type": "string",
          "example": "王五",
          "description": "收件所在市"
        },
        "billDistrict": {
          "type": "string",
          "description": "收件所在区"
        },
        "billAddr": {
          "type": "string",
          "example": "北京市",
          "description": "收件详细地址"
        },
        "totalPrice": {
          "type": "number",
          "description": "总付款"
        },
        "shippingCost": {
          "type": "number",
          "description": "运费"
        },
        "payMethod": {
          "type": "string",
          "description": "支付方式"
        },
        "note": {
          "type": "string",
          "description": "备注"
        }
      }
    },
    "orderItemList": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "orderId": {
            "type": "integer",
            "description": "订单id"
          },
          "taskId": {
            "type": "any",
            "description": "任务单id"
          },
          "itemNum": {
            "type": "integer",
            "description": "商品数量",
            "minimum": 0,
            "maximum": 1000000,
            "exclusiveMinimum": true,
            "exclusiveMaximum": true
          },
          "status": {
            "type": "string",
            "description": "处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)"
          },
          "total": {
            "type": "number",
            "description": "单类商品付款总额"
          },
          "item": {
            "title": "Item",
            "description": "商品项",
            "type": "object",
            "properties": {
              "itemId": {
                "type": "string",
                "description": "商品id",
                "example": "可口可乐 330ml 罐装"
              },
              "categoryId": {
                "type": "string",
                "description": "大类id"
              },
              "name": {
                "type": "string",
                "description": "商品名称"
              },
              "descn": {
                "type": "string",
                "description": "文字描述"
              },
              "unitCost": {
                "type": "number",
                "format": "double",
                "description": "原价",
                "example": 3,
                "minimum": 0
              },
              "listPrice": {
                "type": "number",
                "format": "double",
                "description": "售价",
                "example": 3,
                "minimum": 0,
                "exclusiveMinimum": true
              },
              "imgUrl": {
                "type": "string",
                "description": "图片url",
                "example": "易拉罐 红罐 可乐 可口可乐"
              },
              "status": {
                "type": "string",
                "description": "商品状态：上架（P）；下架（F）",
                "example": "http://baidu.com/1.png"
              }
            },
            "required": [
              "itemId"
            ]
          }
        },
        "required": [
          "orderId",
          "itemNum",
          "status",
          "total",
          "item"
        ],
        "description": "订单商品项"
      },
      "minItems": 1,
      "description": "订单商品项列表"
    },
    "manual": {
      "type": "boolean",
      "description": "是否为手动新增订单: 1为是, 0为不是"
    }
  },
  "required": [
    "order",
    "orderItemList",
    "manual"
  ]
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[OrderAddReq](#schemaorderaddreq)|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 订单管理/订单查询

## GET 获取订单列表

GET /orders

获取订单

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|query|string|false|订单id|
|billName|query|string|false|收件人名称|
|processStatus|query|string|false|订单处理状态: 未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close）|
|dateFrom|query|string|false|none|
|dateTo|query|string|false|none|
|pageNum|query|string|false|当前页码|
|pageSize|query|string|false|每页数量|

> 返回示例

> OK

```json
[
  {
    "orderId": "10000001",
    "billName": "杨林",
    "processStatus": "P"
  },
  {
    "orderId": "98",
    "billName": "住开规系",
    "processStatus": "mollit anim incididunt fugiat"
  },
  {
    "orderId": "81",
    "billName": "战众影叫写",
    "processStatus": "sed proident amet cupidatat est"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» pageNum|integer|true|none|none|
|» pageSize|integer|true|none|none|
|» totalSize|integer|true|none|none|
|» totalPages|integer|true|none|none|
|» content|[object]|true|none|none|
|»» billName|string|true|none|none|
|»» orderId|integer|true|none|none|
|»» processStatus|string|true|none|none|

## GET 根据中缀获取可匹配订单Id列表

GET /orders/Ids

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|infix|query|string|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 根据中缀获取可匹配收件人列表

GET /orders/billNames

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|infix|query|string|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 根据订单Id查询任务单列表

GET /orders/{orderId}/taskforms

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|none|

> 返回示例

> 成功

```json
[
  {
    "taskId": 45,
    "orderId": 75,
    "subSiteId": "69",
    "courier": "aliqua",
    "status": "#",
    "deliveryDateTime": "1983-06-03 08:57:03",
    "shipName": "芳",
    "shipTel": "18152878687",
    "shipPro": "重庆",
    "shipCity": "桂林市",
    "shipDis": "长洲区",
    "shipAddr": "华北",
    "billName": "平",
    "billTel": "18140216783",
    "billCity": "三亚市",
    "billPro": "上海",
    "billDis": "治多县",
    "billAddr": "东北",
    "totalPrice": -7644671113060768,
    "note": "exercitation pariatur",
    "orderItems": [
      {
        "orderId": 46,
        "itemNum": 650590,
        "status": "exercitation consectetur quis nisi dolore",
        "total": 27,
        "item": {
          "itemId": "450000201307035852",
          "categoryId": "410000200801299472",
          "name": "wowg",
          "descn": "除干入流这说联间万识对人到数种作回。",
          "unitCost": 65553381.06607393,
          "listPrice": 5059590.89829814,
          "imgUrl": "http://dummyimage.com/180x150",
          "status": "cupidatat adipisicing Excepteur"
        },
        "taskId": null
      },
      {
        "orderId": 68,
        "itemNum": 993719,
        "status": "sunt velit laborum",
        "total": 57,
        "item": {
          "itemId": "120000199412258998",
          "categoryId": "460000202005070456",
          "name": "rjhmg",
          "descn": "部图头的月听科书重好根三化内听千干亲。",
          "unitCost": 90021496.94778481,
          "listPrice": 51334387.68736999,
          "imgUrl": "http://dummyimage.com/120x60",
          "status": "ut in dolor culpa"
        },
        "taskId": null
      }
    ]
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*任务单列表*

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[TaskForm](#schemataskform)]|false|none|任务单列表|
|» taskId|integer|true|none|任务单编号|
|» orderId|integer|true|none|订单编号|
|» subSiteId|string|true|none|处理该任务单的配送站编号|
|» courier|string|true|none|配送员|
|» status|string|true|none|任务单的状态，缺货等待调货（W），未发出（U），运输中（O），未配送（N），已签收（Y）|
|» deliveryDateTime|string|true|none|发货时间|
|» shipName|string|true|none|寄件人姓名|
|» shipTel|string|true|none|寄件人电话|
|» shipPro|string|true|none|寄件人所在省（直辖市）|
|» shipCity|string|true|none|寄件人所在市|
|» shipDis|string|true|none|寄件人所在区|
|» shipAddr|string|true|none|寄件人详细街道地址|
|» billName|string|true|none|收件人姓名|
|» billTel|string|true|none|收件人电话|
|» billPro|string|true|none|收件人所在省|
|» billCity|string|true|none|收件人所在市|
|» billDis|string|true|none|收件人所在区|
|» billAddr|string|true|none|收件人详细街道地址|
|» totalPrice|number|true|none|该任务单商品总金额|
|» note|string|true|none|备注|
|» orderItems|[[OrderItem](#schemaorderitem)]|true|none|任务单中的商品项|
|»» orderId|integer|true|none|订单id|
|»» taskId|any|false|none|任务单id|
|»» itemNum|integer|true|none|商品数量|
|»» status|string|true|none|处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)|
|»» total|number|true|none|单类商品付款总额|
|»» item|[Item](#schemaitem)|true|none|商品项|
|»»» itemId|string|true|none|商品id|
|»»» categoryId|string|false|none|大类id|
|»»» name|string|false|none|商品名称|
|»»» descn|string|false|none|文字描述|
|»»» unitCost|number(double)|false|none|原价|
|»»» listPrice|number(double)|false|none|售价|
|»»» imgUrl|string|false|none|图片url|
|»»» status|string|false|none|商品状态：上架（P）；下架（F）|

## GET 根据订单Id查询退货单列表

GET /orders/{orderId}/returnforms

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|none|

> 返回示例

> 成功

```json
[
  {
    "formId": 59,
    "orderId": 45,
    "isChange": 52,
    "item": {
      "itemId": "140000199402236625",
      "categoryId": "130000198301141815",
      "name": "chussnrxt",
      "descn": "才价动型它展次证光济打第看特。",
      "unitCost": 27291266.908242106,
      "listPrice": 24412621.64854375,
      "imgUrl": "http://dummyimage.com/88x31",
      "status": "eiusmod sed"
    },
    "itemNum": 84,
    "returnMoney": 7048552084947689,
    "applyTime": "1971-04-06 16:31:27",
    "processTime": "2004-10-12 08:33:18",
    "processStatus": "cupidatat",
    "reason": "aliqua"
  },
  {
    "formId": 13,
    "orderId": 92,
    "isChange": 2,
    "item": {
      "itemId": "45000020020913422X",
      "categoryId": "340000197502191215",
      "name": "mewmgga",
      "descn": "复将作成点下行值业指五单场离增类。",
      "unitCost": 82064639.67181763,
      "listPrice": 61046718.45645124,
      "imgUrl": "http://dummyimage.com/160x600",
      "status": "esse aute fugiat"
    },
    "itemNum": 1,
    "returnMoney": 7320943473300512,
    "applyTime": "2001-02-12 15:55:11",
    "processTime": "2007-09-05 08:38:26",
    "processStatus": "et aliqua incididunt",
    "reason": "do reprehenderit eiusmod sunt"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*退货（换货）信息列表*

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[ReturnForm](#schemareturnform)]|false|none|退货（换货）信息列表|
|» formId|integer|true|none|退换货单的编号|
|» orderId|integer|true|none|对应的订单编号|
|» isChange|integer|true|none|1表示换货 ，0表示退货|
|» item|[Item](#schemaitem)|true|none|商品项|
|»» itemId|string|true|none|商品id|
|»» categoryId|string|false|none|大类id|
|»» name|string|false|none|商品名称|
|»» descn|string|false|none|文字描述|
|»» unitCost|number(double)|false|none|原价|
|»» listPrice|number(double)|false|none|售价|
|»» imgUrl|string|false|none|图片url|
|»» status|string|false|none|商品状态：上架（P）；下架（F）|
|» itemNum|integer|true|none|退换货商品的数量|
|» returnMoney|number|true|none|退款金额（换货无需退款）|
|» applyTime|string|true|none|申请时间|
|» processTime|string|true|none|处理时间|
|» processStatus|string|true|none|处理状态，未处理（N）已处理（P）进行中（I ing）退/换货成功（Y）|
|» reason|string|true|none|申请原因|

## GET 查询订单详细信息

GET /orders/{orderId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|none|

> 返回示例

> 成功

```json
{
  "order": {
    "orderId": 49,
    "customerId": "10000000",
    "payStatus": "pariatur veniam non enim",
    "processStatus": "ut est",
    "billPro": "江西省",
    "billCity": "三亚市",
    "billDistrict": "-",
    "billAddr": "华北",
    "totalPrice": 5,
    "billName": "龙娜",
    "createDateTime": "2013-02-17 22:14:47",
    "payDateTime": "1972-06-12 10:20:39",
    "shippingCost": 65,
    "payMethod": "sint qui cupidatat dolor",
    "note": "书示意接所被花发南候精品圆共次。"
  },
  "orderItemList": [
    {
      "orderId": 9,
      "itemNum": 688873,
      "status": "proident non",
      "total": 82,
      "item": {
        "itemId": "420000197710161862",
        "categoryId": "120000199302137618",
        "name": "bghgre",
        "descn": "商时给要际只里五有该因世西经会单流最。",
        "unitCost": 16515339.862707546,
        "listPrice": 82423086.78614254,
        "imgUrl": "http://dummyimage.com/180x150",
        "status": "enim dolore ex"
      },
      "taskId": null
    },
    {
      "orderId": 70,
      "itemNum": 467136,
      "status": "anim nulla quis voluptate sunt",
      "total": 60,
      "item": {
        "itemId": "350000199107316146",
        "categoryId": "530000200408165723",
        "name": "snsbingmg",
        "descn": "更王听状于容般们般务处革族日影红以。",
        "unitCost": 97779894.32842384,
        "listPrice": 78370764.79524389,
        "imgUrl": "http://dummyimage.com/88x31",
        "status": "quis"
      },
      "taskId": null
    },
    {
      "orderId": 73,
      "itemNum": 24363,
      "status": "id ullamco cillum",
      "total": 64,
      "item": {
        "itemId": "210000200706199003",
        "categoryId": "540000199903011007",
        "name": "ihejg",
        "descn": "何委华育制保度群人下两领响热我构照。",
        "unitCost": 28890209.51457608,
        "listPrice": 39411567.34831933,
        "imgUrl": "http://dummyimage.com/250x250",
        "status": "sunt in eu ut"
      },
      "taskId": null
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» order|[Order](#schemaorder)|true|none|订单|
|»» orderId|integer|true|none|订单Id|
|»» customerId|string|true|none|买家id|
|»» payStatus|string|true|none|付款状态: 已付款（P）未付款（W）|
|»» processStatus|string|true|none|订单处理状态:未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close）|
|»» createDateTime|string|false|none|订单创建时间|
|»» payDateTime|string|false|none|订单付款时间|
|»» billName|string|true|none|收件人姓名|
|»» billPro|string|true|none|收件所在省|
|»» billCity|string|true|none|收件所在市|
|»» billDistrict|string|true|none|收件所在区|
|»» billAddr|string|true|none|收件详细地址|
|»» totalPrice|number|true|none|总付款|
|»» shippingCost|number|false|none|运费|
|»» payMethod|string|false|none|支付方式|
|»» note|string|false|none|备注|
|» orderItemList|[[OrderItem](#schemaorderitem)]|true|none|[订单商品项]|
|»» orderId|integer|true|none|订单id|
|»» taskId|any|false|none|任务单id|
|»» itemNum|integer|true|none|商品数量|
|»» status|string|true|none|处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)|
|»» total|number|true|none|单类商品付款总额|
|»» item|[Item](#schemaitem)|true|none|商品项|
|»»» itemId|string|true|none|商品id|
|»»» categoryId|string|false|none|大类id|
|»»» name|string|false|none|商品名称|
|»»» descn|string|false|none|文字描述|
|»»» unitCost|number(double)|false|none|原价|
|»»» listPrice|number(double)|false|none|售价|
|»»» imgUrl|string|false|none|图片url|
|»»» status|string|false|none|商品状态：上架（P）；下架（F）|

# 订单管理/订单审核

## GET 订单消息对象(不是接口)

GET /orders/messages

> 成功示例中包含订单消息的数据结构, 前端渲染展示列表项

> 返回示例

> 成功

```json
{
  "orderId": 58,
  "billName": "高静",
  "createDateTime": "2016-12-30 06:38:10",
  "totalPrice": 95
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[OrderBriefResp](#schemaorderbriefresp)|

## GET 查询订单审核单详细信息

GET /orders/{orderId}/check

> 通过订单id查看订单审核详细信息，查看时可更改相关数据

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|none|

> 返回示例

> 成功

```json
{
  "order": {
    "orderId": "440000197703081614",
    "customerId": "230000199310267813",
    "payStatus": "P",
    "processStatus": "N",
    "billPro": "浙江省",
    "billCity": "吕梁市",
    "billDistrict": "洛隆县",
    "billAddr": "东北",
    "totalPrice": 60,
    "billName": "孟勇",
    "createDateTime": "1984-08-18 13:57:24",
    "payDateTime": "2016-04-23 14:02:28",
    "shippingCost": 65,
    "payMethod": "支付宝",
    "note": "请及时发货"
  },
  "orderItemList": [
    {
      "orderId": 61,
      "quantity": 310964,
      "status": "aliquip quis adipisicing voluptate ullamco",
      "totalPrice": 33,
      "item": {
        "itemId": "210000197612064656",
        "name": "iezcxridb",
        "unitCost": 3618134.818448038,
        "listPrice": 53099257.22776379,
        "status": "veniam qui",
        "categoryId": "210000198612156897",
        "descn": "题验斯各人消带保土音观业收需精石。",
        "imgUrl": "http://dummyimage.com/125x125"
      },
      "taskId": 79
    },
    {
      "orderId": 64,
      "quantity": 765965,
      "status": "nisi",
      "totalPrice": 66,
      "item": {
        "itemId": "440000200410315047",
        "name": "bbzjalm",
        "unitCost": 9834862.502083719,
        "listPrice": 16734342.036927562,
        "status": "irure dolor",
        "categoryId": "500000200509185154",
        "descn": "记目近元王场要展出单式化向。",
        "imgUrl": "http://dummyimage.com/120x90"
      },
      "taskId": 57
    },
    {
      "orderId": 60,
      "quantity": 635008,
      "status": "esse do proident cupidatat nisi",
      "totalPrice": 33,
      "item": {
        "itemId": "36000020070311621X",
        "name": "puhrbmlo",
        "unitCost": 22612450.691776197,
        "listPrice": 57018712.23601958,
        "status": "tempor",
        "categoryId": "210000197407245300",
        "descn": "面党革王圆将南数保热候术增思存着观。",
        "imgUrl": "http://dummyimage.com/88x31"
      },
      "taskId": 81
    }
  ],
  "mainsite": {
    "mainsiteId": "17",
    "province": "广西壮族自治区",
    "city": "巴彦淖尔市",
    "district": "西南"
  },
  "subsite": {
    "subsiteId": "6",
    "mainsiteId": "9",
    "district": "华南"
  },
  "msg": "订单主站从西南发货, 配送站为华南站, 预计总配送时长3天"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[OrderDetailResp](#schemaorderdetailresp)|

## PATCH 修改订单信息

PATCH /orders/{orderId}/check

### 接口功能
- 修改主站信息
- 取消订单(修改订单处理状态, 默认进入时为N, 审核通过为P, 审核未通过订单取消为C)
- 修改运费

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|none|
|processStatus|query|string|true|订单处理状态: 未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close）|
|mainsiteId|query|string|false|修改主站信息|
|shippingCost|query|string|false|修改运费|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 仓储管理

## POST 新增主站

POST /mainsites

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 仓储管理/主站管理

## GET 获取所有主站id

GET /mainsites

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*主站id*

|名称|类型|必选|约束|说明|
|---|---|---|---|---|

# 仓储管理/主站管理/入库管理

## GET 查询入库单审核详细信息

GET /mainsites/{mainsiteId}/inventory/sitein/{recordId}

> 通过入库记录编号查看入库审核单详细信息，查看时可更改相关数据

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|主站编号|
|recordId|path|string|true|入库记录编号|

> 返回示例

> 成功

```json
{
  "recordId": 10000000,
  "timeStamp": "2020-07-15 00:12:24",
  "warehouseId": "WH-026",
  "warehouseOptionalList": [
    "WH-026",
    "WH-022"
  ],
  "item": {
    "categoryId": "CLOTHES",
    "itemId": "C-002",
    "name": "T-shirt",
    "descn": "short purple T-shirt",
    "unitCost": 199.9,
    "listPrice": 99.9,
    "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/C-002.jpg",
    "status": "P"
  },
  "itemNum": 100,
  "type": 1,
  "typeDesc": "补货",
  "formId": 10000000,
  "itemSrc": "依目了然",
  "approvalStatus": "W",
  "approver": "Auto"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[SiteIOCheckinResp](#schemasiteiocheckinresp)|

## PATCH 修改入库单信息

PATCH /mainsites/{mainsiteId}/inventory/sitein/{recordId}

> 入库单部分可修改，可供修改部分：入库库房编号、入库审核状态

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|recordId|path|string|true|none|
|warehouseId|query|string|false|入库库房编号|
|approvalStatus|query|string|false|入库审核状态，待审核（W）；已审核（Y）；已失效（F），失效即管理员取消入库操作|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 获取入库消息

GET /mainsites/{mainsiteId}/inventory/inmessages

~~前端通过本接口向消息队列发送请求，获取待审核入库信息列表~~
成功示例中包含入库消息的数据结构, 前端进行渲染展示列表项

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|recordId|query|string|true|出入库单编号|

> 返回示例

> 成功

```json
{
  "type": 1,
  "typeDesc": "补货",
  "formId": 10000000,
  "itemId": "C-002",
  "itemNum": 100,
  "recordId": 10000000
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[ItemCheckinResp](#schemaitemcheckinresp)|

## POST 新增入库请求

POST /mainsites/{mainsiteId}/inventory/inquery

> 给系统外部提供的接口
> 模拟入库员完成货物扫码后，外部系统将扫码后的货物信息发送给后台

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "type": {
      "type": "integer",
      "description": "1表示补货，2表示调货，3表示退货，4表示换货"
    },
    "formId": {
      "type": "integer",
      "description": "相关表单编号"
    },
    "itemId": {
      "type": "string",
      "description": "商品id"
    },
    "itemNum": {
      "type": "integer",
      "description": "商品数量"
    }
  }
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|body|body|object|false|none|
|» type|body|integer|false|1表示补货，2表示调货，3表示退货，4表示换货|
|» formId|body|integer|false|相关表单编号|
|» itemId|body|string|false|商品id|
|» itemNum|body|integer|false|商品数量|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 仓储管理/主站管理/出库管理

## PATCH 修改出库单信息 

PATCH /mainsites/{mainsiteId}/inventory/siteout/{recordId}

> 出库单部分可修改，可供修改部分：库房编号、审核状态

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|recordId|path|string|true|none|
|warehouseId|query|string|false|库房编号|
|approvalStatus|query|string|false|审核状态，待审核（W）；已审核（Y）；已失效（F），失效即管理员审核时取消出库|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查询出库审核单详细信息

GET /mainsites/{mainsiteId}/inventory/siteout/{recordId}

> 通过入库记录编号查看出库审核单详细信息，查看时可更改相关数据

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|主站编号|
|recordId|path|string|true|出库记录编号|

> 返回示例

> 成功

```json
{
  "recordId": 10000002,
  "timeStamp": "2020-07-15 17:06:17",
  "warehouseId": "WH-022",
  "item": {
    "categoryId": "CLOTHES",
    "itemId": "C-002",
    "name": "T-shirt",
    "descn": "short purple T-shirt",
    "unitCost": 199.9,
    "listPrice": 99.9,
    "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/C-002.jpg",
    "status": "P"
  },
  "itemNum": 100,
  "type": 1,
  "typeDesc": "主站退货",
  "formId": 10000002,
  "itemDest": "依目了然",
  "approvalStatus": "W",
  "approver": "Auto",
  "warehouseOptionalList": [
    "WH-022",
    "WH-026"
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[SiteIOCheckoutResp](#schemasiteiocheckoutresp)|

## GET 出库消息对象(不是接口)

GET /mainsites/{mainsiteId}/inventory/outmessages

~~前端通过本接口向消息队列发送请求，获取待审核出库信息列表~~
成功示例中包含出库消息的数据结构, 前端进行渲染展示列表项

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|recordId|query|string|true|出库单编号|

> 返回示例

> 成功

```json
{
  "type": 1,
  "typeDesc": "主站退货",
  "formId": 10000002,
  "itemId": "C-002",
  "itemNum": 100,
  "recordId": 10000002
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[ItemCheckoutResp](#schemaitemcheckoutresp)|

# 仓储管理/主站管理/库内管理

## GET 查询货物列表

GET /mainsites/{mainsiteId}/items

获取仓库内商品列表, 筛选条件:
- 库房号
- 大类号列表
- 关键字

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|warehouseIdList|query|string|false|库房编号，可为空|
|categoryIdList|query|string|false|大类编号，可为空|
|keyword|query|string|false|关键字，可为空|
|pageNum|query|string|false|当前页码, 默认为1, 可为空|
|pageSize|query|string|false|每页数量, 默认为6, 可为空|

> 返回示例

> 成功

```json
{
  "pageNum": 1,
  "pageSize": 6,
  "totalSize": 20,
  "totalPages": 4,
  "content": [
    {
      "item": {
        "categoryId": "SHOES",
        "itemId": "S-003",
        "name": "leather shoes",
        "descn": "business dress leather shoes ",
        "unitCost": 324,
        "listPrice": 294,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/S-003.jpg",
        "status": "P"
      },
      "inventory": 800,
      "warehouseId": "WH-006"
    },
    {
      "item": {
        "categoryId": "SHOES",
        "itemId": "S-004",
        "name": "running shoes",
        "descn": "lace-up white running shoes",
        "unitCost": 358,
        "listPrice": 328,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/S-004.jpg",
        "status": "P"
      },
      "inventory": 600,
      "warehouseId": "WH-006"
    },
    {
      "item": {
        "categoryId": "SHOES",
        "itemId": "S-005",
        "name": "Martin boots",
        "descn": "black meduim Martin boots",
        "unitCost": 756,
        "listPrice": 646,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/S-005.jpg",
        "status": "P"
      },
      "inventory": 200,
      "warehouseId": "WH-006"
    },
    {
      "item": {
        "categoryId": "APPLIANCES",
        "itemId": "A-001",
        "name": "LED TV",
        "descn": "55 inch MI LED TV",
        "unitCost": 3399.9,
        "listPrice": 3199.9,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-001.jpg",
        "status": "P"
      },
      "inventory": 80,
      "warehouseId": "WH-007"
    },
    {
      "item": {
        "categoryId": "APPLIANCES",
        "itemId": "A-002",
        "name": "washing machine",
        "descn": "mini AUX washing machine",
        "unitCost": 1999.35,
        "listPrice": 1799.35,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-002.jpg",
        "status": "P"
      },
      "inventory": 145,
      "warehouseId": "WH-007"
    },
    {
      "item": {
        "categoryId": "APPLIANCES",
        "itemId": "A-005",
        "name": "LED TV",
        "descn": "55 inch Hisense LED TV",
        "unitCost": 3799.89,
        "listPrice": 3399.89,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-005.jpg",
        "status": "P"
      },
      "inventory": 76,
      "warehouseId": "WH-007"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» pageNum|integer|true|none|页号|
|» pageSize|integer|true|none|页大小|
|» totalSize|integer|true|none|总商品数|
|» totalPages|integer|true|none|总页数|
|» content|[[ItemInventoryResp](#schemaiteminventoryresp)]|true|none|查询得到的商品列表|
|»» item|[Item](#schemaitem)|true|none|商品项|
|»»» itemId|string|true|none|商品id|
|»»» categoryId|string|false|none|大类id|
|»»» name|string|false|none|商品名称|
|»»» descn|string|false|none|文字描述|
|»»» unitCost|number(double)|false|none|原价|
|»»» listPrice|number(double)|false|none|售价|
|»»» imgUrl|string|false|none|图片url|
|»»» status|string|false|none|商品状态：上架（P）；下架（F）|
|»» inventory|integer|true|none|商品库存|
|»» warehouseId|string|true|none|商品所在库房|

## GET 查询库房列表

GET /mainsites/{mainsiteId}/warehouses

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|

> 返回示例

> 成功

```json
[
  {
    "siteId": "MAIN-001",
    "warehouseId": "WH-001",
    "categoryId": "APPLIANCES",
    "category": {
      "categoryId": "APPLIANCES",
      "name": "appliances",
      "descn": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/category/appliances.jpg"
    },
    "kindNumOfItem": 5,
    "totalSize": 561,
    "maxSize": 1000
  },
  {
    "siteId": "MAIN-001",
    "warehouseId": "WH-002",
    "categoryId": "CLOTHES",
    "category": {
      "categoryId": "CLOTHES",
      "name": "clothes",
      "descn": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/category/clothes.jpg"
    },
    "kindNumOfItem": 8,
    "totalSize": 6868,
    "maxSize": 20000
  },
  {
    "siteId": "MAIN-001",
    "warehouseId": "WH-003",
    "categoryId": "SHOES",
    "category": {
      "categoryId": "SHOES",
      "name": "shoes",
      "descn": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/category/shoes.jpg"
    },
    "kindNumOfItem": 3,
    "totalSize": 3600,
    "maxSize": 20000
  },
  {
    "siteId": "MAIN-001",
    "warehouseId": "WH-004",
    "categoryId": "DRINK",
    "category": {
      "categoryId": "DRINK",
      "name": "drink",
      "descn": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/category/drink.jpg"
    },
    "kindNumOfItem": 3,
    "totalSize": 10900,
    "maxSize": 40000
  },
  {
    "siteId": "MAIN-001",
    "warehouseId": "WH-005",
    "categoryId": "FRUITS",
    "category": {
      "categoryId": "FRUITS",
      "name": "fruits",
      "descn": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/category/fruits.jpg"
    },
    "kindNumOfItem": 3,
    "totalSize": 390,
    "maxSize": 700
  }
]
```

```json
[
  {
    "siteId": "CN-HN",
    "warehouseId": "WH-001",
    "category": {
      "categoryId": "YL",
      "name": "饮料",
      "descn": "需冷藏"
    },
    "kindNumOfItem": 30,
    "totalSize": 125,
    "maxSize": 1000
  },
  {
    "siteId": "CN-HN",
    "warehouseId": "WH-002",
    "category": {
      "categoryId": "NUT",
      "name": "坚果类",
      "descn": "袋装，需防潮"
    },
    "kindNumOfItem": 50,
    "totalSize": 1200,
    "maxSize": 1700
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*库房列表信息*

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[warehouseResp](#schemawarehouseresp)]|false|none|库房列表信息|
|» siteId|string|false|none|所属主站编号|
|» warehouseId|string|false|none|库房编号|
|» category|object|false|none|存储的种类|
|»» categoryId|string|false|none|none|
|»» name|string|false|none|none|
|»» descn|string|false|none|none|
|» kindNumOfItem|integer|false|none|存储的item的类别总数|
|» totalSize|integer|false|none|存储的Iten的总数量|
|» maxSize|integer|false|none|库房容量|

## PATCH 更新库房具体商品库存

PATCH /mainsites/{mainsiteId}/items/{itemId}

从源库房移动指定个数item(id已知), 至目标库房

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|itemId|path|string|true|none|
|sourceWarehouseId|query|string|true|源库房id|
|destWarehouseId|query|string|true|目标库房id|
|itemNum|query|string|true|移动的item个数|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查询库房详细信息

GET /mainsites/{mainsiteId}/warehouse/{warehouseId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|warehouseId|path|string|true|none|

> 返回示例

> 成功

```json
{
  "itemList": [
    {
      "item": {
        "itemId": "460000197801198481",
        "name": "vcgu",
        "unitCost": 41807478.715475254,
        "listPrice": 37100326.35807219,
        "status": "Duis ullamco irure",
        "categoryId": "520000198912252170",
        "descn": "元她从数效众出把时治阶问必设。",
        "imgUrl": "http://dummyimage.com/240x400"
      },
      "inventory": 64
    },
    {
      "item": {
        "itemId": "710000201704039236",
        "name": "tgrt",
        "unitCost": 23628441.259774346,
        "listPrice": 20108817.269916233,
        "status": "consectetur sed mollit commodo consequat",
        "categoryId": "370000201305125483",
        "descn": "精和所增路己展须如府型反电再。",
        "imgUrl": "http://dummyimage.com/336x280"
      },
      "inventory": 59
    }
  ],
  "warehouseId": "76",
  "mainSiteId": "92",
  "mainSiteName": "口思打委道",
  "category": {
    "categoryId": "710000199402276981",
    "descn": "美",
    "name": "qbyb"
  }
}
```

```json
{
  "warehouseId": "WH-003",
  "mainSiteId": "WH-003",
  "category": {
    "categoryId": "SHOES",
    "name": "shoes",
    "descn": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/category/shoes.jpg"
  },
  "itemList": [
    {
      "item": {
        "categoryId": "SHOES",
        "itemId": "S-001",
        "name": "plimsolls",
        "descn": "low-top canvas shoes",
        "unitCost": 499,
        "listPrice": 439,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/S-001.jpg",
        "status": "P"
      },
      "inventory": 600
    },
    {
      "item": {
        "categoryId": "SHOES",
        "itemId": "S-002",
        "name": "high-heeled shoes",
        "descn": "beige leather stiletto heels",
        "unitCost": 289,
        "listPrice": 239,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/S-002.jpg",
        "status": "P"
      },
      "inventory": 1000
    },
    {
      "item": {
        "categoryId": "SHOES",
        "itemId": "S-003",
        "name": "leather shoes",
        "descn": "business dress leather shoes ",
        "unitCost": 324,
        "listPrice": 294,
        "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/S-003.jpg",
        "status": "P"
      },
      "inventory": 2000
    }
  ],
  "mainsiteName": "WH-003"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[warehouseDetailResp](#schemawarehousedetailresp)|

# 仓储管理/整体商品信息

## GET 查询商品详细信息

GET /goods/items/{itemId}

包含内容:
- 该商品的信息
- 该商品在各主站分布状态

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|itemId|path|string|true|none|

> 返回示例

> 成功

```json
{
  "item": {
    "itemId": "310000201504285550",
    "categoryId": "330000199204040555",
    "name": "wbappwxbes",
    "descn": "无实军家条活年是气场清九里又。",
    "unitCost": 68977032.58929197,
    "listPrice": 49541599.28394714,
    "imgUrl": "http://dummyimage.com/250x250",
    "status": "magna culpa non dolor laborum"
  },
  "totalInventory": 157,
  "mainsiteInventoryList": [
    {
      "mainsiteId": "48",
      "itemInventory": 29
    },
    {
      "mainsiteId": "44",
      "itemInventory": 80
    },
    {
      "mainsiteId": "77",
      "itemInventory": 37
    },
    {
      "mainsiteId": "95",
      "itemInventory": 11
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[ItemInventoryDetailResp](#schemaiteminventorydetailresp)|

## PUT 更新商品信息

PUT /goods/items/{itemId}

修改商品

> Body 请求参数

```yaml
type: object
properties:
  imgFile:
    type: string
    description: 如果不上传图片默认不修改图片
    format: binary
  name:
    type: string
  categoryId:
    type: string
  descn:
    type: string
  unitCost:
    type: string
  listPrice:
    type: string
required:
  - name
  - categoryId
  - descn
  - unitCost
  - listPrice

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|itemId|path|string|true|none|
|body|body|object|false|none|
|» imgFile|body|string(binary)|false|如果不上传图片默认不修改图片|
|» name|body|string|true|none|
|» categoryId|body|string|true|none|
|» descn|body|string|true|none|
|» unitCost|body|string|true|none|
|» listPrice|body|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## POST 新增商品

POST /goods/items

添加商品

> Body 请求参数

```yaml
type: object
properties:
  imgFile:
    type: string
    format: binary
  name:
    type: string
  categoryId:
    type: string
  descn:
    type: string
  unitCost:
    type: string
  listPrice:
    type: string
required:
  - imgFile
  - name
  - categoryId
  - descn
  - unitCost
  - listPrice

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|
|» imgFile|body|string(binary)|true|none|
|» name|body|string|true|none|
|» categoryId|body|string|true|none|
|» descn|body|string|true|none|
|» unitCost|body|string|true|none|
|» listPrice|body|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## GET 查询商品列表

GET /goods/items

> 分页查看商品，默认不进行筛选，pageSize=6，pageNum=1
筛选条件：
- categoryId
- 关键字

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|pageNum|query|string|false|默认为1|
|pageSize|query|string|false|默认为6|
|categoryIdList|query|string|false|大类编号列表，可以同时查询多个大类的商品|
|keyword|query|string|false|关键字|

> 返回示例

> 成功

```json
{
  "pageNum": 1,
  "pageSize": 6,
  "totalSize": 28,
  "totalPages": 5,
  "content": [
    {
      "categoryId": "APPLIANCES",
      "itemId": "A-001",
      "name": "LED TV",
      "descn": "55 inch MI LED TV",
      "unitCost": 3399.9,
      "listPrice": 3199.9,
      "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-001.jpg",
      "status": "P"
    },
    {
      "categoryId": "APPLIANCES",
      "itemId": "A-002",
      "name": "washing machine",
      "descn": "mini AUX washing machine",
      "unitCost": 1999.35,
      "listPrice": 1799.35,
      "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-002.jpg",
      "status": "P"
    },
    {
      "categoryId": "APPLIANCES",
      "itemId": "A-003",
      "name": "refrigerator",
      "descn": "146L of double door household refrigerator",
      "unitCost": 1549.88,
      "listPrice": 1299.88,
      "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-003.jpg",
      "status": "P"
    },
    {
      "categoryId": "APPLIANCES",
      "itemId": "A-004",
      "name": "air conditioner",
      "descn": "Haier 1.5 wall mounted air conditioner",
      "unitCost": 2339.49,
      "listPrice": 2099.49,
      "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-004.jpg",
      "status": "P"
    },
    {
      "categoryId": "APPLIANCES",
      "itemId": "A-005",
      "name": "LED TV",
      "descn": "55 inch Hisense LED TV",
      "unitCost": 3799.89,
      "listPrice": 3399.89,
      "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/A-005.jpg",
      "status": "P"
    },
    {
      "categoryId": "CLOTHES",
      "itemId": "C-001",
      "name": "T-shirt",
      "descn": "short white T-shirt",
      "unitCost": 199.9,
      "listPrice": 99.9,
      "imgUrl": "https://i-petstore.oss-cn-shenzhen.aliyuncs.com/i-logistics-system/image/item/C-001.jpg",
      "status": "P"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» pageNum|integer|true|none|none|
|» pageSize|integer|true|none|none|
|» totalSize|integer|true|none|none|
|» totalPages|integer|true|none|none|
|» content|[[Item](#schemaitem)]|true|none|[商品项]|
|»» Item|[Item](#schemaitem)|false|none|商品项|
|»»» itemId|string|true|none|商品id|
|»»» categoryId|string|false|none|大类id|
|»»» name|string|false|none|商品名称|
|»»» descn|string|false|none|文字描述|
|»»» unitCost|number(double)|false|none|原价|
|»»» listPrice|number(double)|false|none|售价|
|»»» imgUrl|string|false|none|图片url|
|»»» status|string|false|none|商品状态：上架（P）；下架（F）|

## GET 获取大类+商品目录

GET /goods/catalog

获取二级目录,包含id+名字+价格

> 返回示例

> 成功

```json
[
  {
    "itemList": [
      {
        "itemId": "52",
        "name": "必国府设准",
        "listPrice": "8"
      },
      {
        "itemId": "38",
        "name": "也革门平",
        "listPrice": "97"
      },
      {
        "itemId": "68",
        "name": "家算流物到近",
        "listPrice": "14"
      },
      {
        "itemId": "39",
        "name": "车车是从出越",
        "listPrice": "50"
      },
      {
        "itemId": "25",
        "name": "东为意子五",
        "listPrice": "83"
      }
    ],
    "categoryId": "66",
    "name": "打高权界铁"
  },
  {
    "itemList": [
      {
        "itemId": "57",
        "name": "务争问接",
        "listPrice": "58"
      },
      {
        "itemId": "86",
        "name": "热厂如七数已连",
        "listPrice": "69"
      },
      {
        "itemId": "9",
        "name": "育复计实制个",
        "listPrice": "30"
      },
      {
        "itemId": "49",
        "name": "部层分",
        "listPrice": "42"
      },
      {
        "itemId": "31",
        "name": "金正老情选",
        "listPrice": "52"
      }
    ],
    "categoryId": "96",
    "name": "过断低理动"
  },
  {
    "itemList": [
      {
        "itemId": "91",
        "name": "保通王半且适又",
        "listPrice": "91"
      },
      {
        "itemId": "6",
        "name": "接圆多及",
        "listPrice": "92"
      },
      {
        "itemId": "6",
        "name": "只快平与",
        "listPrice": "34"
      },
      {
        "itemId": "69",
        "name": "民教接技干达",
        "listPrice": "95"
      }
    ],
    "categoryId": "60",
    "name": "取做期民关转"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» categoryId|string|true|none|none|
|» categoryName|string|true|none|none|
|» itemList|[object]|true|none|none|
|»» itemId|string|true|none|none|
|»» itemName|string|true|none|none|
|»» listPrice|number|true|none|none|

## GET 查询大类列表

GET /goods/categories

> **用于商品筛选时提供参数**

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|categoryId|query|string|true|none|

> 返回示例

> OK

```json
[
  {
    "categoryId": 39,
    "categoryName": "问位准进马整办",
    "description": "Lorem"
  },
  {
    "categoryId": 9,
    "categoryName": "个上之热",
    "description": "consectetur"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» categoryId|integer|false|none|none|
|» categoryName|string|false|none|none|
|» description|string|false|none|none|

# 配送管理

## GET 调货信息

GET /transfer/{mainSiteId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainSiteId|path|string|true|主站id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» adjustId|integer|true|none|调货单id|
|» itemId|string|true|none|调货商品id|
|» itemNum|integer|true|none|调货数量|
|» from|string|true|none|调货发货地id|
|» to|string|true|none|调货收货地id（缺货地）|
|» status|string|true|none|调货状态|
|» fromPoint|object|true|none|调货发货地位置|
|»» lat|number|true|none|纬度|
|»» lng|number|true|none|经度|
|» toPoint|object|true|none|调货收货地位置|
|»» lat|number|true|none|纬度|
|»» lng|number|true|none|经度|

# 配送管理/任务单管理

## GET 获取路线

GET /taskforms/{taskFormId}/route

### [前端效果](https://lbs.amap.com/api/amap-ui/demos/amap-ui-pathsimplifier/index)

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|taskFormId|path|string|true|任务单编号|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» first|[RouteResp](#schemarouteresp)|true|none|路径|
|»» distance|number|true|none|方案距离，单位：米|
|»» duration|number|true|none|线路耗时，单位：秒|
|»» steps|[object]|true|none|途径点|
|»»» leg_index|integer|true|none|途径点序号|
|»»» start_location|object|true|none|分段起点|
|»»»» lng|number|true|none|经度|
|»»»» lat|number|true|none|纬度|
|»»» end_location|object|true|none|分段终点|
|»»»» lng|number|true|none|经度|
|»»»» lat|number|true|none|纬度|
|» second|[RouteResp](#schemarouteresp)|true|none|路径|
|» status|string|true|none|状态（W,U,O,N,Y）|
|» mainSiteName|string|true|none|主站名称|
|» subSiteName|string|true|none|配送站名称|

## GET 查询任务单列表

GET /subsites/{subSiteId}/taskforms

## status和关键词二选一，若都不提供则返回全部

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subSiteId|path|string|true|none|
|status|query|string|false|Y：以处理，N：未处理，W/U/O：未到货|
|q|query|string|false|关键词|
|pageNum|query|string|true|分页页码|
|pageSize|query|string|true|分页大小|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» pageNum|integer|true|none|当前页码|
|» pageSize|integer|true|none|每页数量|
|» totalSize|integer|true|none|记录总数|
|» totalPages|integer|true|none|页码总数|
|» content|[[TaskFormResp](#schemataskformresp)]|true|none|数据列表|
|»» taskFormId|integer|true|none|任务单id|
|»» subSiteId|string|true|none|配送站id|
|»» status|string|true|none|状态|
|»» shipTime|string|true|none|发货时间|
|»» receiverName|string|true|none|收件人姓名|
|»» receiverTel|string|true|none|收件人电话|
|»» receiverAddress|string|true|none|收货地址|
|»» orderItems|[[OrderItem](#schemaorderitem)]|true|none|[订单商品项]|
|»»» orderId|integer|true|none|订单id|
|»»» taskId|any|false|none|任务单id|
|»»» itemNum|integer|true|none|商品数量|
|»»» status|string|true|none|处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)|
|»»» total|number|true|none|单类商品付款总额|
|»»» item|[Item](#schemaitem)|true|none|商品项|
|»»»» itemId|string|true|none|商品id|
|»»»» categoryId|string|false|none|大类id|
|»»»» name|string|false|none|商品名称|
|»»»» descn|string|false|none|文字描述|
|»»»» unitCost|number(double)|false|none|原价|
|»»»» listPrice|number(double)|false|none|售价|
|»»»» imgUrl|string|false|none|图片url|
|»»»» status|string|false|none|商品状态：上架（P）；下架（F）|

## GET 获取配送站列表

GET /subsites

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*subSiteId*

|名称|类型|必选|约束|说明|
|---|---|---|---|---|

## GET 任务单状态统计

GET /taskforms/status

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» status|string|true|none|状态描述|
|» number|string|true|none|数量|

## GET 获取/搜索任务单

GET /taskforms

## 后台管理
关键词，默认全部任务单

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|q|query|string|false|关键词|
|pageNum|query|string|true|分页页码|
|pageSize|query|string|true|分页大小|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» pageNum|integer|true|none|当前页码|
|» pageSize|integer|true|none|每页数量|
|» totalSize|integer|true|none|记录总数|
|» totalPages|integer|true|none|页码总数|
|» content|[[TaskFormResp](#schemataskformresp)]|true|none|数据列表|
|»» taskFormId|integer|true|none|任务单id|
|»» subSiteId|string|true|none|配送站id|
|»» status|string|true|none|状态|
|»» shipTime|string|true|none|发货时间|
|»» receiverName|string|true|none|收件人姓名|
|»» receiverTel|string|true|none|收件人电话|
|»» receiverAddress|string|true|none|收货地址|
|»» orderItems|[[OrderItem](#schemaorderitem)]|true|none|[订单商品项]|
|»»» orderId|integer|true|none|订单id|
|»»» taskId|any|false|none|任务单id|
|»»» itemNum|integer|true|none|商品数量|
|»»» status|string|true|none|处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)|
|»»» total|number|true|none|单类商品付款总额|
|»»» item|[Item](#schemaitem)|true|none|商品项|
|»»»» itemId|string|true|none|商品id|
|»»»» categoryId|string|false|none|大类id|
|»»»» name|string|false|none|商品名称|
|»»»» descn|string|false|none|文字描述|
|»»»» unitCost|number(double)|false|none|原价|
|»»»» listPrice|number(double)|false|none|售价|
|»»»» imgUrl|string|false|none|图片url|
|»»»» status|string|false|none|商品状态：上架（P）；下架（F）|

## GET 通过id获取任务单

GET /taskforms/{taskFormId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|taskFormId|path|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[TaskFormResp](#schemataskformresp)|

# 地图数据

## GET [后端用] 距离测试

GET /test/distance

<http://lbsyun.baidu.com/index.php?title=webapi/route-matrix-api-v2>

> 返回示例

> 成功

```json
{
  "status": 18,
  "message": "enim officia non laboris tempor",
  "result": [
    {
      "distance": {
        "text": "occaecat",
        "value": 72
      },
      "duration": {
        "text": "dolore in",
        "value": 5
      }
    },
    {
      "distance": {
        "text": "ea cillum in esse reprehenderit",
        "value": 33
      },
      "duration": {
        "text": "eiusmod ipsum",
        "value": 97
      }
    },
    {
      "distance": {
        "text": "aliqua ut id veniam consequat",
        "value": 75
      },
      "duration": {
        "text": "aliqua dolore",
        "value": 7
      }
    },
    {
      "distance": {
        "text": "sint dolore reprehenderit officia irure",
        "value": 18
      },
      "duration": {
        "text": "est velit",
        "value": 68
      }
    },
    {
      "distance": {
        "text": "enim",
        "value": 93
      },
      "duration": {
        "text": "anim magna",
        "value": 53
      }
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» status|integer|true|none|none|
|» message|string|true|none|none|
|» result|[object]|true|none|none|
|»» distance|object|true|none|none|
|»»» text|string|true|none|none|
|»»» value|number|true|none|none|
|»» duration|object|true|none|none|
|»»» text|string|true|none|none|
|»»» value|number|true|none|none|

# 废弃接口

## POST 测试出库选项

POST /test/testSiteOut

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查询任务单详情

GET /orders/{orderId}/taskforms/{taskFormId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|none|
|taskFormId|path|string|true|任务单Id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查询物流详情

GET /taskforms/{taskFormId}/transport

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|taskFormId|path|string|true|任务单Id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 测试

GET /test/testLogic

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|query|string|false|none|
|itemId|query|string|false|none|
|logicInv|query|string|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 废弃接口/商品管理

## GET 根据多种条件检索顾客

GET /clientele/customers/{customerReq}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|customerReq|path|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[CustomerWithId](#schemacustomerwithid)]|false|none|[客户]|
|» Customer|[CustomerWithId](#schemacustomerwithid)|false|none|客户|
|»» customerId|integer(int64)|true|none|客户id|
|»» name|string|true|none|客户姓名|
|»» tel|string|true|none|客户邮箱|
|»» email|string|true|none|客户电话|
|»» addr|string|true|none|客户详细地址|
|»» district|string|true|none|客户所在行政区区|
|»» city|string|true|none|客户所在市|
|»» province|string|true|none|所在省|

# 废弃接口/商品管理/category

## DELETE delete Category

DELETE /goods/category

删除商品一级分类

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|categoryId|query|string|true|(Required) 一级分类id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## POST add Category

POST /goods/category

添加商品一级分类

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## PUT modify Category

PUT /goods/category

修改商品一级分类

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## GET get Sub Categories By Category Id (response Id or Object)

GET /goods/category/{categoryId}/subcategory

获得一级分类下的二级分类

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|categoryId|path|string|true|(Required) 一级分类id|

> 返回示例

> OK

```json
[
  {
    "subCategoryId": 1,
    "subCategoryName": "Rice",
    "categoryId": 1,
    "description": "大米"
  },
  {
    "subCategoryId": 1,
    "subCategoryName": "Rice",
    "categoryId": 1,
    "description": "大米"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» subCategoryId|integer|true|none|none|
|» subCategoryName|string|true|none|none|
|» categoryId|integer|true|none|none|
|» description|string|true|none|none|

# 废弃接口/商品管理/subcategory

## DELETE delete Sub Category

DELETE /goods/subcategory

删除商品二级分类

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subCategoryId|query|string|true|(Required) 二级分类id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## PUT modify Sub Category

PUT /goods/subcategory

修改商品二级分类

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## GET get Sub Category

GET /goods/subcategory

获取商品二级分类

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subCategoryId|query|string|true|(Required) 二级分类id|

> 返回示例

> OK

```json
{
  "subCategoryId": 1,
  "subCategoryName": "Rice",
  "categoryId": 1,
  "description": "大米"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» subCategoryId|integer|false|none|none|
|» subCategoryName|string|false|none|none|
|» categoryId|integer|false|none|none|
|» description|string|false|none|none|

## POST add Sub Category

POST /goods/subcategory

添加商品二级分类

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

# 废弃接口/商品管理/subcategory/{sub Category Id}

## GET get Category By Sub Category Id (response Id or Object)

GET /goods/subcategory/{subCategoryId}/category

获得二级分类对象的一级分类

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subCategoryId|path|string|true|(Required) 二级分类id|

> 返回示例

> OK

```json
{
  "categoryId": 1,
  "categoryName": "Food",
  "description": "吃"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» categoryId|integer|false|none|none|
|» categoryName|string|false|none|none|
|» description|string|false|none|none|

## GET get Items By Sub Category Id (response Id or Object)

GET /goods/subcategory/{subCategoryId}/item

获得二级分类下的商品

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subCategoryId|path|string|true|(Required) 二级分类id|

> 返回示例

> OK

```json
[
  {
    "itemId": 1,
    "ItemName": "可口可乐 330ml 罐装",
    "subCategoryId": 1,
    "supplierId": 1,
    "ItemOriginalprice": 3,
    "Itemprice": 3,
    "ItemDescription": "易拉罐 红罐 可乐 可口可乐",
    "ItemPicture": "http://baidu.com/1.png",
    "ItemStatus": "in stock"
  },
  {
    "itemId": 1,
    "ItemName": "可口可乐 330ml 罐装",
    "subCategoryId": 1,
    "supplierId": 1,
    "ItemOriginalprice": 3,
    "Itemprice": 3,
    "ItemDescription": "易拉罐 红罐 可乐 可口可乐",
    "ItemPicture": "http://baidu.com/1.png",
    "ItemStatus": "in stock"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» itemId|integer|true|none|none|
|» ItemName|string|true|none|none|
|» subCategoryId|integer|true|none|none|
|» supplierId|integer|true|none|none|
|» ItemOriginalprice|integer|true|none|none|
|» Itemprice|integer|true|none|none|
|» ItemDescription|string|true|none|none|
|» ItemPicture|string|true|none|none|
|» ItemStatus|string|true|none|none|

# 废弃接口/商品管理/item

## POST add Item

POST /goods/item

添加商品

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## DELETE delete Item

DELETE /goods/item

删除商品

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|itemId|query|string|true|(Required) 商品id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## PUT modify Item

PUT /goods/item

修改商品

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

## GET get Item

GET /goods/item

获取商品

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|itemId|query|string|true|(Required) 商品id|

> 返回示例

> OK

```json
{
  "itemId": 1,
  "ItemName": "可口可乐 330ml 罐装",
  "subCategoryId": 1,
  "supplierId": 1,
  "ItemOriginalprice": 3,
  "Itemprice": 3,
  "ItemDescription": "易拉罐 红罐 可乐 可口可乐",
  "ItemPicture": "http://baidu.com/1.png",
  "ItemStatus": "in stock"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|ERROR|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» itemId|integer|false|none|none|
|» ItemName|string|false|none|none|
|» subCategoryId|integer|false|none|none|
|» supplierId|integer|false|none|none|
|» ItemOriginalprice|integer|false|none|none|
|» Itemprice|integer|false|none|none|
|» ItemDescription|string|false|none|none|
|» ItemPicture|string|false|none|none|
|» ItemStatus|string|false|none|none|

# 废弃接口/库存管理

## PUT modify Warehouse Inventory

PUT /warehouse/{warehouseId}/inventory

加减库存

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|warehouseId|path|string|true|(Required) 库房id|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|OK|Inline|

### 返回数据结构

## GET get Warehouse Inventory

GET /warehouse/{warehouseId}/inventory

获取库存

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|warehouseId|path|string|true|(Required) 库房id|

> 返回示例

> OK

```json
"schema type not provided"
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|OK|undefined|

## POST add Warehouse Inventory

POST /warehouse/{warehouseId}/inventory

新添库存

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|warehouseId|path|string|true|(Required) 库房id|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|OK|Inline|

### 返回数据结构

## POST 调货入库

POST /mainsites/{mainsiteId}/inventory/adjust_items

> 调货商品到达缺货仓库后，库房管理员处理调货单，处理后跳转到商品入库页面审核，确认调货入库

1. 根据调货单信息填充入库表单部分内容
2. 库房管理员选择入库仓库
3. 调货入库后，需判断是否有未完成任务单, 并执行相应业务

> Body 请求参数

```yaml
type: object
properties:
  adjustid:
    type: string
    description: 调货单编号
  warehouseId:
    type: string
    description: 入库仓库编号
  approver:
    type: string
    description: 操作执行人编号（员工编号）
required:
  - adjustid
  - warehouseId
  - approver

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|body|body|object|false|none|
|» adjustid|body|string|true|调货单编号|
|» warehouseId|body|string|true|入库仓库编号|
|» approver|body|string|true|操作执行人编号（员工编号）|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 补货入库

POST /mainsites/{mainsiteId}/inventory/items

> 补货商品到达，

1. 需手动填写入库表单
2. 支持批量新增
3. 新商品采购需跳转至商品管理界面进行商品添加; 之后再返回进行商品入库

> Body 请求参数

```yaml
type: object
properties:
  itemIdList:
    type: string
  itemNumList:
    type: string
  totalCostList:
    type: string
  approver:
    type: string
    description: 操作执行人编号（员工编号）
required:
  - itemIdList
  - itemNumList
  - totalCostList
  - approver

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|主站编号|
|body|body|object|false|none|
|» itemIdList|body|string|true|none|
|» itemNumList|body|string|true|none|
|» totalCostList|body|string|true|none|
|» approver|body|string|true|操作执行人编号（员工编号）|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## DELETE 主站库房调拨出库

DELETE /mainsites/{mainsiteId}/inventory/items

1. 填写出库目标主站, 生成调货单
2. 不含缺货调货操作
3. 支持批量出库

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|主站编号|
|desMainSiteId|query|string|true|目标主站ID|
|itemIdList|query|string|true|none|
|itemNumList|query|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查询入库商品详细信息

GET /mainsites/{mainsiteId}/inventory/items

> 本接口根据返回入库商品的详细信息

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|types|query|string|true|1表示补货，2表示调货，3表示退货|
|formIdList|query|string|true|表单编号，与types相对应。如这里的第一个值为调货单编号，第二个为退货单编号|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 退货入库

POST /mainsites/{mainsiteId}/inventory/returned_items

> 库房管理员在退货申请同意并收到客户寄到的商品后，将退货商品入库

> Body 请求参数

```yaml
type: object
properties:
  rfIdList:
    type: string
    description: 退货单编号
  approver:
    type: string
    description: 操作执行人编号（员工编号）
  warehouseId:
    type: string
    description: 入库仓库编号
required:
  - rfIdList
  - approver
  - warehouseId

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|主站编号|
|body|body|object|false|none|
|» rfIdList|body|string|true|退货单编号|
|» approver|body|string|true|操作执行人编号（员工编号）|
|» warehouseId|body|string|true|入库仓库编号|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## PATCH 库房间调货提交

PATCH /mainsites/{mainsiteId}/items/{itemId}/warehouse_dispatching

使用鼠标将item拖入库房列表项, 进行库房间调货提交
弹出模态框进行数目修改及确认

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|mainsiteId|path|string|true|none|
|itemId|path|string|true|none|
|sourceId|query|string|false|源库房Id|
|destId|query|string|false|目标库房Id|
|amount|query|string|false|调货数目, 不能超过原库房已有最大值|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 废弃接口/退换货管理-废弃

## POST 退货

POST /orders/{orderId}/issue/goods

退货

> Body 请求参数

```json
{
  "type": "object",
  "properties": {
    "itemId": {
      "type": "integer",
      "description": "商品id"
    },
    "itemAmount": {
      "type": "integer",
      "description": "商品数量"
    },
    "description": {
      "type": "string",
      "description": "理由"
    }
  },
  "description": "退货单",
  "required": [
    "itemId",
    "itemAmount",
    "description"
  ]
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|订单id|
|body|body|[ReturnOrder](#schemareturnorder)|false|none|

> 返回示例

> OK

```json
{
  "timestamp": "1993-01-24 05:17:02",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## PATCH 换货

PATCH /orders/{orderId}/issue/goods

换货

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|string|true|订单id|
|body|body|object|false|none|

> 返回示例

> OK

```json
{
  "timestamp": "1995-04-16 16:48:38",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## PATCH 审批退换货单

PATCH /order/issue/goods

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 获取退换货订单

GET /orders/issue/goods

## 客服
根据条件查询退换货单（city，itemId）
## 用户
根据用户id查询退换货单（userId）

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|city|query|string|false|取货地址|
|itemId|query|string|false|商品id|
|userId|query|string|false|用户id|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 废弃接口/增删改查/客户管理/客户

## PUT 修改客户信息

PUT /user/customer

PUT(全部信息修改)

> Body 请求参数

```json
{
  "title": "Customer",
  "description": "客户",
  "type": "object",
  "required": [
    "customerId",
    "name",
    "tel",
    "email",
    "addr",
    "district",
    "city",
    "province"
  ],
  "properties": {
    "customerId": {
      "type": "integer",
      "format": "int64",
      "description": "客户id",
      "example": 1
    },
    "name": {
      "type": "string",
      "description": "客户姓名",
      "example": "张三"
    },
    "tel": {
      "type": "string",
      "description": "客户邮箱",
      "example": "10086"
    },
    "email": {
      "type": "string",
      "description": "客户电话",
      "example": "10001@qq.com"
    },
    "addr": {
      "type": "string",
      "description": "客户详细地址",
      "example": "北京市东城区王府井大街1号"
    },
    "district": {
      "type": "string",
      "description": "客户所在行政区区"
    },
    "city": {
      "type": "string",
      "description": "客户所在市"
    },
    "province": {
      "type": "string",
      "description": "所在省"
    }
  }
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[CustomerWithId](#schemacustomerwithid)|false|none|

> 返回示例

> OK

```json
{
  "timestamp": "2003-03-30 18:51:39",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## DELETE 删除客户

DELETE /user/customer

删除客户

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|customerId|query|string|true|客户id|

> 返回示例

> OK

```json
{
  "timestamp": "1997-05-23 01:36:16",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## GET 获取客户信息

GET /user/customer

获取客户信息

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|customerId|query|string|false|客户id|
|all|query|string|false|是否获取全部客户信息|

> 返回示例

> OK

```json
[
  {
    "customerId": 10946293,
    "customerName": "事养计住直完数",
    "customerPhone": "18685471758",
    "customerEmail": "l.hlvy@qq.com",
    "customerAddress": "海南省德宏傣族景颇族自治州平川区"
  },
  {
    "customerId": -46415003,
    "customerName": "务感经",
    "customerPhone": "19884235159",
    "customerEmail": "g.vmhpbfb@qq.com",
    "customerAddress": "广西壮族自治区酒泉市双峰县"
  },
  {
    "customerId": -25840048,
    "customerName": "决林近的便",
    "customerPhone": "18151433473",
    "customerEmail": "w.stbekqw@qq.com",
    "customerAddress": "香港特别行政区吴忠市张家川回族自治县"
  },
  {
    "customerId": -43801857,
    "customerName": "华织连",
    "customerPhone": "18657136851",
    "customerEmail": "i.tjjmxdigg@qq.com",
    "customerAddress": "广西壮族自治区九江市柞水县"
  },
  {
    "customerId": -28597916,
    "customerName": "象体题马需因",
    "customerPhone": "18642280148",
    "customerEmail": "q.njec@qq.com",
    "customerAddress": "陕西省上海市平湖市"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[CustomerWithId](#schemacustomerwithid)]|false|none|[客户]|
|» Customer|[CustomerWithId](#schemacustomerwithid)|false|none|客户|
|»» customerId|integer(int64)|true|none|客户id|
|»» name|string|true|none|客户姓名|
|»» tel|string|true|none|客户邮箱|
|»» email|string|true|none|客户电话|
|»» addr|string|true|none|客户详细地址|
|»» district|string|true|none|客户所在行政区区|
|»» city|string|true|none|客户所在市|
|»» province|string|true|none|所在省|

## POST 添加客户

POST /user/customer

添加客户

> Body 请求参数

```json
{
  "title": "Customer",
  "description": "客户",
  "type": "object",
  "required": [
    "customerName",
    "customerPhone",
    "customerEmail",
    "customerAddress"
  ],
  "properties": {
    "customerName": {
      "type": "string",
      "description": "客户姓名",
      "example": "张三"
    },
    "customerPhone": {
      "type": "string",
      "description": "客户电话",
      "example": "10086"
    },
    "customerEmail": {
      "type": "string",
      "description": "客户邮箱",
      "example": "10001@qq.com"
    },
    "customerAddress": {
      "type": "string",
      "description": "客户地址",
      "example": "北京市东城区王府井大街1号"
    }
  }
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Customer](#schemacustomer)|false|none|

> 返回示例

> OK

```json
{
  "timestamp": "2012-03-12 01:48:41",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

# 废弃接口/增删改查/客户管理/供应商

## DELETE 删除供应商

DELETE /user/supplier

删除供应商

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|supplierId|query|string|true|供应商id|

> 返回示例

> OK

```json
{
  "timestamp": "1984-04-29 02:19:10",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## GET 获取供应商信息

GET /user/supplier

获取供应商信息

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|supplierId|query|string|false|供应商id|
|all|query|string|false|是否获取全部供应商信息|

> 返回示例

> OK

```json
[
  {
    "supplierId": 64145501,
    "supplierName": "声部列条",
    "contactName": "验增在",
    "contactPhone": "18664282252",
    "supplierProvince": "新疆维吾尔自治区",
    "supplierCity": "广州市",
    "supplierAddress": "辽宁省咸阳市安溪县"
  },
  {
    "supplierId": -60336558,
    "supplierName": "利原头由增张",
    "contactName": "易活书物易复实",
    "contactPhone": "18153561366",
    "supplierProvince": "湖南省",
    "supplierCity": "潍坊市",
    "supplierAddress": "云南省商洛市吴兴区"
  },
  {
    "supplierId": 10827412,
    "supplierName": "起心交运越群",
    "contactName": "石等数文",
    "contactPhone": "19857228571",
    "supplierProvince": "江苏省",
    "supplierCity": "丽水市",
    "supplierAddress": "河南省绍兴市沭阳县"
  },
  {
    "supplierId": 50563655,
    "supplierName": "照验期办",
    "contactName": "素大海受",
    "contactPhone": "18111756891",
    "supplierProvince": "黑龙江省",
    "supplierCity": "上海市",
    "supplierAddress": "湖北省辽源市乃东县"
  },
  {
    "supplierId": -83424776,
    "supplierName": "识热合物石见",
    "contactName": "须状被层存",
    "contactPhone": "18112372151",
    "supplierProvince": "山东省",
    "supplierCity": "齐齐哈尔市",
    "supplierAddress": "内蒙古自治区吐鲁番地区-"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[SupplierWithId](#schemasupplierwithid)]|false|none|[供应商]|
|» Supplier|[SupplierWithId](#schemasupplierwithid)|false|none|供应商|
|»» supplierId|integer(int64)|true|none|供应商id|
|»» supplierName|string|true|none|供应商名称|
|»» contactName|string|true|none|联系人姓名|
|»» contactPhone|string|true|none|联系人电话|
|»» supplierProvince|string|true|none|北京市|
|»» supplierCity|string|true|none|北京市|
|»» supplierAddress|string|true|none|供应商具体地址|

## PUT 修改供应商信息

PUT /user/supplier

PUT(全部信息修改)

> Body 请求参数

```json
{
  "title": "Supplier",
  "description": "供应商",
  "type": "object",
  "required": [
    "supplierId",
    "supplierName",
    "contactName",
    "contactPhone",
    "supplierProvince",
    "supplierCity",
    "supplierAddress"
  ],
  "properties": {
    "supplierId": {
      "type": "integer",
      "format": "int64",
      "description": "供应商id",
      "example": 1
    },
    "supplierName": {
      "type": "string",
      "description": "供应商名称",
      "example": "可口可乐"
    },
    "contactName": {
      "type": "string",
      "description": "联系人姓名",
      "example": "李四"
    },
    "contactPhone": {
      "type": "string",
      "description": "联系人电话",
      "example": "10000"
    },
    "supplierProvince": {
      "type": "string",
      "description": "北京市"
    },
    "supplierCity": {
      "type": "string",
      "description": "北京市"
    },
    "supplierAddress": {
      "type": "string",
      "description": "供应商具体地址"
    }
  }
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[SupplierWithId](#schemasupplierwithid)|false|none|

> 返回示例

> OK

```json
{
  "timestamp": "1991-07-24 13:14:29",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## POST 添加供应商

POST /user/supplier

添加供应商

> Body 请求参数

```json
{
  "title": "Supplier",
  "description": "供应商",
  "type": "object",
  "required": [
    "supplierId",
    "brandName",
    "tel",
    "email",
    "province",
    "city",
    "district",
    "addr",
    "managerName"
  ],
  "properties": {
    "supplierId": {
      "type": "string",
      "description": "供应商编号"
    },
    "brandName": {
      "type": "string",
      "description": "供应商品牌名",
      "example": "李四"
    },
    "managerName": {
      "type": "string",
      "description": "联系人姓名"
    },
    "tel": {
      "type": "string",
      "description": "联系人电话",
      "example": "10000"
    },
    "email": {
      "type": "string",
      "description": "供应商邮箱"
    },
    "province": {
      "type": "string",
      "description": "供应商总部所在省"
    },
    "city": {
      "type": "string",
      "description": "供应商总部所在市"
    },
    "district": {
      "type": "string",
      "description": "供应商总部所在区"
    },
    "addr": {
      "type": "string",
      "description": "供应商总部详细地址"
    }
  }
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Supplier](#schemasupplier)|false|none|

> 返回示例

> OK

```json
{
  "timestamp": "2009-08-30 19:54:59",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

# 废弃接口/增删改查/订单增删查

## DELETE 删除用户订单

DELETE /ordser/{userId}

删除用户订单

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userId|path|string|true|用户id|

> 返回示例

> OK

```json
{
  "timestamp": "1996-10-19 00:25:27",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## POST 新增用户订单

POST /ordser/{userId}

新增用户订单

> Body 请求参数

```json
{
  "title": "Order",
  "description": "订单",
  "type": "object",
  "required": [
    "orderId",
    "customerId",
    "payStatus",
    "processStatus",
    "billPro",
    "billCity",
    "billDistrict",
    "billAddr",
    "totalPrice",
    "billName"
  ],
  "properties": {
    "orderId": {
      "type": "integer",
      "description": "订单Id"
    },
    "customerId": {
      "type": "string",
      "example": "2020-07-05 12:00:00.000",
      "description": "买家id"
    },
    "payStatus": {
      "type": "string",
      "description": "付款状态: 已付款（P）未付款（W）"
    },
    "processStatus": {
      "type": "string",
      "description": "订单处理状态:未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close） "
    },
    "createDateTime": {
      "type": "string",
      "description": "订单创建时间"
    },
    "payDateTime": {
      "type": "string",
      "description": "订单付款时间"
    },
    "billName": {
      "type": "string",
      "description": "收件人姓名"
    },
    "billPro": {
      "type": "string",
      "example": "支付宝",
      "description": "收件所在省"
    },
    "billCity": {
      "type": "string",
      "example": "王五",
      "description": "收件所在市"
    },
    "billDistrict": {
      "type": "string",
      "description": "收件所在区"
    },
    "billAddr": {
      "type": "string",
      "example": "北京市",
      "description": "收件详细地址"
    },
    "totalPrice": {
      "type": "number",
      "description": "总付款"
    },
    "shippingCost": {
      "type": "number",
      "description": "运费"
    },
    "payMethod": {
      "type": "string",
      "description": "支付方式"
    },
    "note": {
      "type": "string",
      "description": "备注"
    }
  }
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userId|path|string|true|用户id|
|body|body|[Order](#schemaorder)|false|none|

> 返回示例

> OK

```json
{
  "timestamp": "1992-10-12 03:18:19",
  "status": 200,
  "data": {
    "ok": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

## GET 获取用户订单

GET /ordser/{userId}

获取用户订单

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userId|path|string|true|用户id|
|orderId|query|string|false|订单id|
|all|query|string|false|是否获取全部订单|

> 返回示例

> OK

```json
[
  {
    "orderId": 59765967,
    "createTime": "1991-03-26 11:19:07",
    "paymentTime": "1997-04-10 02:52:47",
    "paymentAmount": 75439834.70614779,
    "freight": -27856130.24722217,
    "status": "sunt ex",
    "paymentMethod": "Lorem magna ut",
    "receiver": "enim",
    "receiverPhone": "18172304160",
    "receiverAddress": "河北省天津市其它区",
    "remarks": "ut"
  },
  {
    "orderId": 98786452,
    "createTime": "1976-09-28 03:36:53",
    "paymentTime": "2003-05-31 14:54:09",
    "paymentAmount": -53368814.21606803,
    "freight": 33781343.12773469,
    "status": "cillum sint sed enim consequat",
    "paymentMethod": "Ut nisi",
    "receiver": "id magna et exercitation dolor",
    "receiverPhone": "19836856531",
    "receiverAddress": "广西壮族自治区深圳市朗县",
    "remarks": "adipisicing proident in Duis magna"
  },
  {
    "orderId": -35463819,
    "createTime": "2011-08-13 08:33:57",
    "paymentTime": "1972-03-31 08:06:17",
    "paymentAmount": -62780267.76209513,
    "freight": -55416938.86464763,
    "status": "sit est mollit",
    "paymentMethod": "dolor ad eu",
    "receiver": "adipisicing est tempor ipsum deserunt",
    "receiverPhone": "18108769321",
    "receiverAddress": "广西壮族自治区衡阳市石狮市",
    "remarks": "dolore velit sed"
  },
  {
    "orderId": 16991865,
    "createTime": "1997-05-04 08:35:23",
    "paymentTime": "1980-08-29 21:14:21",
    "paymentAmount": 95745832.28773409,
    "freight": -73580266.78216806,
    "status": "et culpa magna ad",
    "paymentMethod": "nisi",
    "receiver": "incididunt Excepteur",
    "receiverPhone": "18188848864",
    "receiverAddress": "贵州省果洛藏族自治州-",
    "remarks": "ut aliquip occaecat culpa sit"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

# 废弃接口/增删改查/库房管理

## DELETE delete Warehouse

DELETE /warehouse

删除库房

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|OK|Inline|

### 返回数据结构

## POST add Warehouse

POST /warehouse

添加库房

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|OK|Inline|

### 返回数据结构

## PUT modify Warehouse

PUT /warehouse

修改库房信息

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|OK|Inline|

### 返回数据结构

## GET get Warehouse

GET /warehouse

获取库房信息

> 返回示例

> OK

```json
"schema type not provided"
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|OK|undefined|

# 废弃接口/配送站管理-搁置

## PATCH 修改配送任务状态

PATCH /subsites/{subSiteId}/deliveries/{deliveryId}

使用场景:
1. 快递员拒绝当前配送任务
2. 无人签收快递
3. 快递已签收

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subSiteId|path|string|true|none|
|deliveryId|path|string|true|none|
|status|query|string|true|配送任务状态|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查询配送任务

GET /subsites/{subSiteId}/deliveries

根据快递员ID查询配送任务

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subSiteId|path|string|true|none|
|courierId|query|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 打印商品签收单

GET /subsites/{subSiteId}/deliveries/{deliveryId}/receipt

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subSiteId|path|string|true|none|
|deliveryId|path|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 配送站货物入站

POST /subsites/{subsiteId}/inventory/items

> Body 请求参数

```json
{
  "type": "object",
  "properties": {}
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subsiteId|path|string|true|none|
|body|body|object|false|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## DELETE 配送站领货出站

DELETE /subsites/{subsiteId}/inventory/items

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subsiteId|path|string|true|none|
|courierId|query|string|true|快递员ID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查询配送站缴款

GET /subsites/{subSiteId}/payments

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subSiteId|path|string|true|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 数据模型

<h2 id="tocS_SubCategoryWithId">SubCategoryWithId</h2>
<!-- backwards compatibility -->
<a id="schemasubcategorywithid"></a>
<a id="schema_SubCategoryWithId"></a>
<a id="tocSsubcategorywithid"></a>
<a id="tocssubcategorywithid"></a>

```json
{
  "title": "SubCategory",
  "description": "商品二级分类",
  "type": "object",
  "properties": {
    "subCategoryId": {
      "type": "integer",
      "format": "int64",
      "description": "二级分类id",
      "example": 1
    },
    "subCategoryName": {
      "type": "string",
      "description": "二级分类名称",
      "example": "Rice"
    },
    "categoryId": {
      "type": "integer",
      "format": "int64",
      "description": "一级分类id",
      "example": 1
    },
    "description": {
      "type": "string",
      "description": "二级分类描述",
      "example": "大米"
    }
  },
  "required": [
    "subCategoryId",
    "subCategoryName",
    "categoryId",
    "description"
  ]
}

```

SubCategory

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|subCategoryId|integer(int64)|true|none|二级分类id|
|subCategoryName|string|true|none|二级分类名称|
|categoryId|integer(int64)|true|none|一级分类id|
|description|string|true|none|二级分类描述|

<h2 id="tocS_Item">Item</h2>
<!-- backwards compatibility -->
<a id="schemaitem"></a>
<a id="schema_Item"></a>
<a id="tocSitem"></a>
<a id="tocsitem"></a>

```json
{
  "title": "Item",
  "description": "商品项",
  "type": "object",
  "properties": {
    "itemId": {
      "type": "string",
      "description": "商品id",
      "example": "可口可乐 330ml 罐装"
    },
    "categoryId": {
      "type": "string",
      "description": "大类id"
    },
    "name": {
      "type": "string",
      "description": "商品名称"
    },
    "descn": {
      "type": "string",
      "description": "文字描述"
    },
    "unitCost": {
      "type": "number",
      "format": "double",
      "description": "原价",
      "example": 3,
      "minimum": 0
    },
    "listPrice": {
      "type": "number",
      "format": "double",
      "description": "售价",
      "example": 3,
      "minimum": 0,
      "exclusiveMinimum": true
    },
    "imgUrl": {
      "type": "string",
      "description": "图片url",
      "example": "易拉罐 红罐 可乐 可口可乐"
    },
    "status": {
      "type": "string",
      "description": "商品状态：上架（P）；下架（F）",
      "example": "http://baidu.com/1.png"
    }
  },
  "required": [
    "itemId"
  ]
}

```

Item

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|itemId|string|true|none|商品id|
|categoryId|string|false|none|大类id|
|name|string|false|none|商品名称|
|descn|string|false|none|文字描述|
|unitCost|number(double)|false|none|原价|
|listPrice|number(double)|false|none|售价|
|imgUrl|string|false|none|图片url|
|status|string|false|none|商品状态：上架（P）；下架（F）|

<h2 id="tocS_OrderDetailResp">OrderDetailResp</h2>
<!-- backwards compatibility -->
<a id="schemaorderdetailresp"></a>
<a id="schema_OrderDetailResp"></a>
<a id="tocSorderdetailresp"></a>
<a id="tocsorderdetailresp"></a>

```json
{
  "type": "object",
  "properties": {
    "order": {
      "title": "Order",
      "description": "订单",
      "type": "object",
      "required": [
        "orderId",
        "customerId",
        "payStatus",
        "processStatus",
        "billPro",
        "billCity",
        "billDistrict",
        "billAddr",
        "totalPrice",
        "billName"
      ],
      "properties": {
        "orderId": {
          "type": "integer",
          "description": "订单Id"
        },
        "customerId": {
          "type": "string",
          "example": "2020-07-05 12:00:00.000",
          "description": "买家id"
        },
        "payStatus": {
          "type": "string",
          "description": "付款状态: 已付款（P）未付款（W）"
        },
        "processStatus": {
          "type": "string",
          "description": "订单处理状态:未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close） "
        },
        "createDateTime": {
          "type": "string",
          "description": "订单创建时间"
        },
        "payDateTime": {
          "type": "string",
          "description": "订单付款时间"
        },
        "billName": {
          "type": "string",
          "description": "收件人姓名"
        },
        "billPro": {
          "type": "string",
          "example": "支付宝",
          "description": "收件所在省"
        },
        "billCity": {
          "type": "string",
          "example": "王五",
          "description": "收件所在市"
        },
        "billDistrict": {
          "type": "string",
          "description": "收件所在区"
        },
        "billAddr": {
          "type": "string",
          "example": "北京市",
          "description": "收件详细地址"
        },
        "totalPrice": {
          "type": "number",
          "description": "总付款"
        },
        "shippingCost": {
          "type": "number",
          "description": "运费"
        },
        "payMethod": {
          "type": "string",
          "description": "支付方式"
        },
        "note": {
          "type": "string",
          "description": "备注"
        }
      }
    },
    "orderItemList": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "orderId": {
            "type": "integer",
            "description": "订单id"
          },
          "taskId": {
            "type": "any",
            "description": "任务单id"
          },
          "itemNum": {
            "type": "integer",
            "description": "商品数量",
            "minimum": 0,
            "maximum": 1000000,
            "exclusiveMinimum": true,
            "exclusiveMaximum": true
          },
          "status": {
            "type": "string",
            "description": "处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)"
          },
          "total": {
            "type": "number",
            "description": "单类商品付款总额"
          },
          "item": {
            "title": "Item",
            "description": "商品项",
            "type": "object",
            "properties": {
              "itemId": {
                "type": "string",
                "description": "商品id",
                "example": "可口可乐 330ml 罐装"
              },
              "categoryId": {
                "type": "string",
                "description": "大类id"
              },
              "name": {
                "type": "string",
                "description": "商品名称"
              },
              "descn": {
                "type": "string",
                "description": "文字描述"
              },
              "unitCost": {
                "type": "number",
                "format": "double",
                "description": "原价",
                "example": 3,
                "minimum": 0
              },
              "listPrice": {
                "type": "number",
                "format": "double",
                "description": "售价",
                "example": 3,
                "minimum": 0,
                "exclusiveMinimum": true
              },
              "imgUrl": {
                "type": "string",
                "description": "图片url",
                "example": "易拉罐 红罐 可乐 可口可乐"
              },
              "status": {
                "type": "string",
                "description": "商品状态：上架（P）；下架（F）",
                "example": "http://baidu.com/1.png"
              }
            },
            "required": [
              "itemId"
            ]
          }
        },
        "required": [
          "orderId",
          "itemNum",
          "status",
          "total",
          "item"
        ],
        "description": "订单商品项"
      },
      "description": "订单项列表"
    },
    "mainsite": {
      "type": "object",
      "properties": {
        "mainsiteId": {
          "type": "string",
          "description": "主站id"
        },
        "province": {
          "type": "string",
          "description": "主站所在省/直辖市"
        },
        "city": {
          "type": "string",
          "description": "主站所在城市"
        },
        "district": {
          "type": "string",
          "description": "主站所在区/县"
        },
        "longitude": {
          "type": "number",
          "description": "经度"
        },
        "latitude": {
          "type": "number",
          "description": "纬度"
        },
        "addr": {
          "type": "string",
          "description": "主站详细地址信息（具体的街道门牌号）"
        }
      },
      "required": [
        "mainsiteId",
        "province",
        "city",
        "district",
        "longitude",
        "latitude",
        "addr"
      ]
    },
    "subsite": {
      "type": "object",
      "properties": {
        "subsiteId": {
          "type": "string",
          "description": "配送站id"
        },
        "mainsiteId": {
          "type": "string",
          "description": "所属主站id"
        },
        "district": {
          "type": "string",
          "description": "配送站所在区"
        },
        "addr": {
          "type": "string",
          "description": "所在详细地址信息（具体的街道门牌号）"
        },
        "longitude": {
          "type": "number",
          "description": "经度"
        },
        "latitude": {
          "type": "number",
          "description": "纬度"
        }
      },
      "required": [
        "subsiteId",
        "mainsiteId",
        "district",
        "addr",
        "longitude",
        "latitude"
      ]
    },
    "msg": {
      "type": "any",
      "description": "预分拣结果信息"
    }
  },
  "required": [
    "order",
    "orderItemList"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|order|[Order](#schemaorder)|true|none|订单|
|orderItemList|[[OrderItem](#schemaorderitem)]|true|none|订单项列表|
|mainsite|[MainSite](#schemamainsite)|false|none|none|
|subsite|[SubSite](#schemasubsite)|false|none|none|
|msg|any|false|none|预分拣结果信息|

<h2 id="tocS_ItemSupplyResp">ItemSupplyResp</h2>
<!-- backwards compatibility -->
<a id="schemaitemsupplyresp"></a>
<a id="schema_ItemSupplyResp"></a>
<a id="tocSitemsupplyresp"></a>
<a id="tocsitemsupplyresp"></a>

```json
{
  "type": "object",
  "properties": {
    "supplierId": {
      "type": "string",
      "description": "供应商编号"
    },
    "itemSupplyList": {
      "type": "array",
      "items": {
        "title": "Item",
        "description": "商品项",
        "type": "object",
        "properties": {
          "itemId": {
            "type": "string",
            "description": "商品id",
            "example": "可口可乐 330ml 罐装"
          },
          "categoryId": {
            "type": "string",
            "description": "大类id"
          },
          "name": {
            "type": "string",
            "description": "商品名称"
          },
          "descn": {
            "type": "string",
            "description": "文字描述"
          },
          "unitCost": {
            "type": "number",
            "format": "double",
            "description": "原价",
            "example": 3,
            "minimum": 0
          },
          "listPrice": {
            "type": "number",
            "format": "double",
            "description": "售价",
            "example": 3,
            "minimum": 0,
            "exclusiveMinimum": true
          },
          "imgUrl": {
            "type": "string",
            "description": "图片url",
            "example": "易拉罐 红罐 可乐 可口可乐"
          },
          "status": {
            "type": "string",
            "description": "商品状态：上架（P）；下架（F）",
            "example": "http://baidu.com/1.png"
          }
        },
        "required": [
          "itemId"
        ]
      },
      "description": "商品供应表"
    }
  },
  "required": [
    "supplierId",
    "itemSupplyList"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|supplierId|string|true|none|供应商编号|
|itemSupplyList|[[Item](#schemaitem)]|true|none|商品供应表|

<h2 id="tocS_PageRequest">PageRequest</h2>
<!-- backwards compatibility -->
<a id="schemapagerequest"></a>
<a id="schema_PageRequest"></a>
<a id="tocSpagerequest"></a>
<a id="tocspagerequest"></a>

```json
{
  "type": "object",
  "properties": {
    "pageNum": {
      "type": "integer",
      "description": "当前页码"
    },
    "pageSize": {
      "type": "integer",
      "description": "每页数量"
    }
  },
  "required": [
    "pageNum",
    "pageSize"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|pageNum|integer|true|none|当前页码|
|pageSize|integer|true|none|每页数量|

<h2 id="tocS_ReturnOrder">ReturnOrder</h2>
<!-- backwards compatibility -->
<a id="schemareturnorder"></a>
<a id="schema_ReturnOrder"></a>
<a id="tocSreturnorder"></a>
<a id="tocsreturnorder"></a>

```json
{
  "type": "object",
  "properties": {
    "itemId": {
      "type": "integer",
      "description": "商品id"
    },
    "itemAmount": {
      "type": "integer",
      "description": "商品数量"
    },
    "description": {
      "type": "string",
      "description": "理由"
    }
  },
  "description": "退货单",
  "required": [
    "itemId",
    "itemAmount",
    "description"
  ]
}

```

退货单

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|itemId|integer|true|none|商品id|
|itemAmount|integer|true|none|商品数量|
|description|string|true|none|理由|

<h2 id="tocS_OrderItem">OrderItem</h2>
<!-- backwards compatibility -->
<a id="schemaorderitem"></a>
<a id="schema_OrderItem"></a>
<a id="tocSorderitem"></a>
<a id="tocsorderitem"></a>

```json
{
  "type": "object",
  "properties": {
    "orderId": {
      "type": "integer",
      "description": "订单id"
    },
    "taskId": {
      "type": "any",
      "description": "任务单id"
    },
    "itemNum": {
      "type": "integer",
      "description": "商品数量",
      "minimum": 0,
      "maximum": 1000000,
      "exclusiveMinimum": true,
      "exclusiveMaximum": true
    },
    "status": {
      "type": "string",
      "description": "处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)"
    },
    "total": {
      "type": "number",
      "description": "单类商品付款总额"
    },
    "item": {
      "title": "Item",
      "description": "商品项",
      "type": "object",
      "properties": {
        "itemId": {
          "type": "string",
          "description": "商品id",
          "example": "可口可乐 330ml 罐装"
        },
        "categoryId": {
          "type": "string",
          "description": "大类id"
        },
        "name": {
          "type": "string",
          "description": "商品名称"
        },
        "descn": {
          "type": "string",
          "description": "文字描述"
        },
        "unitCost": {
          "type": "number",
          "format": "double",
          "description": "原价",
          "example": 3,
          "minimum": 0
        },
        "listPrice": {
          "type": "number",
          "format": "double",
          "description": "售价",
          "example": 3,
          "minimum": 0,
          "exclusiveMinimum": true
        },
        "imgUrl": {
          "type": "string",
          "description": "图片url",
          "example": "易拉罐 红罐 可乐 可口可乐"
        },
        "status": {
          "type": "string",
          "description": "商品状态：上架（P）；下架（F）",
          "example": "http://baidu.com/1.png"
        }
      },
      "required": [
        "itemId"
      ]
    }
  },
  "required": [
    "orderId",
    "itemNum",
    "status",
    "total",
    "item"
  ],
  "description": "订单商品项"
}

```

订单商品项

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|orderId|integer|true|none|订单id|
|taskId|any|false|none|任务单id|
|itemNum|integer|true|none|商品数量|
|status|string|true|none|处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)|
|total|number|true|none|单类商品付款总额|
|item|[Item](#schemaitem)|true|none|商品项|

<h2 id="tocS_ItemCheckoutResp">ItemCheckoutResp</h2>
<!-- backwards compatibility -->
<a id="schemaitemcheckoutresp"></a>
<a id="schema_ItemCheckoutResp"></a>
<a id="tocSitemcheckoutresp"></a>
<a id="tocsitemcheckoutresp"></a>

```json
{
  "type": "object",
  "properties": {
    "type": {
      "type": "integer",
      "description": "1：退货给供应商，2：调货出库，3：发货出库"
    },
    "typeDesc": {
      "type": "string",
      "description": "对type属性的描述"
    },
    "formId": {
      "type": "integer",
      "description": "例如：调货的调货单编号"
    },
    "itemId": {
      "type": "string"
    },
    "itemNum": {
      "type": "integer"
    },
    "recordId": {
      "type": "integer",
      "description": "出库记录编号"
    }
  },
  "required": [
    "recordId",
    "type",
    "typeDesc",
    "formId",
    "itemId",
    "itemNum"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|type|integer|true|none|1：退货给供应商，2：调货出库，3：发货出库|
|typeDesc|string|true|none|对type属性的描述|
|formId|integer|true|none|例如：调货的调货单编号|
|itemId|string|true|none|none|
|itemNum|integer|true|none|none|
|recordId|integer|true|none|出库记录编号|

<h2 id="tocS_SupplierAddReq">SupplierAddReq</h2>
<!-- backwards compatibility -->
<a id="schemasupplieraddreq"></a>
<a id="schema_SupplierAddReq"></a>
<a id="tocSsupplieraddreq"></a>
<a id="tocssupplieraddreq"></a>

```json
{
  "type": "object",
  "properties": {
    "supplier": {
      "title": "Supplier",
      "description": "供应商",
      "type": "object",
      "required": [
        "supplierId",
        "brandName",
        "tel",
        "email",
        "province",
        "city",
        "district",
        "addr",
        "managerName"
      ],
      "properties": {
        "supplierId": {
          "type": "string",
          "description": "供应商编号"
        },
        "brandName": {
          "type": "string",
          "description": "供应商品牌名",
          "example": "李四"
        },
        "managerName": {
          "type": "string",
          "description": "联系人姓名"
        },
        "tel": {
          "type": "string",
          "description": "联系人电话",
          "example": "10000"
        },
        "email": {
          "type": "string",
          "description": "供应商邮箱"
        },
        "province": {
          "type": "string",
          "description": "供应商总部所在省"
        },
        "city": {
          "type": "string",
          "description": "供应商总部所在市"
        },
        "district": {
          "type": "string",
          "description": "供应商总部所在区"
        },
        "addr": {
          "type": "string",
          "description": "供应商总部详细地址"
        }
      }
    },
    "itemSupplyList": {
      "type": "array",
      "items": {
        "type": "string",
        "description": "供应商品Id"
      },
      "description": "供应商供应商品列表"
    }
  },
  "required": [
    "supplier",
    "itemSupplyList"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|supplier|[Supplier](#schemasupplier)|true|none|供应商|
|itemSupplyList|[string]|true|none|供应商供应商品列表|

<h2 id="tocS_SiteIOCheckinResp">SiteIOCheckinResp</h2>
<!-- backwards compatibility -->
<a id="schemasiteiocheckinresp"></a>
<a id="schema_SiteIOCheckinResp"></a>
<a id="tocSsiteiocheckinresp"></a>
<a id="tocssiteiocheckinresp"></a>

```json
{
  "type": "object",
  "properties": {
    "recordId": {
      "type": "integer"
    },
    "timeStamp": {
      "type": "string"
    },
    "warehouseId": {
      "type": "string"
    },
    "item": {
      "title": "Item",
      "description": "商品项",
      "type": "object",
      "properties": {
        "itemId": {
          "type": "string",
          "description": "商品id",
          "example": "可口可乐 330ml 罐装"
        },
        "categoryId": {
          "type": "string",
          "description": "大类id"
        },
        "name": {
          "type": "string",
          "description": "商品名称"
        },
        "descn": {
          "type": "string",
          "description": "文字描述"
        },
        "unitCost": {
          "type": "number",
          "format": "double",
          "description": "原价",
          "example": 3,
          "minimum": 0
        },
        "listPrice": {
          "type": "number",
          "format": "double",
          "description": "售价",
          "example": 3,
          "minimum": 0,
          "exclusiveMinimum": true
        },
        "imgUrl": {
          "type": "string",
          "description": "图片url",
          "example": "易拉罐 红罐 可乐 可口可乐"
        },
        "status": {
          "type": "string",
          "description": "商品状态：上架（P）；下架（F）",
          "example": "http://baidu.com/1.png"
        }
      },
      "required": [
        "itemId"
      ]
    },
    "itemNum": {
      "type": "integer"
    },
    "type": {
      "type": "integer",
      "description": "1表示补货，2表示调货，3表示退货，4表示换货"
    },
    "typeDesc": {
      "type": "string",
      "description": "对type属性的描述，如“补货”"
    },
    "formId": {
      "type": "integer",
      "description": "相关的表单编号"
    },
    "itemSrc": {
      "type": "string",
      "description": "货物来源，例如：用户名"
    },
    "approvalStatus": {
      "type": "string"
    },
    "approver": {
      "type": "string"
    },
    "warehouseOptionalList": {
      "type": "array",
      "items": {
        "type": "string",
        "description": "warehouseId"
      },
      "description": "可供选择的库房列表"
    }
  },
  "required": [
    "warehouseOptionalList"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|recordId|integer|false|none|none|
|timeStamp|string|false|none|none|
|warehouseId|string|false|none|none|
|item|[Item](#schemaitem)|false|none|商品项|
|itemNum|integer|false|none|none|
|type|integer|false|none|1表示补货，2表示调货，3表示退货，4表示换货|
|typeDesc|string|false|none|对type属性的描述，如“补货”|
|formId|integer|false|none|相关的表单编号|
|itemSrc|string|false|none|货物来源，例如：用户名|
|approvalStatus|string|false|none|none|
|approver|string|false|none|none|
|warehouseOptionalList|[string]|true|none|可供选择的库房列表|

<h2 id="tocS_Customer">Customer</h2>
<!-- backwards compatibility -->
<a id="schemacustomer"></a>
<a id="schema_Customer"></a>
<a id="tocScustomer"></a>
<a id="tocscustomer"></a>

```json
{
  "title": "Customer",
  "description": "客户",
  "type": "object",
  "required": [
    "customerName",
    "customerPhone",
    "customerEmail",
    "customerAddress"
  ],
  "properties": {
    "customerName": {
      "type": "string",
      "description": "客户姓名",
      "example": "张三"
    },
    "customerPhone": {
      "type": "string",
      "description": "客户电话",
      "example": "10086"
    },
    "customerEmail": {
      "type": "string",
      "description": "客户邮箱",
      "example": "10001@qq.com"
    },
    "customerAddress": {
      "type": "string",
      "description": "客户地址",
      "example": "北京市东城区王府井大街1号"
    }
  }
}

```

Customer

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|customerName|string|true|none|客户姓名|
|customerPhone|string|true|none|客户电话|
|customerEmail|string|true|none|客户邮箱|
|customerAddress|string|true|none|客户地址|

<h2 id="tocS_CustomerReq">CustomerReq</h2>
<!-- backwards compatibility -->
<a id="schemacustomerreq"></a>
<a id="schema_CustomerReq"></a>
<a id="tocScustomerreq"></a>
<a id="tocscustomerreq"></a>

```json
{
  "type": "object",
  "properties": {
    "name": {
      "type": "string",
      "description": "顾客姓名"
    },
    "district": {
      "type": "string",
      "description": "行政区"
    },
    "city": {
      "type": "string",
      "description": "城市"
    },
    "province": {
      "type": "string",
      "description": "省"
    }
  },
  "required": [
    "name",
    "district",
    "city",
    "province"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|name|string|true|none|顾客姓名|
|district|string|true|none|行政区|
|city|string|true|none|城市|
|province|string|true|none|省|

<h2 id="tocS_Category">Category</h2>
<!-- backwards compatibility -->
<a id="schemacategory"></a>
<a id="schema_Category"></a>
<a id="tocScategory"></a>
<a id="tocscategory"></a>

```json
{
  "title": "Category",
  "description": "商品分类",
  "type": "object",
  "properties": {
    "categoryId": {
      "type": "string",
      "description": "分类名称",
      "example": "Food"
    },
    "descn": {
      "type": "string",
      "description": "分类描述",
      "example": "吃"
    },
    "name": {
      "type": "string",
      "description": "分类名"
    }
  },
  "required": [
    "categoryId",
    "descn",
    "name"
  ]
}

```

Category

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|categoryId|string|true|none|分类名称|
|descn|string|true|none|分类描述|
|name|string|true|none|分类名|

<h2 id="tocS_SupplierWithId">SupplierWithId</h2>
<!-- backwards compatibility -->
<a id="schemasupplierwithid"></a>
<a id="schema_SupplierWithId"></a>
<a id="tocSsupplierwithid"></a>
<a id="tocssupplierwithid"></a>

```json
{
  "title": "Supplier",
  "description": "供应商",
  "type": "object",
  "required": [
    "supplierId",
    "supplierName",
    "contactName",
    "contactPhone",
    "supplierProvince",
    "supplierCity",
    "supplierAddress"
  ],
  "properties": {
    "supplierId": {
      "type": "integer",
      "format": "int64",
      "description": "供应商id",
      "example": 1
    },
    "supplierName": {
      "type": "string",
      "description": "供应商名称",
      "example": "可口可乐"
    },
    "contactName": {
      "type": "string",
      "description": "联系人姓名",
      "example": "李四"
    },
    "contactPhone": {
      "type": "string",
      "description": "联系人电话",
      "example": "10000"
    },
    "supplierProvince": {
      "type": "string",
      "description": "北京市"
    },
    "supplierCity": {
      "type": "string",
      "description": "北京市"
    },
    "supplierAddress": {
      "type": "string",
      "description": "供应商具体地址"
    }
  }
}

```

Supplier

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|supplierId|integer(int64)|true|none|供应商id|
|supplierName|string|true|none|供应商名称|
|contactName|string|true|none|联系人姓名|
|contactPhone|string|true|none|联系人电话|
|supplierProvince|string|true|none|北京市|
|supplierCity|string|true|none|北京市|
|supplierAddress|string|true|none|供应商具体地址|

<h2 id="tocS_RouteResp">RouteResp</h2>
<!-- backwards compatibility -->
<a id="schemarouteresp"></a>
<a id="schema_RouteResp"></a>
<a id="tocSrouteresp"></a>
<a id="tocsrouteresp"></a>

```json
{
  "type": "object",
  "properties": {
    "distance": {
      "type": "number",
      "description": "方案距离，单位：米"
    },
    "duration": {
      "type": "number",
      "description": "线路耗时，单位：秒"
    },
    "steps": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "leg_index": {
            "type": "integer",
            "description": "途径点序号"
          },
          "start_location": {
            "type": "object",
            "properties": {
              "lng": {
                "type": "number",
                "description": "经度"
              },
              "lat": {
                "type": "number",
                "description": "纬度"
              }
            },
            "description": "分段起点",
            "required": [
              "lng",
              "lat"
            ]
          },
          "end_location": {
            "type": "object",
            "properties": {
              "lng": {
                "type": "number",
                "description": "经度"
              },
              "lat": {
                "type": "number",
                "description": "纬度"
              }
            },
            "description": "分段终点",
            "required": [
              "lng",
              "lat"
            ]
          }
        },
        "required": [
          "leg_index",
          "start_location",
          "end_location"
        ]
      },
      "description": "途径点"
    }
  },
  "description": "路径",
  "required": [
    "distance",
    "duration",
    "steps"
  ]
}

```

路径

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|distance|number|true|none|方案距离，单位：米|
|duration|number|true|none|线路耗时，单位：秒|
|steps|[object]|true|none|途径点|
|» leg_index|integer|true|none|途径点序号|
|» start_location|object|true|none|分段起点|
|»» lng|number|true|none|经度|
|»» lat|number|true|none|纬度|
|» end_location|object|true|none|分段终点|
|»» lng|number|true|none|经度|
|»» lat|number|true|none|纬度|

<h2 id="tocS_SubSite">SubSite</h2>
<!-- backwards compatibility -->
<a id="schemasubsite"></a>
<a id="schema_SubSite"></a>
<a id="tocSsubsite"></a>
<a id="tocssubsite"></a>

```json
{
  "type": "object",
  "properties": {
    "subsiteId": {
      "type": "string",
      "description": "配送站id"
    },
    "mainsiteId": {
      "type": "string",
      "description": "所属主站id"
    },
    "district": {
      "type": "string",
      "description": "配送站所在区"
    },
    "addr": {
      "type": "string",
      "description": "所在详细地址信息（具体的街道门牌号）"
    },
    "longitude": {
      "type": "number",
      "description": "经度"
    },
    "latitude": {
      "type": "number",
      "description": "纬度"
    }
  },
  "required": [
    "subsiteId",
    "mainsiteId",
    "district",
    "addr",
    "longitude",
    "latitude"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|subsiteId|string|true|none|配送站id|
|mainsiteId|string|true|none|所属主站id|
|district|string|true|none|配送站所在区|
|addr|string|true|none|所在详细地址信息（具体的街道门牌号）|
|longitude|number|true|none|经度|
|latitude|number|true|none|纬度|

<h2 id="tocS_OrderAddReq">OrderAddReq</h2>
<!-- backwards compatibility -->
<a id="schemaorderaddreq"></a>
<a id="schema_OrderAddReq"></a>
<a id="tocSorderaddreq"></a>
<a id="tocsorderaddreq"></a>

```json
{
  "type": "object",
  "properties": {
    "order": {
      "title": "Order",
      "description": "订单",
      "type": "object",
      "required": [
        "orderId",
        "customerId",
        "payStatus",
        "processStatus",
        "billPro",
        "billCity",
        "billDistrict",
        "billAddr",
        "totalPrice",
        "billName"
      ],
      "properties": {
        "orderId": {
          "type": "integer",
          "description": "订单Id"
        },
        "customerId": {
          "type": "string",
          "example": "2020-07-05 12:00:00.000",
          "description": "买家id"
        },
        "payStatus": {
          "type": "string",
          "description": "付款状态: 已付款（P）未付款（W）"
        },
        "processStatus": {
          "type": "string",
          "description": "订单处理状态:未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close） "
        },
        "createDateTime": {
          "type": "string",
          "description": "订单创建时间"
        },
        "payDateTime": {
          "type": "string",
          "description": "订单付款时间"
        },
        "billName": {
          "type": "string",
          "description": "收件人姓名"
        },
        "billPro": {
          "type": "string",
          "example": "支付宝",
          "description": "收件所在省"
        },
        "billCity": {
          "type": "string",
          "example": "王五",
          "description": "收件所在市"
        },
        "billDistrict": {
          "type": "string",
          "description": "收件所在区"
        },
        "billAddr": {
          "type": "string",
          "example": "北京市",
          "description": "收件详细地址"
        },
        "totalPrice": {
          "type": "number",
          "description": "总付款"
        },
        "shippingCost": {
          "type": "number",
          "description": "运费"
        },
        "payMethod": {
          "type": "string",
          "description": "支付方式"
        },
        "note": {
          "type": "string",
          "description": "备注"
        }
      }
    },
    "orderItemList": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "orderId": {
            "type": "integer",
            "description": "订单id"
          },
          "taskId": {
            "type": "any",
            "description": "任务单id"
          },
          "itemNum": {
            "type": "integer",
            "description": "商品数量",
            "minimum": 0,
            "maximum": 1000000,
            "exclusiveMinimum": true,
            "exclusiveMaximum": true
          },
          "status": {
            "type": "string",
            "description": "处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)"
          },
          "total": {
            "type": "number",
            "description": "单类商品付款总额"
          },
          "item": {
            "title": "Item",
            "description": "商品项",
            "type": "object",
            "properties": {
              "itemId": {
                "type": "string",
                "description": "商品id",
                "example": "可口可乐 330ml 罐装"
              },
              "categoryId": {
                "type": "string",
                "description": "大类id"
              },
              "name": {
                "type": "string",
                "description": "商品名称"
              },
              "descn": {
                "type": "string",
                "description": "文字描述"
              },
              "unitCost": {
                "type": "number",
                "format": "double",
                "description": "原价",
                "example": 3,
                "minimum": 0
              },
              "listPrice": {
                "type": "number",
                "format": "double",
                "description": "售价",
                "example": 3,
                "minimum": 0,
                "exclusiveMinimum": true
              },
              "imgUrl": {
                "type": "string",
                "description": "图片url",
                "example": "易拉罐 红罐 可乐 可口可乐"
              },
              "status": {
                "type": "string",
                "description": "商品状态：上架（P）；下架（F）",
                "example": "http://baidu.com/1.png"
              }
            },
            "required": [
              "itemId"
            ]
          }
        },
        "required": [
          "orderId",
          "itemNum",
          "status",
          "total",
          "item"
        ],
        "description": "订单商品项"
      },
      "minItems": 1,
      "description": "订单商品项列表"
    },
    "manual": {
      "type": "boolean",
      "description": "是否为手动新增订单: 1为是, 0为不是"
    }
  },
  "required": [
    "order",
    "orderItemList",
    "manual"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|order|[Order](#schemaorder)|true|none|订单|
|orderItemList|[[OrderItem](#schemaorderitem)]|true|none|订单商品项列表|
|manual|boolean|true|none|是否为手动新增订单: 1为是, 0为不是|

<h2 id="tocS_Order">Order</h2>
<!-- backwards compatibility -->
<a id="schemaorder"></a>
<a id="schema_Order"></a>
<a id="tocSorder"></a>
<a id="tocsorder"></a>

```json
{
  "title": "Order",
  "description": "订单",
  "type": "object",
  "required": [
    "orderId",
    "customerId",
    "payStatus",
    "processStatus",
    "billPro",
    "billCity",
    "billDistrict",
    "billAddr",
    "totalPrice",
    "billName"
  ],
  "properties": {
    "orderId": {
      "type": "integer",
      "description": "订单Id"
    },
    "customerId": {
      "type": "string",
      "example": "2020-07-05 12:00:00.000",
      "description": "买家id"
    },
    "payStatus": {
      "type": "string",
      "description": "付款状态: 已付款（P）未付款（W）"
    },
    "processStatus": {
      "type": "string",
      "description": "订单处理状态:未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close） "
    },
    "createDateTime": {
      "type": "string",
      "description": "订单创建时间"
    },
    "payDateTime": {
      "type": "string",
      "description": "订单付款时间"
    },
    "billName": {
      "type": "string",
      "description": "收件人姓名"
    },
    "billPro": {
      "type": "string",
      "example": "支付宝",
      "description": "收件所在省"
    },
    "billCity": {
      "type": "string",
      "example": "王五",
      "description": "收件所在市"
    },
    "billDistrict": {
      "type": "string",
      "description": "收件所在区"
    },
    "billAddr": {
      "type": "string",
      "example": "北京市",
      "description": "收件详细地址"
    },
    "totalPrice": {
      "type": "number",
      "description": "总付款"
    },
    "shippingCost": {
      "type": "number",
      "description": "运费"
    },
    "payMethod": {
      "type": "string",
      "description": "支付方式"
    },
    "note": {
      "type": "string",
      "description": "备注"
    }
  }
}

```

Order

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|orderId|integer|true|none|订单Id|
|customerId|string|true|none|买家id|
|payStatus|string|true|none|付款状态: 已付款（P）未付款（W）|
|processStatus|string|true|none|订单处理状态:未处理（N 意为not process）正在处理（P 意为processing）交易成功（D 意为done）交易关闭（C 意为close）|
|createDateTime|string|false|none|订单创建时间|
|payDateTime|string|false|none|订单付款时间|
|billName|string|true|none|收件人姓名|
|billPro|string|true|none|收件所在省|
|billCity|string|true|none|收件所在市|
|billDistrict|string|true|none|收件所在区|
|billAddr|string|true|none|收件详细地址|
|totalPrice|number|true|none|总付款|
|shippingCost|number|false|none|运费|
|payMethod|string|false|none|支付方式|
|note|string|false|none|备注|

<h2 id="tocS_warehouseResp">warehouseResp</h2>
<!-- backwards compatibility -->
<a id="schemawarehouseresp"></a>
<a id="schema_warehouseResp"></a>
<a id="tocSwarehouseresp"></a>
<a id="tocswarehouseresp"></a>

```json
{
  "type": "object",
  "properties": {
    "siteId": {
      "type": "string",
      "description": "所属主站编号"
    },
    "warehouseId": {
      "type": "string",
      "description": "库房编号"
    },
    "category": {
      "type": "object",
      "properties": {
        "categoryId": {
          "type": "string"
        },
        "name": {
          "type": "string"
        },
        "descn": {
          "type": "string"
        }
      },
      "description": "存储的种类"
    },
    "kindNumOfItem": {
      "type": "integer",
      "description": "存储的item的类别总数"
    },
    "totalSize": {
      "type": "integer",
      "description": "存储的Iten的总数量"
    },
    "maxSize": {
      "type": "integer",
      "description": "库房容量"
    }
  }
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|siteId|string|false|none|所属主站编号|
|warehouseId|string|false|none|库房编号|
|category|object|false|none|存储的种类|
|» categoryId|string|false|none|none|
|» name|string|false|none|none|
|» descn|string|false|none|none|
|kindNumOfItem|integer|false|none|存储的item的类别总数|
|totalSize|integer|false|none|存储的Iten的总数量|
|maxSize|integer|false|none|库房容量|

<h2 id="tocS_OrderBriefResp">OrderBriefResp</h2>
<!-- backwards compatibility -->
<a id="schemaorderbriefresp"></a>
<a id="schema_OrderBriefResp"></a>
<a id="tocSorderbriefresp"></a>
<a id="tocsorderbriefresp"></a>

```json
{
  "type": "object",
  "properties": {
    "orderId": {
      "type": "integer",
      "description": "订单id"
    },
    "billName": {
      "type": "string",
      "description": "收件人姓名"
    },
    "createDateTime": {
      "type": "string",
      "description": "订单创建时间"
    },
    "totalPrice": {
      "type": "integer",
      "description": "总价格"
    }
  },
  "required": [
    "orderId",
    "billName",
    "createDateTime",
    "totalPrice"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|orderId|integer|true|none|订单id|
|billName|string|true|none|收件人姓名|
|createDateTime|string|true|none|订单创建时间|
|totalPrice|integer|true|none|总价格|

<h2 id="tocS_TaskFormResp">TaskFormResp</h2>
<!-- backwards compatibility -->
<a id="schemataskformresp"></a>
<a id="schema_TaskFormResp"></a>
<a id="tocStaskformresp"></a>
<a id="tocstaskformresp"></a>

```json
{
  "type": "object",
  "properties": {
    "taskFormId": {
      "type": "integer",
      "description": "任务单id"
    },
    "subSiteId": {
      "type": "string",
      "description": "配送站id"
    },
    "status": {
      "type": "string",
      "description": "状态"
    },
    "shipTime": {
      "type": "string",
      "description": "发货时间"
    },
    "receiverName": {
      "type": "string",
      "description": "收件人姓名"
    },
    "receiverTel": {
      "type": "string",
      "description": "收件人电话"
    },
    "receiverAddress": {
      "type": "string",
      "description": "收货地址"
    },
    "orderItems": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "orderId": {
            "type": "integer",
            "description": "订单id"
          },
          "taskId": {
            "type": "any",
            "description": "任务单id"
          },
          "itemNum": {
            "type": "integer",
            "description": "商品数量",
            "minimum": 0,
            "maximum": 1000000,
            "exclusiveMinimum": true,
            "exclusiveMaximum": true
          },
          "status": {
            "type": "string",
            "description": "处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)"
          },
          "total": {
            "type": "number",
            "description": "单类商品付款总额"
          },
          "item": {
            "title": "Item",
            "description": "商品项",
            "type": "object",
            "properties": {
              "itemId": {
                "type": "string",
                "description": "商品id",
                "example": "可口可乐 330ml 罐装"
              },
              "categoryId": {
                "type": "string",
                "description": "大类id"
              },
              "name": {
                "type": "string",
                "description": "商品名称"
              },
              "descn": {
                "type": "string",
                "description": "文字描述"
              },
              "unitCost": {
                "type": "number",
                "format": "double",
                "description": "原价",
                "example": 3,
                "minimum": 0
              },
              "listPrice": {
                "type": "number",
                "format": "double",
                "description": "售价",
                "example": 3,
                "minimum": 0,
                "exclusiveMinimum": true
              },
              "imgUrl": {
                "type": "string",
                "description": "图片url",
                "example": "易拉罐 红罐 可乐 可口可乐"
              },
              "status": {
                "type": "string",
                "description": "商品状态：上架（P）；下架（F）",
                "example": "http://baidu.com/1.png"
              }
            },
            "required": [
              "itemId"
            ]
          }
        },
        "required": [
          "orderId",
          "itemNum",
          "status",
          "total",
          "item"
        ],
        "description": "订单商品项"
      }
    }
  },
  "description": "任务单",
  "required": [
    "taskFormId",
    "subSiteId",
    "status",
    "shipTime",
    "receiverName",
    "receiverAddress",
    "receiverTel",
    "orderItems"
  ]
}

```

任务单

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|taskFormId|integer|true|none|任务单id|
|subSiteId|string|true|none|配送站id|
|status|string|true|none|状态|
|shipTime|string|true|none|发货时间|
|receiverName|string|true|none|收件人姓名|
|receiverTel|string|true|none|收件人电话|
|receiverAddress|string|true|none|收货地址|
|orderItems|[[OrderItem](#schemaorderitem)]|true|none|[订单商品项]|

<h2 id="tocS_Supplier">Supplier</h2>
<!-- backwards compatibility -->
<a id="schemasupplier"></a>
<a id="schema_Supplier"></a>
<a id="tocSsupplier"></a>
<a id="tocssupplier"></a>

```json
{
  "title": "Supplier",
  "description": "供应商",
  "type": "object",
  "required": [
    "supplierId",
    "brandName",
    "tel",
    "email",
    "province",
    "city",
    "district",
    "addr",
    "managerName"
  ],
  "properties": {
    "supplierId": {
      "type": "string",
      "description": "供应商编号"
    },
    "brandName": {
      "type": "string",
      "description": "供应商品牌名",
      "example": "李四"
    },
    "managerName": {
      "type": "string",
      "description": "联系人姓名"
    },
    "tel": {
      "type": "string",
      "description": "联系人电话",
      "example": "10000"
    },
    "email": {
      "type": "string",
      "description": "供应商邮箱"
    },
    "province": {
      "type": "string",
      "description": "供应商总部所在省"
    },
    "city": {
      "type": "string",
      "description": "供应商总部所在市"
    },
    "district": {
      "type": "string",
      "description": "供应商总部所在区"
    },
    "addr": {
      "type": "string",
      "description": "供应商总部详细地址"
    }
  }
}

```

Supplier

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|supplierId|string|true|none|供应商编号|
|brandName|string|true|none|供应商品牌名|
|managerName|string|true|none|联系人姓名|
|tel|string|true|none|联系人电话|
|email|string|true|none|供应商邮箱|
|province|string|true|none|供应商总部所在省|
|city|string|true|none|供应商总部所在市|
|district|string|true|none|供应商总部所在区|
|addr|string|true|none|供应商总部详细地址|

<h2 id="tocS_MainSite">MainSite</h2>
<!-- backwards compatibility -->
<a id="schemamainsite"></a>
<a id="schema_MainSite"></a>
<a id="tocSmainsite"></a>
<a id="tocsmainsite"></a>

```json
{
  "type": "object",
  "properties": {
    "mainsiteId": {
      "type": "string",
      "description": "主站id"
    },
    "province": {
      "type": "string",
      "description": "主站所在省/直辖市"
    },
    "city": {
      "type": "string",
      "description": "主站所在城市"
    },
    "district": {
      "type": "string",
      "description": "主站所在区/县"
    },
    "longitude": {
      "type": "number",
      "description": "经度"
    },
    "latitude": {
      "type": "number",
      "description": "纬度"
    },
    "addr": {
      "type": "string",
      "description": "主站详细地址信息（具体的街道门牌号）"
    }
  },
  "required": [
    "mainsiteId",
    "province",
    "city",
    "district",
    "longitude",
    "latitude",
    "addr"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|mainsiteId|string|true|none|主站id|
|province|string|true|none|主站所在省/直辖市|
|city|string|true|none|主站所在城市|
|district|string|true|none|主站所在区/县|
|longitude|number|true|none|经度|
|latitude|number|true|none|纬度|
|addr|string|true|none|主站详细地址信息（具体的街道门牌号）|

<h2 id="tocS_ItemInventoryResp">ItemInventoryResp</h2>
<!-- backwards compatibility -->
<a id="schemaiteminventoryresp"></a>
<a id="schema_ItemInventoryResp"></a>
<a id="tocSiteminventoryresp"></a>
<a id="tocsiteminventoryresp"></a>

```json
{
  "type": "object",
  "properties": {
    "item": {
      "title": "Item",
      "description": "商品项",
      "type": "object",
      "properties": {
        "itemId": {
          "type": "string",
          "description": "商品id",
          "example": "可口可乐 330ml 罐装"
        },
        "categoryId": {
          "type": "string",
          "description": "大类id"
        },
        "name": {
          "type": "string",
          "description": "商品名称"
        },
        "descn": {
          "type": "string",
          "description": "文字描述"
        },
        "unitCost": {
          "type": "number",
          "format": "double",
          "description": "原价",
          "example": 3,
          "minimum": 0
        },
        "listPrice": {
          "type": "number",
          "format": "double",
          "description": "售价",
          "example": 3,
          "minimum": 0,
          "exclusiveMinimum": true
        },
        "imgUrl": {
          "type": "string",
          "description": "图片url",
          "example": "易拉罐 红罐 可乐 可口可乐"
        },
        "status": {
          "type": "string",
          "description": "商品状态：上架（P）；下架（F）",
          "example": "http://baidu.com/1.png"
        }
      },
      "required": [
        "itemId"
      ]
    },
    "inventory": {
      "type": "integer",
      "description": "商品库存"
    },
    "warehouseId": {
      "type": "string",
      "description": "商品所在库房"
    }
  },
  "description": "商品项",
  "required": [
    "warehouseId",
    "item",
    "inventory"
  ]
}

```

商品项

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|item|[Item](#schemaitem)|true|none|商品项|
|inventory|integer|true|none|商品库存|
|warehouseId|string|true|none|商品所在库房|

<h2 id="tocS_ReturnForm">ReturnForm</h2>
<!-- backwards compatibility -->
<a id="schemareturnform"></a>
<a id="schema_ReturnForm"></a>
<a id="tocSreturnform"></a>
<a id="tocsreturnform"></a>

```json
{
  "type": "object",
  "properties": {
    "formId": {
      "type": "integer",
      "description": "退换货单的编号"
    },
    "orderId": {
      "type": "integer",
      "description": "对应的订单编号"
    },
    "isChange": {
      "type": "integer",
      "description": "1表示换货 ，0表示退货"
    },
    "item": {
      "title": "Item",
      "description": "商品项",
      "type": "object",
      "properties": {
        "itemId": {
          "type": "string",
          "description": "商品id",
          "example": "可口可乐 330ml 罐装"
        },
        "categoryId": {
          "type": "string",
          "description": "大类id"
        },
        "name": {
          "type": "string",
          "description": "商品名称"
        },
        "descn": {
          "type": "string",
          "description": "文字描述"
        },
        "unitCost": {
          "type": "number",
          "format": "double",
          "description": "原价",
          "example": 3,
          "minimum": 0
        },
        "listPrice": {
          "type": "number",
          "format": "double",
          "description": "售价",
          "example": 3,
          "minimum": 0,
          "exclusiveMinimum": true
        },
        "imgUrl": {
          "type": "string",
          "description": "图片url",
          "example": "易拉罐 红罐 可乐 可口可乐"
        },
        "status": {
          "type": "string",
          "description": "商品状态：上架（P）；下架（F）",
          "example": "http://baidu.com/1.png"
        }
      },
      "required": [
        "itemId"
      ]
    },
    "itemNum": {
      "type": "integer",
      "description": "退换货商品的数量"
    },
    "returnMoney": {
      "type": "number",
      "description": "退款金额（换货无需退款）"
    },
    "applyTime": {
      "type": "string",
      "description": "申请时间"
    },
    "processTime": {
      "type": "string",
      "description": "处理时间"
    },
    "processStatus": {
      "type": "string",
      "description": "处理状态，未处理（N）已处理（P）进行中（I ing）退/换货成功（Y）"
    },
    "reason": {
      "type": "string",
      "description": "申请原因"
    }
  },
  "required": [
    "formId",
    "orderId",
    "isChange",
    "item",
    "itemNum",
    "returnMoney",
    "applyTime",
    "processTime",
    "processStatus",
    "reason"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|formId|integer|true|none|退换货单的编号|
|orderId|integer|true|none|对应的订单编号|
|isChange|integer|true|none|1表示换货 ，0表示退货|
|item|[Item](#schemaitem)|true|none|商品项|
|itemNum|integer|true|none|退换货商品的数量|
|returnMoney|number|true|none|退款金额（换货无需退款）|
|applyTime|string|true|none|申请时间|
|processTime|string|true|none|处理时间|
|processStatus|string|true|none|处理状态，未处理（N）已处理（P）进行中（I ing）退/换货成功（Y）|
|reason|string|true|none|申请原因|

<h2 id="tocS_SubCategory">SubCategory</h2>
<!-- backwards compatibility -->
<a id="schemasubcategory"></a>
<a id="schema_SubCategory"></a>
<a id="tocSsubcategory"></a>
<a id="tocssubcategory"></a>

```json
{
  "title": "SubCategory",
  "description": "商品二级分类",
  "type": "object",
  "properties": {
    "subCategoryName": {
      "type": "string",
      "description": "二级分类名称",
      "example": "Rice"
    },
    "categoryId": {
      "type": "integer",
      "format": "int64",
      "description": "一级分类id",
      "example": 1
    },
    "description": {
      "type": "string",
      "description": "二级分类描述",
      "example": "大米"
    }
  },
  "required": [
    "subCategoryName",
    "categoryId",
    "description"
  ]
}

```

SubCategory

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|subCategoryName|string|true|none|二级分类名称|
|categoryId|integer(int64)|true|none|一级分类id|
|description|string|true|none|二级分类描述|

<h2 id="tocS_TaskForm">TaskForm</h2>
<!-- backwards compatibility -->
<a id="schemataskform"></a>
<a id="schema_TaskForm"></a>
<a id="tocStaskform"></a>
<a id="tocstaskform"></a>

```json
{
  "type": "object",
  "properties": {
    "taskId": {
      "type": "integer",
      "description": "任务单编号"
    },
    "orderId": {
      "type": "integer",
      "description": "订单编号"
    },
    "subSiteId": {
      "type": "string",
      "description": "处理该任务单的配送站编号"
    },
    "courier": {
      "type": "string",
      "description": "配送员"
    },
    "status": {
      "type": "string",
      "description": "任务单的状态，缺货等待调货（W），未发出（U），运输中（O），未配送（N），已签收（Y）"
    },
    "deliveryDateTime": {
      "type": "string",
      "description": "发货时间"
    },
    "shipName": {
      "type": "string",
      "description": "寄件人姓名"
    },
    "shipTel": {
      "type": "string",
      "description": "寄件人电话"
    },
    "shipPro": {
      "type": "string",
      "description": "寄件人所在省（直辖市）"
    },
    "shipCity": {
      "type": "string",
      "description": "寄件人所在市"
    },
    "shipDis": {
      "type": "string",
      "description": "寄件人所在区"
    },
    "shipAddr": {
      "type": "string",
      "description": "寄件人详细街道地址"
    },
    "billName": {
      "type": "string",
      "description": "收件人姓名"
    },
    "billTel": {
      "type": "string",
      "description": "收件人电话"
    },
    "billPro": {
      "type": "string",
      "description": "收件人所在省"
    },
    "billCity": {
      "type": "string",
      "description": "收件人所在市"
    },
    "billDis": {
      "type": "string",
      "description": "收件人所在区"
    },
    "billAddr": {
      "type": "string",
      "description": "收件人详细街道地址"
    },
    "totalPrice": {
      "type": "number",
      "description": "该任务单商品总金额"
    },
    "note": {
      "type": "string",
      "description": "备注"
    },
    "orderItems": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "orderId": {
            "type": "integer",
            "description": "订单id"
          },
          "taskId": {
            "type": "any",
            "description": "任务单id"
          },
          "itemNum": {
            "type": "integer",
            "description": "商品数量",
            "minimum": 0,
            "maximum": 1000000,
            "exclusiveMinimum": true,
            "exclusiveMaximum": true
          },
          "status": {
            "type": "string",
            "description": "处理状态: 缺货（O 意为stockout）已处理/已分配给任务单(P)"
          },
          "total": {
            "type": "number",
            "description": "单类商品付款总额"
          },
          "item": {
            "title": "Item",
            "description": "商品项",
            "type": "object",
            "properties": {
              "itemId": {
                "type": "string",
                "description": "商品id",
                "example": "可口可乐 330ml 罐装"
              },
              "categoryId": {
                "type": "string",
                "description": "大类id"
              },
              "name": {
                "type": "string",
                "description": "商品名称"
              },
              "descn": {
                "type": "string",
                "description": "文字描述"
              },
              "unitCost": {
                "type": "number",
                "format": "double",
                "description": "原价",
                "example": 3,
                "minimum": 0
              },
              "listPrice": {
                "type": "number",
                "format": "double",
                "description": "售价",
                "example": 3,
                "minimum": 0,
                "exclusiveMinimum": true
              },
              "imgUrl": {
                "type": "string",
                "description": "图片url",
                "example": "易拉罐 红罐 可乐 可口可乐"
              },
              "status": {
                "type": "string",
                "description": "商品状态：上架（P）；下架（F）",
                "example": "http://baidu.com/1.png"
              }
            },
            "required": [
              "itemId"
            ]
          }
        },
        "required": [
          "orderId",
          "itemNum",
          "status",
          "total",
          "item"
        ],
        "description": "订单商品项"
      },
      "description": "任务单中的商品项"
    }
  },
  "required": [
    "taskId",
    "orderId",
    "subSiteId",
    "courier",
    "status",
    "deliveryDateTime",
    "shipName",
    "shipTel",
    "shipPro",
    "shipCity",
    "shipDis",
    "shipAddr",
    "billName",
    "billTel",
    "billCity",
    "billPro",
    "billDis",
    "billAddr",
    "totalPrice",
    "note",
    "orderItems"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|taskId|integer|true|none|任务单编号|
|orderId|integer|true|none|订单编号|
|subSiteId|string|true|none|处理该任务单的配送站编号|
|courier|string|true|none|配送员|
|status|string|true|none|任务单的状态，缺货等待调货（W），未发出（U），运输中（O），未配送（N），已签收（Y）|
|deliveryDateTime|string|true|none|发货时间|
|shipName|string|true|none|寄件人姓名|
|shipTel|string|true|none|寄件人电话|
|shipPro|string|true|none|寄件人所在省（直辖市）|
|shipCity|string|true|none|寄件人所在市|
|shipDis|string|true|none|寄件人所在区|
|shipAddr|string|true|none|寄件人详细街道地址|
|billName|string|true|none|收件人姓名|
|billTel|string|true|none|收件人电话|
|billPro|string|true|none|收件人所在省|
|billCity|string|true|none|收件人所在市|
|billDis|string|true|none|收件人所在区|
|billAddr|string|true|none|收件人详细街道地址|
|totalPrice|number|true|none|该任务单商品总金额|
|note|string|true|none|备注|
|orderItems|[[OrderItem](#schemaorderitem)]|true|none|任务单中的商品项|

<h2 id="tocS_ItemWithId">ItemWithId</h2>
<!-- backwards compatibility -->
<a id="schemaitemwithid"></a>
<a id="schema_ItemWithId"></a>
<a id="tocSitemwithid"></a>
<a id="tocsitemwithid"></a>

```json
{
  "title": "Item",
  "description": "商品",
  "type": "object",
  "properties": {
    "itemId": {
      "type": "integer",
      "format": "int64",
      "description": "商品id",
      "example": 1
    },
    "ItemName": {
      "type": "string",
      "description": "商品名称",
      "example": "可口可乐 330ml 罐装"
    },
    "subCategoryId": {
      "type": "integer",
      "format": "int64",
      "description": "二级分类id",
      "example": 1
    },
    "supplierId": {
      "type": "integer",
      "format": "int64",
      "description": "供应商id",
      "example": 1
    },
    "ItemOriginalprice": {
      "type": "number",
      "format": "double",
      "description": "原价",
      "example": 3
    },
    "Itemprice": {
      "type": "number",
      "format": "double",
      "description": "售价",
      "example": 3
    },
    "ItemDescription": {
      "type": "string",
      "description": "文字描述",
      "example": "易拉罐 红罐 可乐 可口可乐"
    },
    "ItemPicture": {
      "type": "string",
      "description": "商品图片url",
      "example": "http://baidu.com/1.png"
    },
    "ItemStatus": {
      "type": "string",
      "default": "商品状态",
      "example": "in stock"
    }
  },
  "required": [
    "itemId",
    "ItemName",
    "subCategoryId",
    "supplierId",
    "ItemOriginalprice",
    "Itemprice",
    "ItemDescription",
    "ItemPicture",
    "ItemStatus"
  ]
}

```

Item

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|itemId|integer(int64)|true|none|商品id|
|ItemName|string|true|none|商品名称|
|subCategoryId|integer(int64)|true|none|二级分类id|
|supplierId|integer(int64)|true|none|供应商id|
|ItemOriginalprice|number(double)|true|none|原价|
|Itemprice|number(double)|true|none|售价|
|ItemDescription|string|true|none|文字描述|
|ItemPicture|string|true|none|商品图片url|
|ItemStatus|string|true|none|none|

<h2 id="tocS_ItemCheckinResp">ItemCheckinResp</h2>
<!-- backwards compatibility -->
<a id="schemaitemcheckinresp"></a>
<a id="schema_ItemCheckinResp"></a>
<a id="tocSitemcheckinresp"></a>
<a id="tocsitemcheckinresp"></a>

```json
{
  "type": "object",
  "properties": {
    "type": {
      "type": "integer",
      "description": "1表示补货，2表示调货，3表示退货，4表示换货"
    },
    "typeDesc": {
      "type": "string",
      "description": "对type属性的描述"
    },
    "formId": {
      "type": "integer",
      "description": "例如：调货的调货单编号"
    },
    "itemId": {
      "type": "string",
      "description": "货物id"
    },
    "itemNum": {
      "type": "integer",
      "description": "货物数量"
    },
    "recordId": {
      "type": "integer",
      "description": "入库记录编号"
    }
  },
  "required": [
    "recordId",
    "type",
    "typeDesc",
    "formId",
    "itemId",
    "itemNum"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|type|integer|true|none|1表示补货，2表示调货，3表示退货，4表示换货|
|typeDesc|string|true|none|对type属性的描述|
|formId|integer|true|none|例如：调货的调货单编号|
|itemId|string|true|none|货物id|
|itemNum|integer|true|none|货物数量|
|recordId|integer|true|none|入库记录编号|

<h2 id="tocS_CustomerWithId">CustomerWithId</h2>
<!-- backwards compatibility -->
<a id="schemacustomerwithid"></a>
<a id="schema_CustomerWithId"></a>
<a id="tocScustomerwithid"></a>
<a id="tocscustomerwithid"></a>

```json
{
  "title": "Customer",
  "description": "客户",
  "type": "object",
  "required": [
    "customerId",
    "name",
    "tel",
    "email",
    "addr",
    "district",
    "city",
    "province"
  ],
  "properties": {
    "customerId": {
      "type": "integer",
      "format": "int64",
      "description": "客户id",
      "example": 1
    },
    "name": {
      "type": "string",
      "description": "客户姓名",
      "example": "张三"
    },
    "tel": {
      "type": "string",
      "description": "客户邮箱",
      "example": "10086"
    },
    "email": {
      "type": "string",
      "description": "客户电话",
      "example": "10001@qq.com"
    },
    "addr": {
      "type": "string",
      "description": "客户详细地址",
      "example": "北京市东城区王府井大街1号"
    },
    "district": {
      "type": "string",
      "description": "客户所在行政区区"
    },
    "city": {
      "type": "string",
      "description": "客户所在市"
    },
    "province": {
      "type": "string",
      "description": "所在省"
    }
  }
}

```

Customer

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|customerId|integer(int64)|true|none|客户id|
|name|string|true|none|客户姓名|
|tel|string|true|none|客户邮箱|
|email|string|true|none|客户电话|
|addr|string|true|none|客户详细地址|
|district|string|true|none|客户所在行政区区|
|city|string|true|none|客户所在市|
|province|string|true|none|所在省|

<h2 id="tocS_ItemInventoryDetailResp">ItemInventoryDetailResp</h2>
<!-- backwards compatibility -->
<a id="schemaiteminventorydetailresp"></a>
<a id="schema_ItemInventoryDetailResp"></a>
<a id="tocSiteminventorydetailresp"></a>
<a id="tocsiteminventorydetailresp"></a>

```json
{
  "type": "object",
  "properties": {
    "item": {
      "title": "Item",
      "description": "商品项",
      "type": "object",
      "properties": {
        "itemId": {
          "type": "string",
          "description": "商品id",
          "example": "可口可乐 330ml 罐装"
        },
        "categoryId": {
          "type": "string",
          "description": "大类id"
        },
        "name": {
          "type": "string",
          "description": "商品名称"
        },
        "descn": {
          "type": "string",
          "description": "文字描述"
        },
        "unitCost": {
          "type": "number",
          "format": "double",
          "description": "原价",
          "example": 3,
          "minimum": 0
        },
        "listPrice": {
          "type": "number",
          "format": "double",
          "description": "售价",
          "example": 3,
          "minimum": 0,
          "exclusiveMinimum": true
        },
        "imgUrl": {
          "type": "string",
          "description": "图片url",
          "example": "易拉罐 红罐 可乐 可口可乐"
        },
        "status": {
          "type": "string",
          "description": "商品状态：上架（P）；下架（F）",
          "example": "http://baidu.com/1.png"
        }
      },
      "required": [
        "itemId"
      ]
    },
    "totalInventory": {
      "type": "integer",
      "description": "全部主站中该商品的总库存"
    },
    "mainsiteInventoryList": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "mainsiteId": {
            "type": "string",
            "description": "主站编号"
          },
          "itemInventory": {
            "type": "integer",
            "description": "该主站中该商品的库存"
          }
        }
      },
      "description": "各主站该商品的库存"
    }
  }
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|item|[Item](#schemaitem)|false|none|商品项|
|totalInventory|integer|false|none|全部主站中该商品的总库存|
|mainsiteInventoryList|[object]|false|none|各主站该商品的库存|
|» mainsiteId|string|false|none|主站编号|
|» itemInventory|integer|false|none|该主站中该商品的库存|

<h2 id="tocS_SiteIOCheckoutResp">SiteIOCheckoutResp</h2>
<!-- backwards compatibility -->
<a id="schemasiteiocheckoutresp"></a>
<a id="schema_SiteIOCheckoutResp"></a>
<a id="tocSsiteiocheckoutresp"></a>
<a id="tocssiteiocheckoutresp"></a>

```json
{
  "type": "object",
  "properties": {
    "recordId": {
      "type": "integer"
    },
    "timeStamp": {
      "type": "string"
    },
    "warehouseId": {
      "type": "string"
    },
    "item": {
      "type": "object",
      "properties": {
        "itemId": {
          "type": "string"
        },
        "name": {
          "type": "string"
        },
        "categoryId": {
          "type": "string"
        },
        "categoryName": {
          "type": "string"
        },
        "unitPrice": {
          "type": "integer"
        },
        "listPrice": {
          "type": "integer"
        },
        "descn": {
          "type": "string"
        },
        "imgUrl": {
          "type": "string"
        }
      }
    },
    "itemNum": {
      "type": "integer"
    },
    "type": {
      "type": "integer",
      "description": "1：退货给供应商，2：调货出库，3：发货出库"
    },
    "typeDesc": {
      "type": "string",
      "description": "对tpe属性的描述，如“调货出库”"
    },
    "formId": {
      "type": "integer",
      "description": "相关表单编号"
    },
    "itemDest": {
      "type": "string",
      "description": "货物出处，例如：其他主站的编号"
    },
    "approvalStatus": {
      "type": "string"
    },
    "approver": {
      "type": "string"
    },
    "warehouseOptionalList": {
      "type": "array",
      "items": {
        "type": "string",
        "description": "warehouseId"
      },
      "description": "可供选择的库房列表"
    }
  },
  "required": [
    "warehouseOptionalList"
  ]
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|recordId|integer|false|none|none|
|timeStamp|string|false|none|none|
|warehouseId|string|false|none|none|
|item|object|false|none|none|
|» itemId|string|false|none|none|
|» name|string|false|none|none|
|» categoryId|string|false|none|none|
|» categoryName|string|false|none|none|
|» unitPrice|integer|false|none|none|
|» listPrice|integer|false|none|none|
|» descn|string|false|none|none|
|» imgUrl|string|false|none|none|
|itemNum|integer|false|none|none|
|type|integer|false|none|1：退货给供应商，2：调货出库，3：发货出库|
|typeDesc|string|false|none|对tpe属性的描述，如“调货出库”|
|formId|integer|false|none|相关表单编号|
|itemDest|string|false|none|货物出处，例如：其他主站的编号|
|approvalStatus|string|false|none|none|
|approver|string|false|none|none|
|warehouseOptionalList|[string]|true|none|可供选择的库房列表|

<h2 id="tocS_warehouseDetailResp">warehouseDetailResp</h2>
<!-- backwards compatibility -->
<a id="schemawarehousedetailresp"></a>
<a id="schema_warehouseDetailResp"></a>
<a id="tocSwarehousedetailresp"></a>
<a id="tocswarehousedetailresp"></a>

```json
{
  "type": "object",
  "properties": {
    "warehouseId": {
      "type": "string",
      "description": "库房编号"
    },
    "mainSiteId": {
      "type": "string",
      "description": "所属主站编号"
    },
    "mainSiteName": {
      "type": "string",
      "description": "所属主站名"
    },
    "category": {
      "title": "Category",
      "description": "商品分类",
      "type": "object",
      "properties": {
        "categoryId": {
          "type": "string",
          "description": "分类名称",
          "example": "Food"
        },
        "descn": {
          "type": "string",
          "description": "分类描述",
          "example": "吃"
        },
        "name": {
          "type": "string",
          "description": "分类名"
        }
      },
      "required": [
        "categoryId",
        "descn",
        "name"
      ]
    },
    "itemList": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "item": {
            "title": "Item",
            "description": "商品项",
            "type": "object",
            "properties": {
              "itemId": {
                "type": "string",
                "description": "商品id",
                "example": "可口可乐 330ml 罐装"
              },
              "categoryId": {
                "type": "string",
                "description": "大类id"
              },
              "name": {
                "type": "string",
                "description": "商品名称"
              },
              "descn": {
                "type": "string",
                "description": "文字描述"
              },
              "unitCost": {
                "type": "number",
                "format": "double",
                "description": "原价",
                "example": 3,
                "minimum": 0
              },
              "listPrice": {
                "type": "number",
                "format": "double",
                "description": "售价",
                "example": 3,
                "minimum": 0,
                "exclusiveMinimum": true
              },
              "imgUrl": {
                "type": "string",
                "description": "图片url",
                "example": "易拉罐 红罐 可乐 可口可乐"
              },
              "status": {
                "type": "string",
                "description": "商品状态：上架（P）；下架（F）",
                "example": "http://baidu.com/1.png"
              }
            },
            "required": [
              "itemId"
            ]
          },
          "inventory": {
            "type": "integer",
            "description": "商品库存"
          },
          "warehouseId": {
            "type": "string",
            "description": "商品所在库房"
          }
        },
        "description": "商品项",
        "required": [
          "warehouseId",
          "item",
          "inventory"
        ]
      },
      "description": "存储的Item列表"
    }
  },
  "required": [
    "itemList"
  ],
  "description": "库房详细信息"
}

```

库房详细信息

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|warehouseId|string|false|none|库房编号|
|mainSiteId|string|false|none|所属主站编号|
|mainSiteName|string|false|none|所属主站名|
|category|[Category](#schemacategory)|false|none|商品分类|
|itemList|[[ItemInventoryResp](#schemaiteminventoryresp)]|true|none|存储的Item列表|

<h2 id="tocS_CategoryWithId">CategoryWithId</h2>
<!-- backwards compatibility -->
<a id="schemacategorywithid"></a>
<a id="schema_CategoryWithId"></a>
<a id="tocScategorywithid"></a>
<a id="tocscategorywithid"></a>

```json
{
  "title": "Category",
  "description": "商品一级分类",
  "type": "object",
  "properties": {
    "categoryId": {
      "type": "integer",
      "format": "int64",
      "description": "一级分类id",
      "example": 1
    },
    "categoryName": {
      "type": "string",
      "description": "一级分类名称",
      "example": "Food"
    },
    "description": {
      "type": "string",
      "description": "一级分类描述",
      "example": "吃"
    }
  },
  "required": [
    "categoryId",
    "categoryName",
    "description"
  ]
}

```

Category

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|categoryId|integer(int64)|true|none|一级分类id|
|categoryName|string|true|none|一级分类名称|
|description|string|true|none|一级分类描述|

