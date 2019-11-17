package com.Language.model;

import java.util.List;



public class LanguageService {
	
	private LanguageDAO_interface dao;
	
	public LanguageService() {
		
		dao=new LanguageDAO();
	}
	
	
	public LanguageVO addLanguage(String language) {

		LanguageVO languageVO = new LanguageVO();

		languageVO.setLanguage(language);
		dao.insert(languageVO);

		return languageVO;
	}

	//預留給 Struts 2 用的
	public void addLanguage(LanguageVO languageVO) {
		dao.insert(languageVO);
	}
	
	public LanguageVO updateLanguage(String language_id,String language) {

		LanguageVO languageVO = new LanguageVO();

		languageVO.setLanguage_id(language_id);
		languageVO.setLanguage(language);
		dao.update(languageVO);

		return dao.findByPrimaryKey(language_id);
	}
	
	//預留給 Struts 2 用的
	public void updateLanguage(LanguageVO languageVO) {
		dao.update(languageVO);
	}

	public void deleteLanguage(String language_id) {
		dao.delete(language_id);
	}

	public LanguageVO getOneLanguage(String language_id) {
		return dao.findByPrimaryKey(language_id);
	}

	public List<LanguageVO> getAll() {
		return dao.getAll();
	}
	
	
	

}
