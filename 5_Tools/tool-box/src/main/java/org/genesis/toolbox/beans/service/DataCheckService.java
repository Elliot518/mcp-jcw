package org.genesis.toolbox.beans.service;

import org.genesis.toolbox.beans.domains.group.DomdGroup;
import org.genesis.toolbox.beans.ui.component.MyTextPane;

/**
 * @author KG(Kelvin Gu)
 * @InterfaceName: DataCheckService
 * @Package org.genesis.toolbox.beans.service
 * @Description: check data
 * @date 2018/7/31 10:33
 */
public interface DataCheckService {
    void checkInputData(DomdGroup domdGroup, MyTextPane textPane);
}
