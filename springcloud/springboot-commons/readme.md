jdk版本要求1.8以上


cloud:
    consul:
      host: localhost
host为consul服务端ip地址

@HystrixCommand fallbackMethod参数，被调的方法必须和源方法参数，返回类型一致