# README file for web app

## REST API

There are several end points that can be tested for each service/facade:

### Login - UI

Using login page..


### Login - REST using -u

```
curl ... -u <userEmail>:<password>
```

g.e.:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/ -u Adam@rms.com:password
```

Note: successful login result header contains new generated sessionId token that can be reused as auth token in further requests.

## Here are some examples of CURLs for each end point: 

List of endpoints with URIs:


```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/
```

All entities can:

- get by id

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/<users|households|shoppingitems|shoppinglists>/{id}
```

- or list all

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/<users|households|shoppingitems|shoppinglists>
```

User:

 - list
 
```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/users
```

- register 

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Adam@rms.com:password -X GET http://localhost:8080/rms-web/rest/users/register --data '{"password":"password","email":"Test","firstName":"","lastName":""}'
```

- current authenticated user info

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -u Adam@rms.com:password -X GET http://localhost:8080/rms-web/rest/users/current --data '{"password":"password","email":"Test","firstName":"","lastName":""}'
```


Households:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/households
```

Shopping Items:

- list all:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/shoppingitems
```

- create:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST http://localhost:8080/rms-web/rest/shoppingitems/create -u Adam@rms.com:password --data '{"id":0,"name":"xyz","quantity":5,"buyerId":1,"shoppingListId":1}'

```

- buy:
    - (returns true if not bought yet or false if already bought before this acquisition) 

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST http://localhost:8080/rms-web/rest/shoppingitems/buy/{itemId} -u Adam@rms.com:password
```



Shopping Lists:

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/rms-web/rest/shoppinglists
```
