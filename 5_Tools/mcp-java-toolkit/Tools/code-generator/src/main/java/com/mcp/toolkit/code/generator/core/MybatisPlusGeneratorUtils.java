package com.mcp.toolkit.code.generator.core;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mcp.toolkit.code.generator.constants.CodeConst;
import com.mcp.toolkit.infrastructure.util.PropertyUtils;

import java.util.Scanner;

/**
 * @author: KG
 * @description:
 * @date: Created in 10:44 上午 2020/7/14
 * @modified by:
 */

public class MybatisPlusGeneratorUtils {
    public static void createCodeStructure() {
        String dbUser = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.DB_USER);
        String dbPass = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.DB_PASS);
        String moduleName = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.MODULE_NAME);
        String dbUrl = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.DB_URL);

        String basePath = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.BASE_PATH);
        String outputPath = String.format(basePath + "%s", moduleName);
        String packageName = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.PKG_NAME);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        System.out.print("请输入作者: ");
        Scanner scanner = new Scanner(System.in);
        String author = scanner.nextLine();
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputPath);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        //dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername(dbUser);
        dsc.setPassword(dbPass);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityBuilderModel(true);
        strategy.setEntitySerialVersionUID(true);
        strategy.setRestControllerStyle(true);

        mpg.setStrategy(strategy);

        mpg.execute();

        System.out.println(String.format("代码生成完毕! 请去[%s]位置查看代码!", outputPath));
    }
}
