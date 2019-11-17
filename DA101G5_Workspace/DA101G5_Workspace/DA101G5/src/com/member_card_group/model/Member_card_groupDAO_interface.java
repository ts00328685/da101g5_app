package com.member_card_group.model;

import java.util.*;

public interface Member_card_groupDAO_interface {
          public void insert(Member_card_groupVO member_card_groupVO);
          public void update(Member_card_groupVO member_card_groupVO);
          public void delete(String member_card_group_id);
          public Member_card_groupVO findByPrimaryKey(String member_card_group_id);
          public List<Member_card_groupVO> getAll();
//        public List<Member_card_groupVO> getAll(Map<String, String[]> map); 
}
