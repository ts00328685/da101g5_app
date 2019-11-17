package com.res.model;

import java.util.List;

public interface ResDAO_interface {
	public void insert(ResVO resVO);
	public void update(ResVO resVO);
	public void delete(String res_id);
	public void hide(String res_id);
	public void onshelf(String res_id);
	public ResVO findByPK(String res_id);
	public List<ResVO> findByBelongto(String que_id);
	public List<ResVO> getAll();
	public void edit(String res_id, byte[] resfile, String filetype);
	//public ResVO insertThenGet(ResVO resVO);/*���*/
}
