package com.mcp.toolkit.code.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.mcp.toolkit.code.generator.core.MybatisPlusGeneratorUtils;
import com.mcp.toolkit.code.generator.helper.CodeHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author : KG
 * description:
 * create date: 10:56 AM 2020/6/15
 * modified by:
 */

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // generate mybatis plus code hierarchy
        MybatisPlusGeneratorUtils.createCodeStructure();

        // rename service file names to repository file names
        CodeHelper.renameServicesToRepositories();

        // rename service impls to repository impls
        CodeHelper.renameServiceImplsToRepositoryImpls();

        // add time table field annotations
        //CodeHelper.addTimeTableFields();
    }

}








