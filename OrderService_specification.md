# Order Service
## 1. Add new order API
### 1.1 Path
> /order/add <br />
### 1.2 Method
> POST
### 1.3 Headers
> Access-Token: your access token from login
### 1.4 Cookies
> none
### 1.5 Parameters detail
> JSON:<br />
```json
{
   "address": "xxxx",
   "amount": xxx.xx,
   "receiver": "xxxx"
}
```

| Param Name | Type | Location | mandatory | memo |
| :---: | :---: | :---: | :---: | :---: |
| address | string | body | true | can not be blank |
| amount | double | body | true |  |
| receiver | string | body | true | the name of the receiver |

### 1.6 Return
> JSON:<br />

```json
{
    "retCode": xxxx,
    "retMsg": "xxxx"
}
```

> retCode: retMsg
>
> - 10000: successful
>
> - 30001: Address is null
>
> - 30002: Amount is less than 0.00
>
> - 30003: Receiver is null
>
> - 
>
>   

### 1.7 Example

xxxx



## 2. Order enable API

### 2.1 Path

> /order/:order_id/enable<br />

### 2.2 Method

> PUT

### 2.3 Headers

> Access-Token: your access token from login

### 2.4 Cookies

> none

### 2.5 Parameters detail

> 

| Param Name | Type   | Location      | mandatory | memo |
| ---------- | ------ | ------------- | --------- | ---- |
| orderId    | string | path variable | true      |      |

### 2.6 Return

> JSON:<br />

```json
{
    "retCode": xxxx,
    "retMsg": "xxxx",
}
```

> retCode: retMsg
>
> - 10000: success
> - 30004: orderId is invalid

### 2.7 Example

xxxx



## 3. Order disable API

### 3.1 Path

> /order/:order_id/disable<br />

### 3.2 Method

> PUT

### 3.3 Headers

> Access-Token: your access token from login

### 3.4 Cookies

> none

### 3.5 Parameters detail

> path

| Param Name | Type   | Location      | mandatory | memo |
| ---------- | ------ | ------------- | --------- | ---- |
| orderId    | string | path variable | true      |      |

### 3.6 Return

> JSON:<br />

```json
{
    "retCode": xxxx,
    "retMsg": "xxxx",
}
```

> retCode: retMsg
>
> - 10000: success
> - 30004: orderId is invalid

### 3.7 Example

xxxx



## 4. Order modify API

### 4.1 Path

> /order/:order_id/modify<br />

### 4.2 Method

> POST

### 4.3 Headers

> Access-Token: your access token from login

### 4.4 Cookies

> none

### 4.5 Parameters detail

> Path

| Param Name | Type   | Location      | mandatory | memo |
| ---------- | ------ | ------------- | --------- | ---- |
| orderId    | string | path variable | true      |      |

> JSON

| Param Name | Type   | Location | mandatory | memo                     |
| ---------- | ------ | -------- | --------- | ------------------------ |
| address    | string | body     | true      | can not be blank         |
| amount     | double | body     | true      |                          |
| receiver   | string | body     | true      | the name of the receiver |

### 4.6 Return

> JSON:<br />

```json
{
    "retCode": xxxx,
    "retMsg": "xxxx",
}
```

> retCode: retMsg
>
> - 10000: success
> - 30001: Address is null
> - 30002: Amount is less than 0.00
> - 30003: Receiver is null
> - 30004: orderId is invalid

### 4.7 Example

xxxx



## 5. Order search API

### 5.1 Path

> /order/search<br />

### 5.2 Method

> POST

### 5.3 Headers

> Access-Token: your access token from login

### 5.4 Cookies

> none

### 5.5 Parameters detail

> form-data

| Param Name | Type   | Location | mandatory | memo                    |
| ---------- | ------ | -------- | --------- | ----------------------- |
| orderId    | int    | body     | false     |                         |
| isPaid     | int    | body     | false     | 1- paid, 0- not paid    |
| address    | string | body     | false     |                         |
| amountLow  | double | body     | false     |                         |
| amountHigh | double | body     | false     |                         |
| receiver   | string | body     | false     |                         |
| status     | int    | body     | false     | 1- enabled, 0- disabled |

### 5.6 Return

> JSON:<br />

```json
{
    "retCode": xxxx,
    "retMsg": "xxxx",
    "data": [search result]
}
```

> retCode: retMsg
>
> - 10000: success
>
> - 30005: order id is NAN
>
> - 30006: isPaid is NAN
>
> - 30007: amountLow is NAN
>
> - 30008: amountHigh is NAN
>
> - 30009: status is NAN
>
> - 40000: Unknown error while querying
>
>   If no such order can be found in storage, the data in result will be "no data found".

### 5.7 Example

xxxx