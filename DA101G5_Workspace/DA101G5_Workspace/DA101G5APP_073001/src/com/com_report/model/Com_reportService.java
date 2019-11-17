package com.com_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Com_reportService {

	private Com_reportDAO_interface dao;

	public Com_reportService() {
		dao = new Com_reportDAO();
	}

	public Com_reportVO addCom_report(String com_id, String r_des, Timestamp trandate, Integer r_status) {

		Com_reportVO com_reportVO = new Com_reportVO();

		com_reportVO.setCom_id(com_id);
		com_reportVO.setR_des(r_des);
		com_reportVO.setTrandate(trandate);
		com_reportVO.setR_status(r_status);
		
		dao.insert(com_reportVO);
		
		return com_reportVO;
	}

	public Com_reportVO updateCom_report(String com_report_id, String com_id, String r_des, Timestamp trandate, Integer r_status) {

		Com_reportVO com_reportVO = new Com_reportVO();

		com_reportVO.setCom_report_id(com_report_id);
		com_reportVO.setCom_id(com_id);
		com_reportVO.setR_des(r_des);
		com_reportVO.setTrandate(trandate);
		com_reportVO.setR_status(r_status);
		
		dao.update(com_reportVO);

		return com_reportVO;
	}

	public void deleteCom_report(String com_report_id) {
		dao.delete(com_report_id);
	}

	public Com_reportVO getOneCom_report(String com_report_id) {
		return dao.findByPrimaryKey(com_report_id);
	}

	public List<Com_reportVO> getAll() {
		return dao.getAll();
	}
}
