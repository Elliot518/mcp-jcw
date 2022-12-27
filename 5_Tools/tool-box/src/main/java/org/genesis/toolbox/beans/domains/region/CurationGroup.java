package org.genesis.toolbox.beans.domains.region;

import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: CurationGroup
 * @Package org.genesis.toolbox.beans.domains.region
 * @Description: curation group for auto curation xml
 * @date 2018/7/12 14:27
 */
public class CurationGroup {
    /**
     * head elements
     */
    private String executionClassName;
    private String topic;
    private String failRegion;

    private RegionElementGroup regionElementGroup;

    private List<MethodElement> methodElements;

    private UdfElement udfElement;

    private ViewRegionElement viewRegionElement;

    public String getExecutionClassName() {
        return executionClassName;
    }

    public void setExecutionClassName(String executionClassName) {
        this.executionClassName = executionClassName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFailRegion() {
        return failRegion;
    }

    public void setFailRegion(String failRegion) {
        this.failRegion = failRegion;
    }

    public RegionElementGroup getRegionElementGroup() {
        return regionElementGroup;
    }

    public void setRegionElementGroup(RegionElementGroup regionElementGroup) {
        this.regionElementGroup = regionElementGroup;
    }

    public List<MethodElement> getMethodElements() {
        return methodElements;
    }

    public void setMethodElements(List<MethodElement> methodElements) {
        this.methodElements = methodElements;
    }

    public UdfElement getUdfElement() {
        return udfElement;
    }

    public void setUdfElement(UdfElement udfElement) {
        this.udfElement = udfElement;
    }

    public ViewRegionElement getViewRegionElement() {
        return viewRegionElement;
    }

    public void setViewRegionElement(ViewRegionElement viewRegionElement) {
        this.viewRegionElement = viewRegionElement;
    }
}

