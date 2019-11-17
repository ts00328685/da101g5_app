package com.commodity.model;

import java.util.*;

public interface CommodityDAO_interface {
          public void insert(CommodityVO commodityVO);
          public void update(CommodityVO commodityVO);
          public void updateImage(String commodity_id, byte[] com_pic);
          public void delete(String commodity_id);
          public CommodityVO findByPrimaryKey(String commodity_id);
          public List<CommodityVO> findByMember_id(String member_id);
          public List<CommodityVO> getAll();
//        public List<CommodityVO> getAll(Map<String, String[]> map); 
}
