package com.hjc.learn.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 任务指标值计算模型关联关系表
 *
 * @author houjichao
 */
public class IndexValueCalcRelationVo implements Serializable {

    private static final long serialVersionUID = 4413692093901918004L;

    @ApiModelProperty("子任务id")
    @NotBlank(message = "关联的子任务id不能为空")
    private String relationId;

    @ApiModelProperty("子任务名称")
    private String relationName;

    @ApiModelProperty("子任务指标值")
    private Double indexValue;

    @ApiModelProperty("权重")
    private Double weight;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public Double getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(Double indexValue) {
        this.indexValue = indexValue;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
