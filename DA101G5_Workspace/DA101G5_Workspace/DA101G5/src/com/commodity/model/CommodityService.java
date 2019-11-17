package com.commodity.model;

import java.sql.Timestamp;
import java.util.List;

public class CommodityService {

	private CommodityDAO_interface dao;

	public CommodityService() {
		dao = new CommodityDAO();
	}

	public CommodityVO addCommodity(String member_id, String com_description,  byte[]  com_pic,
			String com_download, Integer com_price, Timestamp trandate, Integer com_status, Double e_score,
			Integer e_pol_statistics, String e_des, String com_detail) {

		CommodityVO commodityVO = new CommodityVO();

		commodityVO.setMember_id(member_id);
		commodityVO.setCom_description(com_description);
		commodityVO.setCom_pic(com_pic);
		commodityVO.setCom_download(com_download);
		commodityVO.setCom_price(com_price);
		commodityVO.setTrandate(trandate);
		commodityVO.setCom_status(com_status);
		commodityVO.setE_score(e_score);
		commodityVO.setE_pol_statistics(e_pol_statistics);
		commodityVO.setE_des(e_des);
		commodityVO.setCom_detail(com_detail);
		
		dao.insert(commodityVO);
		
		return commodityVO;
	}

	public CommodityVO updateCommodity(String com_id, String member_id, String com_description,  byte[]  com_pic,
			String com_download, Integer com_price, Timestamp trandate, Integer com_status, Double e_score,
			Integer e_pol_statistics, String e_des, String com_detail) {

		CommodityVO commodityVO = new CommodityVO();

		commodityVO.setCom_id(com_id);
		commodityVO.setMember_id(member_id);
		commodityVO.setCom_description(com_description);
		commodityVO.setCom_pic(com_pic);
		commodityVO.setCom_download(com_download);
		commodityVO.setCom_price(com_price);
		commodityVO.setTrandate(trandate);
		commodityVO.setCom_status(com_status);
		commodityVO.setE_score(e_score);
		commodityVO.setE_pol_statistics(e_pol_statistics);
		commodityVO.setE_des(e_des);
		commodityVO.setCom_detail(com_detail);
		
		dao.update(commodityVO);

		return commodityVO;
	}

	public void deleteCommodity(String com_id) {
		dao.delete(com_id);
	}

	public CommodityVO getOneCommodity(String com_id) {
		return dao.findByPrimaryKey(com_id);
	}

	public List<CommodityVO> getAll() {
		return dao.getAll();
	}
}
