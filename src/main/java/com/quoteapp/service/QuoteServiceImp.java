package com.quoteapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quoteapp.dao.QuoteDao;
import com.quoteapp.model.Quote;

@Service("quoteService")
@Transactional
public class QuoteServiceImp implements QuoteService {

	@Autowired
	private QuoteDao quoteDao;
	
	@Override
	public void saveQuote(Quote q) {
		quoteDao.saveQuote(q);
	}

	@Override
	public List<Quote> findAllQuotes() {
		return quoteDao.findAllQuotes();
	}

	@Override
	public void deleteQuoteById(Long id) {
		quoteDao.deleteQuoteById(id);

	}

	@Override
	public Quote findQuoteById(Long id) {
		return quoteDao.findById(id);
	}

	@Override
	public void updateQuote(Quote q) {
		quoteDao.updateQuote(q);

	}

}
