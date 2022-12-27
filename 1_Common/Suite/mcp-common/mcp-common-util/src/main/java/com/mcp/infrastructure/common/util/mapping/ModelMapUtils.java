package com.mcp.infrastructure.common.util.mapping;

import com.mcp.infrastructure.common.util.log.LogUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.util.List;

/**
 * @author: KG
 * @description:
 * @date: Created in 1:42 PM 2019/9/17
 * @modified by:
 */

public class ModelMapUtils {
    public static<S,D> List<D> mapList(List<S> srcList) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(srcList, new TypeToken<List<D>>() {}.getType());
    }

    public static<S,D> List<D> mapList(List<S> srcList, PropertyMap<S,D> propertyMap) {
        ModelMapper modelMapper = new ModelMapper();

        //添加映射器
        modelMapper.addMappings(propertyMap);
        modelMapper.validate();

        List<D> destList = modelMapper.map(srcList, new TypeToken<List<D>>() {}.getType());

        LogUtils.info(destList.toString());

        return destList;
    }
}


