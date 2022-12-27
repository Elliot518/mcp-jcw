package org.genesis.toolbox.beans.service;

import java.util.Set;

public interface ADFRestService {
    String login();
    int count(String path,String token);
    Set<String> getColumn(String path, String token);
}
