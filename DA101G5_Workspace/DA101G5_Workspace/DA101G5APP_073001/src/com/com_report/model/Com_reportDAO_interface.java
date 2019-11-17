package com.com_report.model;

import java.util.*;

public interface Com_reportDAO_interface {
          public void insert(Com_reportVO com_reportVO);
          public void update(Com_reportVO com_reportVO);
          public void delete(String com_report_id);
          public Com_reportVO findByPrimaryKey(String com_report_id);
          public List<Com_reportVO> getAll();
//        public List<Com_reportVO> getAll(Map<String, String[]> map); 
}
