package com.teacher_card_table.model;

import java.util.*;

public interface Teacher_card_tableDAO_interface {
          public void insert(Teacher_card_tableVO teacher_card_tableVO);
          public void update(Teacher_card_tableVO teacher_card_tableVO);
          public void delete(String card_id, String teacher_card_group_id);
          public Teacher_card_tableVO findByPrimaryKey(String card_id, String teacher_card_group_id);
          public List<Teacher_card_tableVO> getAll();
//        public List<Teacher_card_tableVO> getAll(Map<String, String[]> map); 
}
