springboot2.x微服务实现
-
* 模块说明<br>
springcloud-dependencies<br>
springboot-commons<br>
springcloud-gateway<br>

> 网关使用自动规则匹配自动调用相关服务

DynamicRouteServiceImpl.java
```java
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher publisher;

    /**
     * 增加路由
     *
     * @param definition
     * @return
     */
    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /**
     * 更新路由
     *
     * @param definition
     * @return
     */
    public String update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route  routeId: " + definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete fail";
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
```
DynamicRouteServiceImplByNacos.java
```java
@Component
public class DynamicRouteServiceImplByNacos implements CommandLineRunner {
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;
    @Autowired
    private NacosGatewayProperties nacosGatewayProperties;

    /**
     * 监听Nacos Server下发的动态路由配置
     */
    public void dynamicRouteByNacosListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(nacosGatewayProperties.getAddress());
//            String content = configService.getConfig(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), nacosGatewayProperties.getTimeout());
//            System.out.println(content);
            configService.addListener(nacosGatewayProperties.getDataId(), nacosGatewayProperties.getGroupId(), new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> list = JSONArray.parseArray(configInfo, RouteDefinition.class);
                    list.forEach(definition -> {
                        dynamicRouteService.update(definition);
                    });
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        dynamicRouteByNacosListener();
    }
}
```