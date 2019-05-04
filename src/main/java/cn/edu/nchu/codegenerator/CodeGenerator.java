package cn.edu.nchu.codegenerator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * @description:
 * @author: zhouxiang
 * @time: 2019/5/2 19:26
 */
public class CodeGenerator {

    //数据库连接信息
    public static final String LINK = "jdbc:mysql://localhost:3306/db_gbss_new?useUnicode=true&characterEncoding=utf8";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String ACCOUNT = "root";
    public static final String PASSWORD = "123456";

    public static String[] tables = {
            "admin",
            "admin_role",
            "admin_role_inf",
            "goods",
            "goods_description",
            "goods_in_and_out",
            "goods_order",
            "goods_statistics",
            "shopping_cart",
            "user_address",
            "user_base",
            "user_detail",
            "user_point",
            "user_point_rules"
    };


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java/")
                .setAuthor("zhouxiang")
                .setOpen(false)
                .setFileOverride(true)
                // 不需要ActiveRecord特性的请改为false
                .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                /*.setControllerName("%sController")
                .setMapperName("%sDao")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")*/;

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(LINK);
        dsc.setDriverName(DRIVER);
        dsc.setUsername(ACCOUNT);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig packageConfig = new PackageConfig()
                .setParent("cn.edu.nchu.gbss")
                .setController("controller")
                .setEntity("model")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl");

        mpg.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        //自定义MapperXml位置
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/vm/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //模板设置
        /*TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("/templates/xDomain")
                .setMapper("/templates/xDao")
                .setService("/templates/xManager")
                .setServiceImpl("/templates/xManagerImpl")
                .setXml(null)
                .setController(null);
        mpg.setTemplate(templateConfig);*/

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //lombok默认为true
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        //设置要被扫描的表名
        strategy.setInclude(tables);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();

    }
}
