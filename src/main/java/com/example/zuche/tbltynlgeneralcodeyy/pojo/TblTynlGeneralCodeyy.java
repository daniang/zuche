package com.example.zuche.tbltynlgeneralcodeyy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统编码表，并发量高，读写表
 * </p>
 *
 * @author chengzhang
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TblTynlGeneralCodeyy对象", description="系统编码表，并发量高，读写表")
public class TblTynlGeneralCodeyy implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId("CODE_ID")
    private String codeId;

    @ApiModelProperty(value = "编码名称")
    @TableField("CODE_NAME")
    private String codeName;

    @TableField("CODE_VALUE")
    private String codeValue;

    @ApiModelProperty(value = "排序号")
    @TableField("SORT_NO")
    private Integer sortNo;

    @TableField("CODE_TYPE")
    private String codeType;

    @ApiModelProperty(value = "创建者")
    @TableField("CREATE_USER")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @TableField("CREATE_NAME")
    private String createName;

    @ApiModelProperty(value = "最后修改者")
    @TableField("UPDATE_USER")
    private String updateUser;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @TableField("UPDATE_NAME")
    private String updateName;

    @TableField("PRO_ID")
    private String proId;

    @TableField("ORG_ID")
    private String orgId;

    @TableField("TENANT_ID")
    private String tenantId;

    @TableField("IS_DELETE")
    private Integer isDelete;

    @ApiModelProperty(value = "0不是是系统编码            1是系统编码            系统编码不允许进行修改")
    @TableField("IS_EDIT")
    private Integer isEdit;

    @TableField("VAILD_START_TIME")
    private LocalDateTime vaildStartTime;

    @TableField("VAILD_END_TIME")
    private LocalDateTime vaildEndTime;

    @TableField("PARENT_ID")
    private String parentId;

    @TableField("TREE_PATH")
    private String treePath;

    @TableField("TREE_NAME_PATH")
    private String treeNamePath;

    @TableField("LEVEL_NO")
    private Integer levelNo;

    @TableField("IS_LEAF")
    private Integer isLeaf;

    @ApiModelProperty(value = "拼音码")
    @TableField("LETTER_CODE")
    private String letterCode;

    @TableField("CODE_KEYWORD")
    private String codeKeyword;

    @TableField("BUS_PRO_ID")
    private String busProId;


}
