package com.mcp.toolkit.infrastructure.util;

import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 11:04 上午 2020/7/14
 * @modified by:
 */

public class FileNameUtils {
    private static final String INTERFACE_PREFIX = "I";
    private static final String SERVICE_SUFFIX = "Service";
    private static final String REPO_SUFFIX = "Repository";
    private static final String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    private static final String REPO_IMPL_SUFFIX = "RepositoryImpl";
    private static final String JAVA_SUFFIX = ".java";
    private static final String IMPL = "Impl";
    private static final String SERVICE_ANNOTATION = "@Service";
    private static final String REPO_ANNOTATION = "@Repository";
    private static final String API_MODEL_INSERT = "@ApiModelProperty(value = \"创建时间\")";
    private static final String ANNOTATION_INSERT = "@TableField(fill = FieldFill.INSERT)";
    private static final String API_MODEL_UPDATE = "@ApiModelProperty(value = \"更新时间\")";
    private static final String ANNOTATION_UPDATE = "@TableField(fill = FieldFill.INSERT_UPDATE)";
    private static final String CREATE_DATE_FIELD = "private LocalDateTime createDate;";
    private static final String CREATE_TIME_FIELD = "private LocalDateTime createTime;";
    private static final String LAST_UPDATE_FIELD = "private LocalDateTime lastUpdate;";
    private static final String UPDATE_TIME_FIELD = "private LocalDateTime updateTime;";
    private static final String TAB = "    ";

    /**
     * 将ISomeClassService文件名改成SomeClassRepository
     *
     * @param serviceName
     * @return
     */
    public static String changeServiceToRepository(String serviceName) {
        String newServiceName = serviceName;

        // remove first I
        if (INTERFACE_PREFIX.equals(serviceName.substring(0, 1))) {
            newServiceName = serviceName.substring(1);
        }

        newServiceName = newServiceName.replace(SERVICE_SUFFIX, REPO_SUFFIX);

        return newServiceName;
    }

    /**
     * 将SomeClassServiceImpl文件名改成SomeClassRepositoryImpl
     *
     * @param serviceName
     * @return
     */
    public static String changeServiceImplToRepositoryImpl(String serviceName) {
        String newServiceName = serviceName;

        newServiceName = newServiceName.replace(SERVICE_IMPL_SUFFIX, REPO_IMPL_SUFFIX);

        return newServiceName;
    }

    public static String getClassNameFromFile(String fileName) {
        return fileName.replace(JAVA_SUFFIX, "");
    }

    /**
     * 根据老的类名获取老的Service接口名
     *
     * @param className
     * @return
     */
    public static String getServiceInterfaceFromImpl(String className) {
        String interfaceName = className.replace(IMPL, "");

        return INTERFACE_PREFIX + interfaceName;
    }

    /**
     * 根据新的类名获取新的接口名
     *
     * @param className
     * @return
     */
    public static String getRepositoryInterfaceFromImpl(String className) {
        return className.replace(IMPL, "");
    }

    public static void renameServicesToRepositories(String basePath, Boolean isImpl) {
        List<String> oldFileNames = FilePlusUtils.listFileNames(basePath, JAVA_SUFFIX);
        for (String fileName : oldFileNames) {
            String oldFilePath = FilePlusUtils.combinePath(basePath, fileName);

            // 旧类名
            String oldClassName = getClassNameFromFile(fileName);

            // 新文件名
            String newFileName;
            if (isImpl) {
                newFileName = changeServiceImplToRepositoryImpl(fileName);
            } else {
                newFileName = changeServiceToRepository(fileName);
            }

            // 新类名
            String newClassName = getClassNameFromFile(newFileName);

            // 修改文件内容
            FilePlusUtils.modifyFileContent(oldFilePath, oldClassName, newClassName);

            if (isImpl) {
                // 老的接口名
                String oldServiceName = getServiceInterfaceFromImpl(oldClassName);

                // 新的接口名
                String newRepoName = getRepositoryInterfaceFromImpl(newClassName);

                // 修改注解名
                FilePlusUtils.modifyFileContent(oldFilePath, SERVICE_ANNOTATION, REPO_ANNOTATION + "\n");

                // 修改接口名
                FilePlusUtils.modifyFileContent(oldFilePath, oldServiceName, newRepoName);

                // fix bug
                FilePlusUtils.modifyFileContent(oldFilePath, "lic class", "public class");
                FilePlusUtils.appendContentToFile(oldFilePath, "}");
            }

            // 新的文件名路径
            String newFilePath = FilePlusUtils.combinePath(basePath, newFileName);

            FilePlusUtils.moveFilePath(oldFilePath, newFilePath);
        }
    }

    private static List<String> listJavaFilePaths(String basePath) {
        return FilePlusUtils.listFilePaths(basePath, JAVA_SUFFIX);
    }

    public static void addCreateAnnotationsToEntities(String basePath) {
        List<String> filePaths = listJavaFilePaths(basePath);

        for (String filePath : filePaths) {
            // add create field annotation
            String createAnnotation = TAB + API_MODEL_INSERT + "\n" + TAB + ANNOTATION_INSERT + "\n";
            FilePlusUtils.insertFileContentBeforeKeyWords(filePath, CREATE_DATE_FIELD, createAnnotation);
            FilePlusUtils.insertFileContentBeforeKeyWords(filePath, CREATE_TIME_FIELD, createAnnotation);
        }
    }

    public static void addUpdateAnnotationToEntities(String basePath) {
        List<String> filePaths = listJavaFilePaths(basePath);

        for (String filePath : filePaths) {
            // add update field annotation
            String updateAnnotation = TAB + API_MODEL_UPDATE + "\n" + TAB + ANNOTATION_UPDATE + "\n";
            FilePlusUtils.insertFileContentBeforeKeyWords(filePath, LAST_UPDATE_FIELD, updateAnnotation);
            FilePlusUtils.insertFileContentBeforeKeyWords(filePath, UPDATE_TIME_FIELD, updateAnnotation);
        }
    }




}
