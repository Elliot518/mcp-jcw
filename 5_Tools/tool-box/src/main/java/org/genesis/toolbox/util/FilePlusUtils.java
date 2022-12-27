package org.genesis.toolbox.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang.StringUtils;
import org.genesis.toolbox.regex.RegexJudger;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : KG
 * description:
 * create date: 3:14 PM 2020/1/3
 * modified by:
 */
@Slf4j
public class FilePlusUtils {

    private static final String UNIT_B = "B";
    private static final String UNIT_K = "K";
    private static final String UNIT_M = "M";
    private static final String UNIT_G = "G";

    /**
     * 判断文件大小限制
     * @author KG
     * @date 2021/8/25 9:34 PM
     * @param len 文件长度
     * @param size 限制大小
     * @param unit 限制单位(B,K,M,G)
     * @return boolean
     */
    public static boolean checkFileSizeLimit(Long len, int size, String unit) {
        double fileSize = 0;

        if (UNIT_B.equalsIgnoreCase(unit)) {
            fileSize = len;
        } else if (UNIT_K.equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1024;
        } else if (UNIT_M.equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1048576;
        } else if (UNIT_G.equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1073741824;
        }

        return fileSize <= size;
    }

    /**
     * 列出目录下文件列表的完整路径
     *
     * @param dirPath
     * @param suffix
     * @return
     */
    public static List<String> listFilePaths(String dirPath, String suffix) {
        Collection<File> files = FileUtils.listFiles(new File(dirPath), FileFilterUtils.suffixFileFilter(suffix), null);

        return files.stream().map(File::getPath).collect(Collectors.toList());
    }

    /**
     * 列出目录下文件列表名
     *
     * @param dirPath
     * @param suffix
     * @return
     */
    public static List<String> listFileNames(String dirPath, String suffix) {
        Collection<File> files = FileUtils.listFiles(new File(dirPath), FileFilterUtils.suffixFileFilter(suffix), null);

        return files.stream().map(File::getName).collect(Collectors.toList());
    }

    public static List<String> listFilePaths(String dirPath, String suffix, String pattern) {
        Collection<File> files = FileUtils.listFiles(new File(dirPath), FileFilterUtils.suffixFileFilter(suffix), null);
        List<String> filePaths = new ArrayList<>();
        for (File file : files) {
            String filePath = file.getPath();
            filePath = filePath.replace("~$", "");
            if (RegexJudger.match(pattern, filePath)) {
                filePaths.add(filePath);
            }
        }

        return filePaths;
    }

    /**
     * move file to a new path(not including file name)
     *
     * @param oldPath
     * @param newPath
     */
    public static void moveFile(String oldPath, String newPath) {
        try {
            File file = new File(oldPath);
            if (file.renameTo(new File(newPath + file.getName()))) {
                log.info("File is moved successful!");
            } else {
                log.error("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * move file to a new file path(including file name)
     *
     * @param oldPath
     * @param newPath
     */
    public static void moveFilePath(String oldPath, String newPath) {
        try {
            File file = new File(oldPath);
            if (file.renameTo(new File(newPath))) {
                log.info("File is moved successful!");
            } else {
                log.error("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件长度
     *
     * @param filePath
     */
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file.length();
        }

        return 0;
    }

    private static void testListFileListByPattern() {
        String pattern = ".*(\\d{4})-(\\d+)-(\\d+).xlsx";

        String dirPath = "/Users/jgu55/data/attrition/RG0074";
        List<String> fileList = FilePlusUtils.listFilePaths(dirPath, "xlsx", pattern);
        for (String filePath : fileList) {
            System.out.println(filePath);
        }
    }

    private static void testMoveFile() {
        String oldPath = "/Users/jgu55/data/attrition/RV1012/test.xlsx";
        String newPath = "/Users/jgu55/Downloads/test/";
        moveFile(oldPath, newPath);
    }

    private static void testFileSize() {
        String filePath = "/Users/jgu55/data/attrition/RV1012/rv1012.xlsx";
        long fileSize = getFileSize(filePath);
        System.out.println("File Size: " + fileSize);
    }

    public static String combinePath(String path1, String path2) {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);

        return file2.getPath();
    }

    public static String combinePathAndPackage(String basePath, String pkg) {
        String newPath = basePath;

        List<String> pkgList = Arrays.asList(StringUtils.split(pkg, "."));
        for (String pkgPath : pkgList) {
            newPath = combinePath(newPath, pkgPath);
        }

        return newPath;
    }

    public static String combinePathPackageAndModule(String basePath, String pkg, String module) {
        String newBasePath = combinePath(basePath, module);

        String newPath = combinePathAndPackage(newBasePath, pkg);
        newPath = combinePath(newPath, module);

        return newPath;
    }

    /**
     * 修改文件内容，把旧字符串替换成新字符串
     *
     * @param filePath 文件路径
     * @param oldStr   旧字符串
     * @param newStr   新字符串
     * @return
     */
    public static boolean modifyFileContent(String filePath, String oldStr, String newStr) {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            String line = null;
            //记住上一次的偏移量
            long lastPoint = 0;
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                if (line.contains(oldStr)) {
                    String str = line.replace(oldStr, newStr);
                    raf.seek(lastPoint);
                    raf.write(str.getBytes());
                }
                lastPoint = ponit;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 增加内容到文件末尾
     *
     * @param filePath
     * @param content
     */
    public static void appendContentToFile(String filePath, String content) {

        // 打开一个随机访问文件流，按读写方式
        try (RandomAccessFile randomFile = new RandomAccessFile(filePath, "rw")) {
            // 文件长度，字节数
            long fileLength = randomFile.length();

            // 将写文件指针移到文件尾
            randomFile.seek(fileLength);

            randomFile.writeBytes(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入文件内容
     *
     * @param filePath 文件路径
     * @param keyWords 搜索字符串
     * @param content  插入字符串
     * @return
     */
    public static boolean insertFileContentBeforeKeyWords(String filePath, String keyWords, String content) {
        //创建一个临时文件来保存插入点后的数据
        File tmp = null;
        try {
            tmp = File.createTempFile("tmp", null);
            tmp.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream tmpOut = null;
        FileInputStream tmpIn = null;

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            String line = null;
            tmpOut = new FileOutputStream(tmp);
            tmpIn = new FileInputStream(tmp);

            //记住上一次的偏移量
            long lastPoint = 0;
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                if (line.contains(keyWords)) {
                    raf.seek(lastPoint);

                    //下面代码将插入点后的内容读入临时文件中保存
                    byte[] bbuf = new byte[64];
                    //用于保存实际读取的字节数
                    int hasRead = 0;
                    //使用循环方式读取插入点后的数据
                    while ((hasRead = raf.read(bbuf)) > 0) {
                        //将读取的数据写入临时文件
                        tmpOut.write(bbuf, 0, hasRead);
                    }
                    //下面代码插入内容
                    //把文件记录指针重新定位到pos位置
                    raf.seek(lastPoint);
                    //追加需要插入的内容
                    raf.write(content.getBytes());
                    //追加临时文件中的内容
                    while ((hasRead = tmpIn.read(bbuf)) > 0) {
                        raf.write(bbuf, 0, hasRead);
                    }
                }
                lastPoint = ponit;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
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
