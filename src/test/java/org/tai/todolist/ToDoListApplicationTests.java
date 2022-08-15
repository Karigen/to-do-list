package org.tai.todolist;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.tai.todolist.exception.BusinessException;
import org.tai.todolist.exception.ErrorCode;

@SpringBootTest
class ToDoListApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("test");
    }

    // @Test
    @Deprecated
    void mybatisPlusGenerator() {
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
                .parent("org.tai.todolist")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("dao")
                .xml("dao")
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

}
