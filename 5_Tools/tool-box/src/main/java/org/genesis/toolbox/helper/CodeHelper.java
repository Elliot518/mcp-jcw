package org.genesis.toolbox.helper;

import org.genesis.toolbox.beans.domains.config.CodeGenConfig;
import org.genesis.toolbox.util.CodeUtils;
import org.genesis.toolbox.util.FilePlusUtils;

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

    public static String getCodeBasePath(CodeGenConfig config) {
        String basePath = config.getCodeOutputPath();
        String packageName = config.getCodePackageName();
        String moduleName = config.getCodeModuleName();

        return FilePlusUtils.combinePathPackageAndModule(basePath, packageName, moduleName);
    }

    public static String getEntityPath(CodeGenConfig config) {
        String codeBasePath = getCodeBasePath(config);

        return FilePlusUtils.combinePath(codeBasePath, ENTITY_FOLDER);
    }

    public static String getServicePath(CodeGenConfig config) {
        String codeBasePath = getCodeBasePath(config);

        return FilePlusUtils.combinePath(codeBasePath, SERVICE_FOLDER);
    }

    public static String getServiceImplPath(CodeGenConfig config) {
        String codeBasePath = getServicePath(config);

        return FilePlusUtils.combinePath(codeBasePath, IMPL_FOLDER);
    }

    public static void renameServicesToRepositories(CodeGenConfig config) {
        String servicePath = getServicePath(config);

        CodeUtils.renameServicesToRepositories(servicePath, false);
    }

    public static void renameServiceImplsToRepositoryImpls(CodeGenConfig config) {
        String servicePath = getServiceImplPath(config);

        CodeUtils.renameServicesToRepositories(servicePath, true);
    }

    public static void addTimeTableFields(CodeGenConfig config) {
        String entityPath = getEntityPath(config);

        CodeUtils.addCreateAnnotationsToEntities(entityPath);
        CodeUtils.addUpdateAnnotationToEntities(entityPath);
    }
}

