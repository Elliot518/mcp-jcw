package org.genesis.toolbox.beans.core.impl;

import org.genesis.toolbox.beans.domains.action.TemplateWriter;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.region.RegionElement;
import org.genesis.toolbox.beans.domains.region.RegionElementGroup;
import org.genesis.toolbox.beans.domains.region.RegionEntity;
import org.genesis.toolbox.constants.code.KW;
import org.genesis.toolbox.constants.code.PKG;
import org.genesis.toolbox.constants.config.FoldersConf;
import org.genesis.toolbox.constants.template.PlaceHolder;
import org.genesis.toolbox.constants.template.Template;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ACXmlCodeGenerator
 * @Package org.genesis.toolbox.beans.core.impl
 * @Description: generating auto curation xml
 * @date 2018/6/26 17:33
 */
@Repository("acXmlCodeGenerator")
public class ACXmlCodeGenerator extends BaseCodeGenerator {
    @Override
    public void generate(EnvAssembly envAssembly) {
        super.generate(envAssembly);

        String destFileName = envAssembly.getCodeConfigGroup().getGlobalAppConfig().getAutoCurationXmlFile();
        String destFilePath = envAssembly.getFolderGroup().getAutoCurationFolder() + KW.PATH_SP + destFileName;

        EntityMap entityMap = envAssembly.getDomdGroup().getEntityMap();
        HashMap<String, RegionEntity> sourceRegions = entityMap.getSourceRegions();
        RegionElementGroup sourceRegionGroup = transformRegionsToGroup(sourceRegions);

        String xmlPathPrefix = envAssembly.getRegionGroup().getAutoCurationConfig().getXmlPathPrefix();
        String exeClassName = envAssembly.getCodeConfigGroup().getGlobalAppConfig().getBasePackage() + PKG.SEP +
                envAssembly.getRegionGroup().getAutoCurationConfig().getSubPackage() + PKG.SEP +
                envAssembly.getRegionGroup().getAutoCurationConfig().getExecutionClass();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getResourceAsStream(FoldersConf.TEMP_LOCATION +Template.AUTO_CURATION)));
             BufferedWriter bw = new BufferedWriter(
                     new OutputStreamWriter(
                             new FileOutputStream(new File(destFilePath)), "UTF-8"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(PlaceHolder.OTHER_REGIONS_BLOCK)) {
                    // other region relation
                    List<RegionElement> otherRegionElements = sourceRegionGroup.getOtherRegions();
                    HashMap<String, String> replaceMap = new HashMap<>();
                    for (RegionElement regionElement:otherRegionElements) {
                        replaceMap.put(PlaceHolder.REGION_PATH, regionElement.getPath());
                        replaceMap.put(PlaceHolder.COL_STR, regionElement.getColumns());
                        TemplateWriter.writeTempLine(FoldersConf.TEMP_LOCATION, Template.OTHER_REGION_RELATION, replaceMap, bw);
                    }
                } else {
                    // xml head
                    line = line.replace(PlaceHolder.XML_PATH_PREFIX, xmlPathPrefix);
                    line = line.replace(PlaceHolder.EXE_CLS_NAME, exeClassName);

                    // main region
                    line = line.replace(PlaceHolder.MAIN_REGION_PATH, sourceRegionGroup.getMainRegionPath());
                    line = line.replace(PlaceHolder.COL_STR, sourceRegionGroup.getMainRegionColumns());

                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RegionElementGroup transformRegionsToGroup(HashMap<String, RegionEntity> regions) {
        RegionElementGroup regionElementGroup = new RegionElementGroup();
        List<RegionElement> otherRegions = new ArrayList<>();
        for (Map.Entry<String, RegionEntity> entry : regions.entrySet()) {
            RegionEntity regionEntity = entry.getValue();
            String regionPath = regionEntity.getPath();
            String regionColumns = String.join(",", regionEntity.getColumns());

            if (regionEntity.getMain()) {
                regionElementGroup.setMainRegionPath(regionPath);
                regionElementGroup.setMainRegionColumns(regionColumns);
            } else {
                // other regions
                RegionElement regionElement = new RegionElement();
                regionElement.setPath(regionPath);
                regionElement.setColumns(regionColumns);
                otherRegions.add(regionElement);
            }
        }
        regionElementGroup.setOtherRegions(otherRegions);

        return regionElementGroup;
    }
}

