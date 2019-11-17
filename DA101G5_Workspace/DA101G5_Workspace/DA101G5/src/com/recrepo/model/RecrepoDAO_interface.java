package com.recrepo.model;

import java.util.List;

public interface RecrepoDAO_interface {
	public void insert(RecrepoVO recrepoVO);
	public void delete(String recrepo_id);
	public void update(String recrepo_id,Integer repostatus);
	public RecrepoVO findByPK(String recrepo_id);
	public List<RecrepoVO> getAll();
	public List<RecrepoVO> getAllNotDoneYet();
}
