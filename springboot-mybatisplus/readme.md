springboot2.x集成mybatis-plus 3.x
--
* 注意事项
1. CodeGenerator输入表名必须和数据库一致，目前还未找到相关配置可以过滤表名前缀
2. 自定义ID生成器注意事项,对象添加注解TableField
```java
	/**策略fill 表示什么时候触发，DEFAULT 默认、INSERT新增、UPDATE 更新、INSERT_UPDATE插入更新 */
	@TableField(value = "createTime", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
```
MyMetaObjectHandler.java
```java
	@Slf4j
	@Component
	public class MyMetaObjectHandler implements MetaObjectHandler {
		/**新增方法执行策略  */
	    @Override
	    public void insertFill(MetaObject metaObject) {
	    	/** 添加字段 注意参数fieldType 必须和fieldVal的类型一致 */
	        this.strictInsertFill(metaObject, "accountId", String.class, IdGeneratorUtils.getAccountID()); // 起始版本 3.3.0(推荐使用)
	        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
	//        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
	        // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
	        /* 上面选其一使用,下面的已过时(注意 strictInsertFill 有多个方法,详细查看源码) */
	        //this.setFieldValByName("operator", "Jerry", metaObject);
	        //this.setInsertFieldValByName("operator", "Jerry", metaObject);
	    }
		/**更新方法执行策略 */
	    @Override
	    public void updateFill(MetaObject metaObject) {
	        log.info("start update fill ....");
	        /** 添加字段 注意参数fieldType 必须和fieldVal的类型一致 */
	        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
	        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
	        /* 上面选其一使用,下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码) */
	        //this.setFieldValByName("operator", "Tom", metaObject);
	        //this.setUpdateFieldValByName("operator", "Tom", metaObject);
	    }
	}
```
