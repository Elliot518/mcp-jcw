package org.genesis.toolbox.beans.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.domains.config.*;
import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.group.CodeConfigGroup;
import org.genesis.toolbox.beans.domains.group.DomdGroup;
import org.genesis.toolbox.beans.domains.group.FolderGroup;
import org.genesis.toolbox.beans.domains.group.RegionGroup;
import org.genesis.toolbox.beans.domains.setting.FileInfo;
import org.genesis.toolbox.beans.repository.ExcelTemplateButler;
import org.genesis.toolbox.beans.service.EnvService;
import org.genesis.toolbox.constants.code.KW;
import org.genesis.toolbox.constants.config.FilesConf;
import org.genesis.toolbox.constants.config.FoldersConf;
import org.genesis.toolbox.util.FileUtil;
import org.genesis.toolbox.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: EnvServiceImpl
 * @Package org.genesis.toolbox.beans.service.impl
 * @Description: env service impl
 * @date 2018/6/22 11:29
 */
@Service
public class EnvServiceImpl implements EnvService {
    @Autowired
    private FileInfo fileInfo;

    @Autowired
    private ExcelTemplateButler excelTemplateButler;

    @Override
    public EnvAssembly bootstrap(String configMode) {
        EnvAssembly envAssembly = new EnvAssembly();

        // 1. get code config
        CodeConfigGroup codeConfigGroup = getCodeConfig(configMode);
        envAssembly.setCodeConfigGroup(codeConfigGroup);

        // 2. setup folders
        FolderGroup folderGroup = setupFolders(codeConfigGroup);
        envAssembly.setFolderGroup(folderGroup);

        // 3. get region group
        RegionGroup regionGroup = getRegionGroup(codeConfigGroup);
        envAssembly.setRegionGroup(regionGroup);

        // 4. get domd group
        DomdGroup domdGroup = getDomdGroup(codeConfigGroup);
        envAssembly.setDomdGroup(domdGroup);

        return envAssembly;
    }

    /**
     * @return
     * @throws
     * @Description: get code config deserialize from file RegionConfig.json
     * @author KG(Kelvin Gu)
     * @date 05-28-2018 04:02:05
     */
    private CodeConfigGroup getCodeConfig(String configMode) {
        String subFolder = "";
        if (configMode.equals(FoldersConf.XML_MODE)) {
            subFolder = FoldersConf.CFG_XML;
        } else {
            subFolder = FoldersConf.CFG_JSON;
        }

        String filePathGlobal = PathUtil.getAppConfigPath() + KW.PATH_SP + subFolder + KW.PATH_SP + FilesConf.FOLDER_DEV + KW.PATH_SP + FilesConf.GLOBAL_CONFIG;
        String fileDomdConfig = PathUtil.getAppConfigPath() + KW.PATH_SP + subFolder + KW.PATH_SP + FilesConf.FOLDER_BUIT + KW.PATH_SP + FilesConf.DOMD_CONFIG;
        String filePathFolderConfig = PathUtil.getAppConfigPath() + KW.PATH_SP + subFolder + KW.PATH_SP + FilesConf.FOLDER_DEV + KW.PATH_SP + FilesConf.FOLDER_CONFIG;
        String fileCodeGenConfig = PathUtil.getAppConfigPath() + KW.PATH_SP + subFolder + KW.PATH_SP + FilesConf.FOLDER_DEV + KW.PATH_SP + FilesConf.CODE_GEN_CONFIG;

        String jsonGlobal = FileUtil.readToString(filePathGlobal);
        String jsonDomdConfig = FileUtil.readToString(fileDomdConfig);
        String jsonFolderConfig = FileUtil.readToString(filePathFolderConfig);
        String jsonCodeGenConfig = FileUtil.readToString(fileCodeGenConfig);

        GlobalAppConfig globalAppConfig = null;
        DomdConfig domdConfig = null;
        FolderConfig folderConfig = null;
        CodeGenConfig codeGenConfig = null;
        CodeConfigGroup configGroup = new CodeConfigGroup();
        try {
            ObjectMapper mapper = new ObjectMapper();
            globalAppConfig = mapper.readValue(jsonGlobal, GlobalAppConfig.class);
            domdConfig = mapper.readValue(jsonDomdConfig, DomdConfig.class);
            folderConfig = mapper.readValue(jsonFolderConfig, FolderConfig.class);
            codeGenConfig = mapper.readValue(jsonCodeGenConfig, CodeGenConfig.class);

            configGroup.setGlobalAppConfig(globalAppConfig);
            configGroup.setDomdConfig(domdConfig);
            configGroup.setFolderConfig(folderConfig);
            configGroup.setCodeGenConfig(codeGenConfig);
        } catch (IOException e) {
            //Debugger.print(e.getMessage(), DebugLevel.ERR);
            e.printStackTrace();
        }

        return configGroup;
    }

    private RegionGroup getRegionGroup(CodeConfigGroup codeConfigGroup) {

        String filePathAutoConfig = PathUtil.getAppConfigPath() + KW.PATH_SP + FoldersConf.CFG_JSON + KW.PATH_SP + FilesConf.FOLDER_DEV + KW.PATH_SP + FilesConf.AUTO_CURATION_CONFIG;
        String jsonAutoConfig = FileUtil.readToString(filePathAutoConfig);

        AutoCurationConfig autoCurationConfig = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            autoCurationConfig = mapper.readValue(jsonAutoConfig, AutoCurationConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RegionGroup regionGroup = new RegionGroup();
        regionGroup.setAutoCurationConfig(autoCurationConfig);

        return regionGroup;
    }

    private DomdGroup getDomdGroup(CodeConfigGroup codeConfigGroup) {
        DomdConfig domdConfig = codeConfigGroup.getDomdConfig();
        EntityMap entityMap = excelTemplateButler.getEntityMap(domdConfig);
        HashMap<String, InMemoryTable> inputTables = excelTemplateButler.getInputTables(domdConfig);

        DomdGroup domdGroup = new DomdGroup();
        domdGroup.setEntityMap(entityMap);
        domdGroup.setInputTables(inputTables);

        return domdGroup;
    }

    /**
     * setup folder structure
     *
     * @param configGroup
     */
    private FolderGroup setupFolders(CodeConfigGroup configGroup) {
        String appRootPath = PathUtil.getAppRootPath();
        String codeOutputFolder = PathUtil.getAppRootPath() + FoldersConf.CODE_OUTPUT;
        String autoCurationFolder = codeOutputFolder + KW.PATH_SP + configGroup.getFolderConfig().getAutoCuration();

        FileUtil.createDir(codeOutputFolder);
        FileUtil.createDir(autoCurationFolder);

        FolderGroup folderGroup = new FolderGroup();
        folderGroup.setAppRootPath(appRootPath);
        folderGroup.setOutputFolder(codeOutputFolder);
        folderGroup.setAutoCurationFolder(autoCurationFolder);

        return folderGroup;
    }
}
