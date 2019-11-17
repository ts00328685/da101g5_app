package com.card.model;

import java.util.*;

public interface CardDAO_interface {
          public void insert(CardVO cardVO);
          public void update(CardVO cardVO);
          public void delete(String card_id);
          public void updateVoice(String card_id, byte[] voice);
          public CardVO findByPrimaryKey(String card_id);
          public List<CardVO> getAll();
//        public List<CardVO> getAll(Map<String, String[]> map); 
}
