package com.qrreport.model;

import java.util.ArrayList;
import java.util.List;

public class QrrepoService {
	private QrrepoDAO qrrepoDAO ;
	
	public QrrepoService() {
		qrrepoDAO = new QrrepoDAO();
	}
	
	public void addQrrepo(String repoq_id, String repor_id, String qrrepocontent) {
		QrrepoVO qrrepoVO =new QrrepoVO();
		qrrepoVO.setRepoq_id(repoq_id);
		qrrepoVO.setRepor_id(repor_id);
		qrrepoVO.setQrrepocontent(qrrepocontent);
		qrrepoDAO.insert(qrrepoVO);
	}
	
	public void deleteQrrepo(String qrrepo_id) {
		qrrepoDAO.delete(qrrepo_id);
	}
	
	public void updateQrrepo(String qrrepo_id, Integer qrrepostatus) {
		QrrepoVO qrrepoVO =new QrrepoVO();
		qrrepoVO.setQrrepo_id(qrrepo_id);
		qrrepoVO.setQrrepostatus(qrrepostatus);
		qrrepoDAO.update(qrrepoVO);
	}
	
	public QrrepoVO findQrrepoByPK(String qrrepo_id) {
		QrrepoVO qrrepoVO =new QrrepoVO();
		qrrepoVO = qrrepoDAO.findByPK(qrrepo_id);
		return qrrepoVO;
	}
	
	public List<QrrepoVO> getAllQrrepo(){
		List<QrrepoVO> qrrepolist = new ArrayList<QrrepoVO>();
		qrrepolist = qrrepoDAO.getAll();
		return qrrepolist;
	}
	
	public List<QrrepoVO> getAllQrrepoNotDoneYet(){
		List<QrrepoVO> qrrepolist = new ArrayList<QrrepoVO>();
		qrrepolist = qrrepoDAO.getAllNotDoneYet();
		return qrrepolist;
	}
}
