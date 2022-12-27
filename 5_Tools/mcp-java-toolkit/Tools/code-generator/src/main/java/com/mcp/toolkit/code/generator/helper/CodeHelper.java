package com.mcp.toolkit.code.generator.helper;

import com.mcp.toolkit.code.generator.constants.CodeConst;
import com.mcp.toolkit.infrastructure.util.CodeUtils;
import com.mcp.toolkit.infrastructure.util.FilePlusUtils;
import com.mcp.toolkit.infrastructure.util.PropertyUtils;

/**
 * @author: KG
 * @description:
 * @date: Created in 11:39 上午 2020/7/14
 * @modified by:
 */

public class CodeHelper {
    private static final String SERVICE_FOLDER = "service";
    private static final String IMPL_FOLDER = "impl";
    private static final String ENTITY_FOLDER = "entity";

    public static String getCodeBasePath() {
        String basePath = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.BASE_PATH);
        String packageName = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.PKG_NAME);
        String moduleName = PropertyUtils.getProperty(CodeConst.CONFIG_FILE, CodeConst.MODULE_NAME);

        return FilePlusUtils.combinePathPackageAndModule(basePath, packageName, moduleName);
    }

    public static String getEntityPath() {
        String codeBasePath = getCodeBasePath();

        return FilePlusUtils.combinePath(codeBasePath, ENTITY_FOLDER);
    }

    public static String getServicePath() {
        String codeBasePath = getCodeBasePath();

        return FilePlusUtils.combinePath(codeBasePath, SERVICE_FOLDER);
    }

    public static String getServiceImplPath() {
        String codeBasePath = getServicePath();

        return FilePlusUtils.combinePath(codeBasePath, IMPL_FOLDER);
    }

    public static void renameServicesToRepositories() {
        String servicePath = getServicePath();

        CodeUtils.renameServicesToRepositories(servicePath, false);
    }

    public static void renameServiceImplsToRepositoryImpls() {
        String servicePath = getServiceImplPath();

        CodeUtils.renameServicesToRepositories(servicePath, true);
    }

    public static void addTimeTableFields() {
        String entityPath = getEntityPath();

        CodeUtils.addCreateAnnotationsToEntities(entityPath);
        CodeUtils.addUpdateAnnotationToEntities(entityPath);
    }
}
