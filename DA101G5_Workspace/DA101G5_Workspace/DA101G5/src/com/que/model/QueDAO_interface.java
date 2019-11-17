package com.que.model;

import java.util.List;
import com.que.model.QueVO;

public interface QueDAO_interface {
	public void insert(QueVO queVO,String[] qhashtagArray);/*hashtag一起 done*/
    public void update(QueVO queVO);
    public void edit(byte[] quefile,String filetype,String que_id,String[] hashtagArray);
    public void delete(String que_id);/*Res要一起 done*/ /*進階:que內文顯示刪除文章 res保留*/
    public void hide(String que_id);/*Res要一起 done*/ /*進階:que內文顯示審核中 res保留*/
    public void onshelf(String que_id);
    public QueVO findByPK(String que_id);
//    public QueVO findLatest();/*基本款做完補上*/
//    public QueVO insertThenGet(QueVO queVO);/*同上*/
    public List<QueVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //public List<QueVO> getAll(Map<String, String[]> map);
}
