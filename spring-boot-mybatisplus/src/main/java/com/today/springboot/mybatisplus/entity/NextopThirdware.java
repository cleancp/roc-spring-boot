package com.today.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.today.springboot.mybatisplus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Insert;

/**
 * <p>
 *
 * </p>
 *
 * @author roc.zou
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("nextop_thirdware")
public class NextopThirdware extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * sku编码
     */
    @TableField(value = "sku", fill = FieldFill.INSERT)
    private String sku;


}
