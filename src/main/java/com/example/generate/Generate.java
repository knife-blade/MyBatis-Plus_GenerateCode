package com.example.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generate {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mp" +
            "?useUnicode=true&characterEncoding=utf8" +
            "&allowPublicKeyRetrieval=True&useSSL=false" +
            "&serverTimezone=Asia/Shanghai";
    private static final String userName    = "root";
    private static final String password    = "222333";
    private static final String tableName   = "t_user";
    private static final String tablePrefix = "t_";

    // 演示例子
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("xxx");
        globalConfig.setFileOverride(false);  //默认就是false
        globalConfig.setOpen(false);
        // gc.setBaseResultMap(true); // mapper.xml 生成 ResultMap
        // gc.setBaseColumnList(true); // mapper.xml 生成 ColumnList

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setMapperName("%sMapper");
        // gc.setXmlName("%sDao");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        dataSourceConfig.setUrl(url);
        // dsc.setSchemaName("public");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(userName);
        dataSourceConfig.setPassword(password);
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        // pc.setParent("com.example.generate.out");
        // pc.setModuleName("xxx");
        packageConfig.setParent("com.example.generate." + tableName.substring(tablePrefix.length()));
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        // InjectionConfig cfg = new InjectionConfig() {
        //     @Override
        //     public void initMap() {
        //         // to do nothing
        //     }
        // };

        // 配置自定义输出模板
        TemplateConfig templateConfig = new TemplateConfig();
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController("templates_mp/MyController.java");
        templateConfig.setService("templates_mp/MyService.java");
        templateConfig.setServiceImpl("templates_mp/MyServiceImpl.java");
        templateConfig.setMapper("templates_mp/MyMapper.java");
        templateConfig.setEntity("templates_mp/MyEntity.java");
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);

        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");

        // 表名
        strategyConfig.setInclude(tableName);
        strategyConfig.setTablePrefix(tablePrefix);
        strategyConfig.setControllerMappingHyphenStyle(true);

        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }
}
