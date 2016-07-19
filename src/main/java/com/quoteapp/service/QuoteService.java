package com.quoteapp.service;

import java.util.List;

import com.quoteapp.model.Quote;

public interface QuoteService {

	void saveQuote(Quote q);
	
	List<Quote> findAllQuotes();
	
	void deleteQuoteById(Long id);
	
	Quote findQuoteById(Long id);
	
	void updateQuote(Quote q);
}
