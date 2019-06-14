# 技术栈：
## spring boot
## redis 集成
### jedis连接池 集成
## mq集成
## 后台参数校验
## 全局异常
## 两次md5加密 
    MD5Util.java
    两次md5加密 input-->from-->db
## 分布式session
### 基于Redis存储token 实现分布式session
```java
    // 登录时
    //生成token
    String token  = UUIDUtil.uuid();
    addCookie(response, token, user);
    // 设置token
    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
    		redisService.set(MiaoshaUserKey.token, token, user);
    		Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
    		cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
    		cookie.setPath("/");
    		response.addCookie(cookie);
    	}

    //登录校验时 获取token
    private String getCookieValue(HttpServletRequest request, String cookiName) {
    		Cookie[]  cookies = request.getCookies();
    		if(cookies == null || cookies.length <= 0){
    			return null;
    		}
    		for(Cookie cookie : cookies) {
    			if(cookie.getName().equals(cookiName)) {
    				return cookie.getValue();
    			}
    		}
    		return null;
    	}
    //再用token拼接key 去redis中取出User对象
    //再延长token缓存期限
    addCookie(response, token, user);
```
    

## 缓存
## jmeter 测试
## 限流
## 隐藏接口
## 验证码 34 +9
## 异步处理（mq）秒杀等