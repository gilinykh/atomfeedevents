# atomfeed

Spring Boot server and client for atom feed syndication (https://github.com/ICT4H/atomfeed)

## Server
Queue events:
```
POST http://localhost:8080/feeds
Content-Type: application/json

{"title": "Create User", "url": "http://example.com", "category": "users", "content": "{\"username\":\"Bill\"}"}

###

POST http://localhost:8080/feeds
Content-Type: application/json

{"title": "Create User", "url": "http://example.com", "category": "users", "content": "{\"username\":\"John\"}"}

###

POST http://localhost:8080/feeds
Content-Type: application/json

{"title": "Create User", "url": "http://example.com", "category": "users", "content": "{\"username\":\"Sam\"}"}

###

POST http://localhost:8080/feeds
Content-Type: application/json

{"title": "Create User", "url": "http://example.com", "category": "users", "content": "{\"username\":\"Phil\"}"}

###

POST http://localhost:8080/feeds
Content-Type: application/json

{"title": "Create User", "url": "http://example.com", "category": "user", "content": "{\"username\":\"Tom\"}"}
```

Publish events:

```
PUT http://localhost:8080/feeds
```

Check created feed:

```
GET http://localhost:8080/feed/recent

HTTP/1.1 200 
Content-Type: application/atom+xml;charset=UTF-8
Content-Length: 1694
Date: Wed, 19 Jun 2019 16:11:54 GMT

<?xml version="1.0" encoding="UTF-8"?>
<feed xmlns="http://www.w3.org/2005/Atom">
  <title>Events</title>
  <link rel="self" type="application/atom+xml" href="http://localhost:8080/feed/recent" />
  <link rel="via" type="application/atom+xml" href="http://localhost:8080/feed/1" />
  
  <author>
    <name>AtomfeedStandalone</name>
  </author>
  <id>ff554579-a19f-4bf9-b245-440814b513d9+3</id>
  <generator uri="https://github.com/ICT4H/atomfeed">atomfeed-standalone</generator>
  <updated>2019-06-19T15:56:01Z</updated>
  <entry>
    <title>Create User</title>
    <category term="users" />
    <id>tag:atomfeed.ict4h.org:5a668ecd-2c88-41cc-a817-54bc2c912bc1</id>
    <updated>2019-06-19T15:49:23Z</updated>
    <published>2019-06-19T15:49:23Z</published>
    <content type="application/vnd.atomfeed+xml"><![CDATA[{"username":"Bill"}]]></content>
  </entry>
  <entry>
    <title>Create User</title>
    <category term="users" />
    <id>tag:atomfeed.ict4h.org:68f09ae9-900b-48a2-8502-daef0c7c6081</id>
    <updated>2019-06-19T15:52:53Z</updated>
    <published>2019-06-19T15:52:53Z</published>
    <content type="application/vnd.atomfeed+xml"><![CDATA[{"username":"John"}]]></content>
  </entry>
  <entry>
    <title>Create User</title>
    <category term="users" />
    <id>tag:atomfeed.ict4h.org:4a28ab06-2f11-4ad9-817a-1c90181b8682</id>
    <updated>2019-06-19T15:56:01Z</updated>
    <published>2019-06-19T15:56:01Z</published>
    <content type="application/vnd.atomfeed+xml"><![CDATA[{"username":"Sam"}]]></content>
  </entry>
  <entry>
    <title>Create User</title>
    <category term="users" />
    <id>tag:atomfeed.ict4h.org:75a16638-b469-4205-803a-c3100cde31a1</id>
    <updated>2019-06-19T15:56:01Z</updated>
    <published>2019-06-19T15:56:01Z</published>
    <content type="application/vnd.atomfeed+xml"><![CDATA[{"username":"Phil"}]]></content>
  </entry>
    <entry>
    <title>Create User</title>
    <category term="users" />
    <id>tag:atomfeed.ict4h.org:a65f46db-b846-484f-8c81-42d0babb6f17</id>
    <updated>2019-06-19T15:56:01Z</updated>
    <published>2019-06-19T15:56:01Z</published>
    <content type="application/vnd.atomfeed+xml"><![CDATA[{"username":"Tom"}]]></content>
  </entry>
</feed>
```

## Client

Process events:

```
POST http://localhost:8090/feeds/success
```
