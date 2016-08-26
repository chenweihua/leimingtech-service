package com.leimingtech.service.module.store.vo;

import com.leimingtech.core.entity.StoreGoodsClass;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class StoreGoodsClassVo extends StoreGoodsClass {

    private List<StoreGoodsClassVo> list;
}
