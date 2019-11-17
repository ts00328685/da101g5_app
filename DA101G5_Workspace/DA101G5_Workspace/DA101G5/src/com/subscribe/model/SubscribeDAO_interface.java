package com.subscribe.model;

import java.util.*;

public interface SubscribeDAO_interface {
          public void insert(SubscribeVO subscribeVO);
          public void update(SubscribeVO subscribeVO);
          public void delete(String sub_id);
          public SubscribeVO findByPrimaryKey(String sub_id);
          public List<SubscribeVO> getAll();
//        public List<SubscribeVO> getAll(Map<String, String[]> map); 
}
