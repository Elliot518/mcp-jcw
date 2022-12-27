package org.genesis.toolbox.util;

import org.genesis.toolbox.constants.debug.DebugLevel;

import java.io.File;
import java.io.FileInputStream;

public class FileUtil {
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            Debugger.print("目录: " + destDirName + "已经存在", DebugLevel.DBG);
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            Debugger.print("创建目录: " + destDirName + "成功！", DebugLevel.DBG);
            return true;
        } else {
            Debugger.print("创建目录: " + destDirName + "失败！", DebugLevel.DBG);
            return false;
        }
    }

    /**
     * @Description: read string from file
     * @param fileName
     * @return boolean
     * @throws
     * @author KG(Kelvin Gu)
     * @date 05-28-2018 02:59:57
     */
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try (FileInputStream in = new FileInputStream(file)) {
            in.read(filecontent);
            return new String(filecontent, encoding);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
