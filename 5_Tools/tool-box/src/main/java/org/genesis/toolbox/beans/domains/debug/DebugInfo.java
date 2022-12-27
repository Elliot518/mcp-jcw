package org.genesis.toolbox.beans.domains.debug;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: DebugInfo
 * @Package org.genesis.fastcode.beans.domains
 * @Description: debug info
 * @date 2018/6/4 21:20
 */
@Component
public class DebugInfo {
    @Value("${debug.level}")
    private String level;

    public Integer getLevel() {
        return Integer.parseInt(level);
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

