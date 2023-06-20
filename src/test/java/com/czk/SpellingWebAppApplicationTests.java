package com.czk;

import com.czk.domain.Word;
import com.czk.domain.WordListType;
import com.czk.service.RecordService;
import com.czk.service.WordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpellingWebAppApplicationTests {

	@Autowired
	private WordService wordService;

	@Autowired
	private RecordService recordService;

	@Test
	public void testGetWords(){
		List<Word> words = wordService.getWords(2);
		System.out.println(words);
	}

	@Test
	public void testGetWordList(){
		List<WordListType> wordList = wordService.getWordList();
		System.out.println(wordList);
	}

	@Test
	public void testGetWordsAndRecord(){
		List<Word> wordsAndRecord = wordService.getWordsAndRecord(1, 1);
		System.out.println(wordsAndRecord);
	}

	@Test void testGetLearnedAndGraspCount(){
		Map map = recordService.getLearnedAndGraspCount(1);
		System.out.println(map);
	}

	@Test
	public void testGetRecordCouunt(){
		Integer recordCount = recordService.getRecordCount(999, 1);
		System.out.println("recordCount:" +recordCount);
	}

	@Test void testSetRecordUpdateTime(){
		System.out.println(new Date(new java.util.Date().getTime()));
	}

	@Test void testgetNowdayStudyWordSum(){
		Integer sum = recordService.getUserNowdayStudySum(1);
		System.out.println(sum);
	}

	@Test void testgetUserRecordCountSum(){
		System.out.println(recordService.getUserRecordCountSum(1));
	}

	@Test void testgetCountLogs(){
		System.out.println(recordService.getCountLogs(1));
	}
}
