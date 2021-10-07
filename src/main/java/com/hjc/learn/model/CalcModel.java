package com.hjc.learn.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 指标值的计算模型
 *
 * @author houjichao
 */
@ApiModel("指标值的计算模型")
@Data
public class CalcModel implements Serializable {

    private static final long serialVersionUID = 2107655332424835241L;

    /**
     * 指标标志
     */
    @ApiModelProperty("指标标志")
    private String indexId;

    /**
     * 1 数据源
     */
    @ApiModelProperty(name = "1-数据源 2-子任务计算")
    private Integer sourceType;

    /**
     * sourceType=1表名
     */
    @ApiModelProperty("表名")
    private String table;

    /**
     * schema
     */
    @ApiModelProperty("schema")
    private String scheme;

    /**
     * 操作字段
     */
    @ApiModelProperty("操作字段")
    private String field;

    /**
     * max,min,avg,count,sum,manul
     */
    @ApiModelProperty("max,min,avg,count,sum,manul")
    private String method;

    /**
     * 数据源标志
     */
    @ApiModelProperty("数据源标志")
    private String dbSourceId;


    @ApiModelProperty("计算公式类型；1：加权平均方式；2：自定义方式，sourceType=2特有")
    private Integer calcRule;


    @ApiModelProperty(value = "计算公式，sourceType=2特有", example = "\\[(2*_44f25f7880f440f19c602e2c431f43ce)+ (3*4)+(4*5)"
            + "\\]/_1124e76845b141809a1ac3d1b158bb94")
    private String calcFormula;


    @ApiModelProperty(value = "关联关系，sourceType=2特有", example = "[{\"relationId\":\"子任务id\",\"relationName\":\"子任务名称\","
            + "\"weight\":0.5}]")
    private List<IndexValueCalcRelationVo> calcRelations;
}
