package org.tai.todolist;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tai.todolist.dao.BlogMapper;
import org.tai.todolist.dao.FansMapper;
import org.tai.todolist.entity.Blog;
import org.tai.todolist.entity.Fans;
import org.tai.todolist.entity.User;
import org.tai.todolist.exception.BusinessException;
import org.tai.todolist.exception.ErrorCode;
import org.tai.todolist.service.FansService;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ToDoListApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("test");
    }

    // @Test
    @Deprecated
    public void mybatisPlusGenerator() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig
                .Builder("jdbc:mysql:///to_do_list", "root", "password")
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();

        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir("src\\main\\java")
                .author("Karigen")
                .enableSwagger()
                .enableSpringdoc()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .build();

        PackageConfig packageConfig = new PackageConfig.Builder()
                /*
                    大坑:mybatis-plus生成的xml文件只能放在resources目录下才能被扫描到
                    和mybatis一样XxMapper和XxMapper.xml放在一起是不能被扫描到的
                 */
                .xml("mapper")
                .parent("org.tai.todolist")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("dao")
                .controller("controller")
                .build();

        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .addInclude("user", "fans", "task", "blog")

                .entityBuilder()
                .enableActiveRecord()
                .enableFileOverride()
                .idType(IdType.AUTO)
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .enableLombok()
                .enableChainModel()
                .logicDeleteColumnName("flag")

                .controllerBuilder()
                .enableRestStyle()

                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
                .superServiceClass(IService.class)
                .superServiceImplClass(ServiceImpl.class)

                .mapperBuilder()
                .enableBaseResultMap()
                .enableBaseColumnList()
                .superClass(BaseMapper.class)
                .build();

        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .strategy(strategyConfig)
                .packageInfo(packageConfig);

        autoGenerator.execute();
    }

    @Test
    public void testException() {
        try {
            BusinessException businessException = new BusinessException(ErrorCode.USERNAME_NOT_EXIST);
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(businessException));
            throw businessException;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCurrentTimeHours() {
        Integer hours = (int) (System.currentTimeMillis() / (60 * 60 * 1_000));
        System.out.println(hours);
    }

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testSelectAllBlogsRelated() {
        List<Map<Integer, Object>> maps = blogMapper.selectAllBlogsRelated(9);
        maps.forEach(System.out::println);
    }

    // @Test
    public void testInsetUser() {
        new User().setEmail("1317794623@qq.com")
                .setPassword("123")
                .setUserid(1)
                .setUsername("zhuzhu")
                .insert();
    }

    // @Test
    public void testRemoveBlog() {
        blogMapper.deleteById(1);
    }

    // @Test
    public void testInsertBlog() {
        new Blog().setUserid(1)
                .setContext("test")
                .setPostTime((int) (System.currentTimeMillis() / 1000))
                .setFlag(true)
                .insert();

    }

    // @Test
    public void testSelectBlog() throws JsonProcessingException {
        Blog blog = new Blog().selectById(1);
        String s = new ObjectMapper().writeValueAsString(blog);
        System.out.println(s);
    }

    @Autowired
    private FansMapper fansMapper;

    // @Test
    public void testGetFans() {
        int userid = 4;
        List<Map<Integer, Object>> fans = fansMapper.selectByUserId(userid);
        int count = fans.size();
        System.out.println(fans);
        System.out.println(count);
    }

    @Test
    public void testGetFollows() {
        int fanid = 5;
        List<Map<Integer, Object>> fans = fansMapper.selectByFanId(fanid);
        int count = fans.size();
        System.out.println(fans);
        System.out.println(count);
    }

    // @Test
    public void testInsertFans() {
        new Fans().setUserid(1)
                .setFanId(2)
                .insert();
    }

    @Test
    public void testCommonFollows() {
        List<Map<Integer, Object>> maps = fansMapper.selectCommonFollows(2, 3);
        System.out.println(maps);
    }

    @Test
    public void testMutualFollows() {
        List<Map<Integer, Object>> maps = fansMapper.selectMutualFollows(1);
        System.out.println(maps);
    }

    @Test
    public void testDelete() {
        fansMapper.deleteFan(8,7);
    }

    @Test
    public void testBlogGet(){
        blogMapper.deleteBlog(4);
        List<Map<Integer, Object>> maps = blogMapper.selectAllBlogs();
        maps.forEach(System.out::println);

    }

}

