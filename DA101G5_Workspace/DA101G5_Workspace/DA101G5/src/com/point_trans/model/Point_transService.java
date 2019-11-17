package com.point_trans.model;

import java.sql.Date;
import java.util.List;

public class Point_transService {

	private Point_transDAO_interface dao;

	public Point_transService() {
		dao = new Point_transDAO();
	}

	public Point_transVO addPoint_trans(String member_id, Double point_record, java.sql.Date transdate
			) {

		Point_transVO point_transVO = new Point_transVO();

		point_transVO.setMember_id(member_id);
		point_transVO.setPoint_record(point_record);
		point_transVO.setTransdate(transdate);
		dao.insert(point_transVO);

		return point_transVO;
	}

	public Point_transVO updatePoint_trans(String point_trans_id, String member_id, Double point_record, java.sql.Date transdate
			) {

		Point_transVO point_transVO = new Point_transVO();

		point_transVO.setPoint_trans_id(point_trans_id);
		point_transVO.setMember_id(member_id);
		point_transVO.setPoint_record(point_record);
		point_transVO.setTransdate(transdate);
		dao.update(point_transVO);

		return point_transVO;
	}

	public void deletePoint_trans(String point_trans_id) {
		dao.delete(point_trans_id);
	}

	public Point_transVO getOnePoint_trans(String point_trans_id) {
		return dao.findByPrimaryKey(point_trans_id);
	}

	public List<Point_transVO> getAll() {
		return dao.getAll();
	}
	public List<Point_transVO> getAll2(String member_id) {
		System.out.println(member_id);
		return dao.getAllById(member_id);
	}
}
