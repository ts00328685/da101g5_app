package com.card.model;

import java.util.List;

public class CardService {

	private CardDAO_interface dao;

	public CardService() {
		dao = new CardDAO();
	}

	public CardVO addCard(String teacher_id, String word, String phonetic_symbol,
							byte[] voice, String native_explain, String example	) {

		CardVO cardVO = new CardVO();

		cardVO.setTeacher_id(teacher_id);
		cardVO.setWord(word);
		cardVO.setPhonetic_symbol(phonetic_symbol);
		cardVO.setVoice(voice);
		cardVO.setNative_explain(native_explain);
		cardVO.setExample(example);
		dao.insert(cardVO);
		
		return cardVO;
	}
	
	public void updateOneCardVoice(String card_id, byte[] voice) {

		dao.updateVoice(card_id, voice);
		
	}
	
	public CardVO updateCard(String card_id, String teacher_id, String word, String phonetic_symbol,
			byte[] voice, String native_explain, String example	) {

		CardVO cardVO = new CardVO();

		cardVO.setCard_id(card_id);
		cardVO.setTeacher_id(teacher_id);
		cardVO.setWord(word);
		cardVO.setPhonetic_symbol(phonetic_symbol);
		cardVO.setVoice(voice);
		cardVO.setNative_explain(native_explain);
		cardVO.setExample(example);
		dao.update(cardVO);

		return cardVO;
	}

	public void deleteCard(String card_id) {
		dao.delete(card_id);
	}

	public CardVO getOneCard(String card_id) {
		return dao.findByPrimaryKey(card_id);
	}

	public List<CardVO> getAll() {
		return dao.getAll();
	}
}
