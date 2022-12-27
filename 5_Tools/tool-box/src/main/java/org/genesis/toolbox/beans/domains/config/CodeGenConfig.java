package org.genesis.toolbox.beans.domains.config;

import lombok.Data;

/**
 * @author KG
 * @developer
 * @description
 * @date Created in 2022年10月08日 1:33 PM
 * @modified_by
 */
@Data
public class CodeGenConfig {
    private String codeOutputPath;
    private String codePackageName;
    private String codeModuleName;
    private String dbUrl;
    private String dbUser;
    private String dbPass;
    private String author;
}
