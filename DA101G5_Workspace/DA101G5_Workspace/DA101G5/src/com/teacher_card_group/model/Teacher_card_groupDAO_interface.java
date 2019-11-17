package com.teacher_card_group.model;

import java.util.*;

public interface Teacher_card_groupDAO_interface {
          public void insert(Teacher_card_groupVO teacher_card_groupVO);
          public void update(Teacher_card_groupVO teacher_card_groupVO);
          public void delete(String teacher_card_group_id);
          public Teacher_card_groupVO findByPrimaryKey(String teacher_card_group_id);
          public List<Teacher_card_groupVO> getAll();
//        public List<Teacher_card_groupVO> getAll(Map<String, String[]> map); 
}
