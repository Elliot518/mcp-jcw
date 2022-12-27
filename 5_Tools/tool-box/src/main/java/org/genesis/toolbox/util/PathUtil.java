package org.genesis.toolbox.util;

import org.apache.commons.lang.StringUtils;
import org.genesis.toolbox.constants.code.KW;
import org.genesis.toolbox.constants.config.FilesConf;
import org.genesis.toolbox.constants.config.FoldersConf;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: PathUtil
 * @Package org.genesis.fastcode.util
 * @Description: path util, return all kinds of paths
 * @date 2018/5/28 15:22
 */
public class PathUtil {
    /** 
     * @Description: get app root path for all environments
     * @return 
     * @throws 
     * @author KG(Kelvin Gu)
     * @date 05-28-2018 03:24:29
     */
    public static String getAppRootPath() {
        String appRootPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fileName = FilesConf.APP_FILE;
        if (StringUtils.contains(appRootPath, fileName)) {
            int index = appRootPath.indexOf(fileName);
            return appRootPath.substring(0, index);
        }

        return appRootPath;
    }

    public static String getAppConfigPath() {
        String rootPath = getAppRootPath();
        String lastChar = rootPath.substring(rootPath.length()-1, rootPath.length());
        if (lastChar.equals(KW.PATH_SP)) {
            return getAppRootPath() + FoldersConf.CONFIG_FOLDER;
        }

        return getAppRootPath() + KW.PATH_SP + FoldersConf.CONFIG_FOLDER;
    }
}
