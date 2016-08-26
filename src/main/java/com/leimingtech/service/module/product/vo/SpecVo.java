package com.leimingtech.service.module.product.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.leimingtech.core.entity.base.SpecValue;

@Data
@ToString
public class SpecVo {
	private Integer spId;
	private String spName;
	private List<SpecValue> specValueList;
}
