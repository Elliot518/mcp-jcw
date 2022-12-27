package org.genesis.toolbox.beans.service.impl;

import org.genesis.toolbox.beans.domains.database.InMemoryTable;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.domains.group.DomdGroup;
import org.genesis.toolbox.beans.repository.DataInspector;
import org.genesis.toolbox.beans.service.DataCheckService;
import org.genesis.toolbox.beans.ui.component.MyTextPane;
import org.genesis.toolbox.util.Debugger;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: DataCheckServiceImpl
 * @Package org.genesis.toolbox.beans.service.impl
 * @Description: check data service implementation
 * @date 2018/7/31 10:36
 */

@Service
public class DataCheckServiceImpl implements DataCheckService {
    private DataInspector dataInspector;

    public DataInspector getDataInspector() {
        return dataInspector;
    }

    public void setDataInspector(DataInspector dataInspector) {
        this.dataInspector = dataInspector;
    }

    @Override
    public void checkInputData(DomdGroup domdGroup, MyTextPane textPane) {
        EntityMap entityMap = domdGroup.getEntityMap();
        HashMap<String, InMemoryTable> inputTables = domdGroup.getInputTables();

        Boolean result = this.dataInspector.validateInputData(entityMap, inputTables, textPane);

        Debugger.printLine(textPane);
        Debugger.printSubHead(textPane, "-- 4&gt; Overall Evaluation --", "#008000");
        Debugger.printLine(textPane);
        String overallReport = "";
        if (result) {
            overallReport = "Data quality is good!";
            Debugger.printBanner(textPane, overallReport, 0, "#008000");
        } else {
            overallReport = "Data quality is bad!! Please note all the hints with red color and fix them!";
            Debugger.printBanner(textPane, overallReport, 0, "#DC143C");
        }

        Debugger.printSeparator(textPane);
    }
}
