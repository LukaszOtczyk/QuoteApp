package com.quoteapp.dao;

import java.util.List;

import com.quoteapp.model.Quote;

public interface QuoteDao {

	void saveQuote(Quote q);
	
	List<Quote> findAllQuotes();
	
	void deleteQuoteById(Long id);
	
	Quote findById(Long id);
	
	void updateQuote(Quote q);
}
