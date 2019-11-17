package com.qrreport.model;

import java.util.List;

public interface QrrepoDAO_interface {
	
	public void insert(QrrepoVO qrrepoVO);
	public void delete(String qrrepo_id);
	public void update(QrrepoVO qrrepoVO);/*�B�z���A*/
	public QrrepoVO findByPK(String qrrepo_id);
	public List<QrrepoVO> getAll();
	public List<QrrepoVO> getAllNotDoneYet();
}
