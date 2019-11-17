package com.point_trans.model;

import java.sql.Connection;
import java.util.*;

public interface Point_transDAO_interface{
	public void insert(Point_transVO point_transVO);
	public void update(Point_transVO point_transVO);
	public void delete(String member_id);
	public Point_transVO findByPrimaryKey(String point_trans_id);
	public List<Point_transVO> getAll();
	public List<Point_transVOApp> getAllMyPointTrans(String member_id);
	public void insertWithCourseApp(Point_transVOApp point_transVOApp, Connection con);
	public void insertApp(Point_transVOApp point_transVOApp);
}
