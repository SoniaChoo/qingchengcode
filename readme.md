# 青橙电商项目实战Day01遇到的坑
## 1.setting中maven的设置,务必设置成自己本地的,不然后面导入jar包会报错,以后养成这个习惯,建立一个maven项目或者导入一个maven项目,要去看看setting中maven有没有对

## 2.自己在main下面新建webapp项目时,要在配置文件中添加<packaging>war</packaging>,webapp文件才会出现一个点

## 3 在service层和controller层使用注解的时候,要注意是哪个包下面的.
```java
/*这个注解我们需要选择dubbo下面的,不要选错了*/
@Service
public class BrandServiceImpl implements BrandService {
    
    @Autowired
    private BrandMapper brandMapper;
    
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }
}
```

```java
@RestController
@RequestMapping("/brand")
public class Brandcontroller {
    /*这个也是要选择dubbo下面的*/
    @Reference
    private BrandService brandService;
     @RequestMapping("/findAll")       
    public List<Brand> findAll() {
        return brandService.findAll();
    }
}
```

## 4. pagehelper报红,虽然父工程的pom.xml是有这个依赖的,但是没有用,所以在qingcheng_common_service的pom文件中重新导入了依赖
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="plugins">
			<array>
				<bean class="com.github.pagehelper.PageHelper">
					<property name="properties">
						<value>
							dialect=mysql
						</value>
					</property>
				</bean>
			</array>
		</property>
	</bean>
```
```xml
<dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
<!--如果不是这个版本好像不行-->
            <version>4.1.6</version>
        </dependency>
```
## 5.实体类pojo要写上setter和getter方法,不然的话查询得到的结果全是空格