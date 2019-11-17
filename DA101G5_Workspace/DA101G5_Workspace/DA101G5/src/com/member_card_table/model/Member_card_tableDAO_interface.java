package com.member_card_table.model;

import java.util.*;

public interface Member_card_tableDAO_interface {
          public void insert(Member_card_tableVO member_card_tableVO);
          public void update(Member_card_tableVO member_card_tableVO);
          public void delete(String card_id, String member_card_group_id);
          public Member_card_tableVO findByPrimaryKey(String card_id, String member_card_group_id);
          public List<Member_card_tableVO> getAll();
//        public List<Member_card_tableVO> getAll(Map<String, String[]> map); 
}
