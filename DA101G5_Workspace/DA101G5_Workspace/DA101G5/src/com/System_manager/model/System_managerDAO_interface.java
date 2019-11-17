package com.System_manager.model;

import java.util.*;

public interface System_managerDAO_interface {
	public void insert(System_managerVO system_managerVO);
    public void update(System_managerVO system_managerVO);
    public System_managerVO findByPrimaryKey(String sm_id);
    public System_managerVO findByAccount(String sm_ac);
    public List<System_managerVO> getAll();
 
}
