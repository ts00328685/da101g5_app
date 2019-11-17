package com.recruit.model;

import java.util.List;

public interface RecruitDAO_interface {
	public void insert(RecruitVO recruitVO);
    public void delete(String recruit_id);
    public void update(String recruit_id,Integer recstatus);
    public void updateMemberPoint (String member_id,Integer mem_point);
    public RecruitVO findByPK(String recruit_id);

    public List<RecruitVO> getAll();
}
