package com.mcp.infrastructure.common.util.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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


    /***
     * 替换指定文件中的指定内容
     *
     * @param filepath
     *            文件路径
     * @param sourceStr
     *            文件需要替换的内容
     * @param targetStr
     *            替换后的内容
     * @return 替换成功返回true，否则返回false
     */
    public static boolean replaceFileStr(String filepath, String sourceStr, String targetStr) {
        try {
            FileReader fis = new FileReader(filepath); // 创建文件输入流
//			BufferedReader br = new BufferedReader(fis);
            char[] data = new char[1024]; // 创建缓冲字符数组
            int rn = 0;
            StringBuilder sb = new StringBuilder(); // 创建字符串构建器
            // fis.read(data)：将字符读入数组。在某个输入可用、发生 I/O
            // 错误或者已到达流的末尾前，此方法一直阻塞。读取的字符数，如果已到达流的末尾，则返回 -1
            while ((rn = fis.read(data)) > 0) { // 读取文件内容到字符串构建器
                String str = String.valueOf(data, 0, rn);// 把数组转换成字符串
//				System.out.println(str);
                sb.append(str);
            }
            fis.close();// 关闭输入流
            // 从构建器中生成字符串，并替换搜索文本
            String str = sb.toString().replace(sourceStr, targetStr);
            FileWriter fout = new FileWriter(filepath);// 创建文件输出流
            fout.write(str.toCharArray());// 把替换完成的字符串写入文件内
            fout.close();// 关闭输出流

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
