<!--
 * @Author: Karigen B
 * @Date: 2022-07-25 15:20:27
 * @LastEditors: Karigen B
 * @LastEditTime: 2022-07-25 15:53:50
 * @Description: 
 * @FilePath: \Desktop\xxx.md
-->

# to-do-list

## 项目规划

- 登录和注册页面
- **功能**
    - 全部task
    - CRUDtask
    - 朋友圈(图片?)
- **task结构**
    - 任务名
    - 任务内容(备注)
    - 时间(ddl)
    - 项目类型()
    - 是否周期
    - 优先级(置顶)
    - **统计(已完成,一共,未完成)**

## 接口规范

```http request
# 注册--post
http://localhost:8080/user/register
# 登陆--post
http://localhost:8080/user/login

# 加任务--post
http://localhost:8080/task/add
# 减任务--delete
http://localhost:8080/task/delete
# 减已完成任务--deleteall
http://localhost:8080/task/deleteall
# 查任务--get
http://localhost:8080/task/get
# 改任务--put
http://localhost:8080/task/update

# 查blog
http://localhost:8080/blog/get
# 加blog
http://localhost:8080/blog/add
# 找用户
http://localhost:8080/blog/users
```

- 请求

```json
[
  /*注册*/
  {
    "username": "?",
    "password": "?",
    "email": "?"
  },
  /*登陆*/
  {
    "username": "?",
    "password": "?"
  },
  /*加任务*/
  {
    "userid": "?",
    "task-name": "?"
  },
  /*减任务*/
  {
    "taskid": "?"
  },
  /*减已完成任务*/
  {
    "taskid": []
  },
  /*查任务*/
  {
    "userid": "?"
  },
  /*改任务*/
  {
    "taskid": "?",
    "description": "?",
    "deadline": "?"
    "finish": true
  },
  /*查blog*/
  {
    "userid": 1
  },
  /*加blog*/
  {
    "userid": 1,
    "context": "?"
  },
  /*找用户*/
  {
    "myuserid": "?",
    "searchingusername": "?"
  }
]
```

- 响应成功

```json
[
  /*注册*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": null
  },
  /*登陆*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": {
      "userid": 1,
      "username": "?"
    }
  },
  /*加任务*/
  {
    "state": true,
    "code": "00000",
    "message": null,
     "data": {
        "tasks": [
        {
          /*task1*/
        },
        {
          /*task2*/
        }
        /*...*/
      ]
    }
  },
  /*减任务*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": {
        "tasks": [
        {
          /*task1*/
        },
        {
          /*task2*/
        }
        /*...*/
      ]
    }
  },
  /*减已完成任务*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": {
        "tasks": [
        {
          /*task1*/
        },
        {
          /*task2*/
        }
        /*...*/
      ]
    }
  },
  /*查任务*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": {
      "tasks": [
        {
          /*task1*/
        },
        {
          /*task2*/
        }
        /*...*/
      ],
      "unfinished": 1,
      /*未完成*/
      "finished": 2,
      /*已完成*/
      "all": 3,
      /*总数*/
      "isExpire": 4
      /*已到达截止时间*/
    }
  },
  /*改任务*/
  {
    "state": true,
    "code": "00000",
    "message": null,
     "data": {
        "tasks": [
        {
          /*task1*/
        },
        {
          /*task2*/
        }
        /*...*/
      ]
    }
  },
  /*查blog*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": {
      "fans": 1,
      "count": 2,
      "follow": 3,
      "blogs": [
        {
          /*blog1*/
        },
        {
          /*blog2*/
        }
        /*...*/
      ]
    }
  },
  /*加blog*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": null
  },
  /*找用户+关注*/
  {
    "state": true,
    "code": "00000",
    "message": null,
    "data": {
      "userid": 1
    }
  }
]
```

- 响应失败

```json
[
  /*注册*/
  {
    "state": false,
    "code": "?",
    /*A0100 A0111 A0200*/
    "message": "?",
    /*用户注册错误 用户名已存在 用户登陆异常*/
    "data": null
  },
  /*登陆*/
  {
    "state": false,
    "code": "?",
    /*A0111 A0120 A0210*/
    "message": "?",
    /*用户名已存在 密码校验失败 用户密码错误*/
    "data": null
  },
  /*加任务*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  },
  /*减任务*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  },
  /*减已完成任务*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  },
  /*查任务*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  },
  /*改任务*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  },
  /*查blog*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  },
  /*加blog*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  },
  /*找用户+关注*/
  {
    "state": false,
    "code": "B0001",
    "message": "系统执行出错",
    "data": null
  }
]
```