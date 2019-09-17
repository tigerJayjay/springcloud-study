# springcloud-study
在学习过程中集成springcloud各个组件及配置


Ribbon调用过程:通过在spring的RestTemplate上添加注解@LoadBalanced，可以让RestTemplate使用LoadBalancerClient,每次通过RestTemplate调用会通过LoadBalancerInterceptor拦截执行,然后调用LoadBalancerClient的execute方法，然后在execute方法中通过调用IloadBalancer的chooseServer方法最终调用IRule的choose方法


Feign:	一种Web Service客户端。
	可拔插注解，支持Feign注解和JAX-RS注解。
	支持可插拔的HTTP编码器和解码器
	支持Ribbon的负载均衡
	支持HTTP请求和响应的压缩
FeignClient属性
	name:FeignClient名称，如果使用了Ribbon，name属性会作为为服务的名称，用于服务发现。
	url:一般用于调试，可以手动指定@FeignClient调用的地址
	decode404:当发生404错误时，如果该字段为true,会调用decoder进行解码，否则抛出FeignException
	configuration:Feign配置类，可以自定义Feign的Encoder,Decoder,LogLevel,Contract
	fallback：定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑fallback指定的类必须实现@FeignClient标记的接口。
	fallbackFactory：用于生成fallback类实例，通过这个属性可以实现每个接口通用的容错逻辑，减少重复的代码。
	path:定义当前FeignClient的统一前缀
Feign开启GZIP压缩
	feign.compression.request.enabled=true
	feign.compression.response.enabled=true
	feign.compression.request.mime-types=text/xml,application/xml,application/json
	feign.compression.request.min-request-size=2048


Hystrix
	通过客户端库对延迟和故障进行保护和控制，在一个复杂的分布式系统中停止级联故障；快速失败和迅速恢复；
	在合理的情况下回退和优雅的降级；开启近实时监控、告警和操作控制。

开启断路器模式     在Application类上添加@EnableHystrix注解
降级  		   在调用方法上添加@HystrixCommand(fallbackMethod="defaultUser") 降级方法需要与被注解方法完全一致（返回值类型及参数类型和个数），但是可以在参数中加Throwable，
		   将异常抛出

Hystrix Dashboard：仪表盘是根据一段时间内的请求情况生成的可视化面板，是每个HystrixCommand执行过程中的信息，可以反映
		   系统具体的运行情况。
搭建Hystrix仪表盘
	1.在producer工程中添加actuator依赖，并公开hystrix.stream端点
	2.在客户端添加FeignClient接口
	3.搭建Hystrix Dashboard工程
		添加dashboard依赖，开启应用，访问locahost:port/hystrix，按照页面中的提示访问producer的端点
异常处理  有一种类型的异常不会触发fallback，抛出HystrixBadRquestException
请求缓存
	1.初始化上下文：继承HandlerInterceptor，实现preHandle和afeterCompletion
		  在preHandle方法中初始化HystrixRequestContext.initializeContext();
		  在afterCompletion中关闭上下文 context.shutdown();
	继承方式：  继承HystrixCommand，重写getCacheKey方法，保证同一个请求返回同样的键值，清除缓存，调用HystrixRequestCache的
		  clean方法
	注解方式：@CacheResult @CacheRemove
Hystrix request collapser：可以将多个请求合并到一个线程中。
	1.和缓存一样，需要先实现Hystrix上下文的初始化和关闭。
	2.在需要合并请求的方法上添加@HystrixCollapser注解，包含以下属性：
		1.batchMethod="xxx"，实际调用的方法。
		2.collapserProperties=(@HystrixProperty{name="timerDelayInmilliseconds",value="1000)在多少毫秒内的请求需要合并
		3.scope：request,global request表示同一个请求范围内内，global表示不同请求全范围局内的请求
	3.在真实调用的方法上添加@HystrixCommand注解



Zuul	面向服务治理，服务编排，是从设备和网站到后端应用程序所有请求的前门，提供可配置的对外url和服务之间的映射关系

	简单例子
	1.在启动类上添加@EnableZuulProxy注解
	2.在properties文件中添加路由规则：
		zuul.routes.服务名.path=/client/**
		zuul.routes.服务名.serviceId=client-a
		将所有/client开头的url映射到client-a这个服务中去
	典型配置
	1.路由配置规则
		1)单实例serviceId映射，及上面的路由规则
		上面的路由规则可以简化而为zuul.routes.服务名=/client/**
		还有更加简单的映射规则 zuul.routes.服务名= ,相当于对/服务名/**的url进行路由
		2）单实例url映射
		可以将serviceId修改为url，指定服务的ip地址+端口
		3）多实例路由
		默认情况下,Zuul会使用Eureka中的基本负载均衡，如果要使用Ribbon的负载均衡功能，需要指定一个serviceId，然后
			禁止Ribbon使用Eureka
			zuul.routes.ribbon-route.path=/ribbon/**
			zuul.routes.ribbon-route.servcieId=ribbon-route
			ribbon.eureka.enabled=false
			ribbon-route.ribbon.NIWSServerListClassName=
			ribbon-route.ribbon.NFLoadBalancerRuleClassName=
			ribbon-route.listOfServers=
		4)forward本地跳转，匹配规则跳转到匹配到url的方法上
			zuul.routes.client-a.path=/client/**
			zuul.routes.client-a.url=forward:/client
		5)相同路径的加载规则，如果为同一个映射路径指定多个serviceId，那么会路由到最后面那个服务
			
	2.路由通配符
		/**:匹配任意数量的路径和字符，如/client/add,/client/add/a
		/*:匹配任意数量的字符  如/client/a,/client/ab
		/?:匹配单个字符

	功能配置
	1.路由前缀
		zuul.prefix=/pre,再次访问后端接口就需要加上在原来的路径上加上/pre,如/pre/client/**
	2.服务屏蔽与路径屏蔽
		zuul.ignored-services=client-b
		zuul.ignored-patterns=/**/div/**	
		加上这两个配置，zuul在拉取服务列表创建映射规则的时候就会忽略掉client-b服务和/**/div/**接口
	
	3.敏感头信息
		zuul.routes.client-a.sensitiveHeaders:Cookie,Set-Cookie,Authorization
		可以切断指定的敏感头与下游服务的交互，防止信息泄露
	4.重定向问题
		防止认证成功跳转的地址变成了认证服务的host而不是zuul的host
		zuul.add-host-header:true
	5.重试机制
		zuul.retryable=true
		ribbon.MaxAutoRetries=1 #同一个服务重试的次数(除去首次）
		ribbon.MaxAutoRetriesNextServer:1 #切换相同服务数量
		#此功能要慎用，保证幂等性
	
	Filter链
		Zuul中一共有四种不同生命周期的Filter
			pre:到下级服务之前执行，对请求进行预处理，如鉴权、限流。
			route:路由动作的执行者，发送Http请求的地方
			post:在源服务返回结果或者异常信息发生后执行的，对返回信息做处理
			error:在整个生命周期内如果发生异常，会进入error Filter
	1.认证和鉴权

	2.压力控制
		spring-cloud-zuul-ratelimit限流库
		   根据user认证用户名
		   根据origin 客户机ip
		   根据url 某个请求Url
		   根据serviceId 特定服务限流
	
	3.金丝雀测试

	4.动态路由

	5.负载消减

	6.静态响应处理

	7.主动流量管理

	
