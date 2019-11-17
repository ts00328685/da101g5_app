package com.Language.model;

import java.util.List;

public interface LanguageDAO_interface {
	 public void insert(LanguageVO languageVO);
     public void update(LanguageVO languageVO);
     public void delete(String language_id);
     public LanguageVO findByPrimaryKey(String language_id);
     public List<LanguageVO> getAll();

}
