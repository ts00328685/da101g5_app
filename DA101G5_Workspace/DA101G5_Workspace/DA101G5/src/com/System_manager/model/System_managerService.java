package com.System_manager.model;

import java.util.List;

public class System_managerService {
	private System_managerDAO_interface dao;
	
	public System_managerService() {
		dao = new System_managerDAO();
	}
	
	public System_managerVO addSystem_manager(String sm_id, String sm_name, String sm_ac, String sm_pass) {
		System_managerVO system_managerVO = new System_managerVO();

		system_managerVO.setSm_id(sm_id);
		system_managerVO.setSm_name(sm_name);
		system_managerVO.setSm_ac(sm_ac);
		system_managerVO.setSm_pass(sm_pass);
		dao.insert(system_managerVO);
		
		return system_managerVO;
	}
	public System_managerVO updatesystem_manager(String sm_id, String sm_name, String sm_ac, String sm_pass) {

		System_managerVO system_managerVO = new System_managerVO();

		system_managerVO.setSm_id(sm_id);
		system_managerVO.setSm_name(sm_name);
		system_managerVO.setSm_ac(sm_ac);
		system_managerVO.setSm_pass(sm_pass);
		dao.update(system_managerVO);

		return system_managerVO;
	}
	public System_managerVO getSystem_manager(String sm_id) {
		return dao.findByPrimaryKey(sm_id);
	}

	public List<System_managerVO> getAll() {
		return dao.getAll();
	}
	
	public System_managerVO findSystem_managerByAccount(String sm_ac) {
		return dao.findByAccount(sm_ac);
	}

}
