package com.quoteapp.controller;

import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.quoteapp.dao.QuoteDao;
import com.quoteapp.model.Quote;
import com.quoteapp.service.QuoteService;

import javax.validation.Valid;

@Controller
public class QuoteAppWelcomeController {
	
	@Autowired
	private QuoteService quoteService;

	@RequestMapping("/welcome")
	public ModelAndView showWelcome() {
		Quote quote = retrieveLastQuoteFromDB();
		return new ModelAndView("welcome", "quote", quote);
	}

	@RequestMapping("/welcome-random")
	public ModelAndView showRandomQuote() {
		Quote quote = retrieveRandomQuoteFromDB();
		return new ModelAndView("welcome", "quote", quote);
	}
	
	@RequestMapping(value="/quote", method=RequestMethod.GET)
	public String showQuoteForm(final Quote quote, Model model) {
		quote.setAuthor("Adam Luther");
		Quote q1 = new Quote("Hillary Clinton", "Some quote");
		model.addAttribute("q1", q1);
		return "quote-form";
	}
	
	@RequestMapping(value="/quote", method=RequestMethod.POST)
	public String saveQuote(@Valid final Quote quote, final BindingResult bindingResult,
			final ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "quote-form";
		}
		quoteService.saveQuote(quote);
		return "redirect:/welcome";
	}
	
	@RequestMapping(value="/get-last-quote")
	@ResponseBody
	public Quote getLastQuote() {
		return retrieveLastQuoteFromDB();
	}
	
	@RequestMapping(value="/get-random-quote")
	@ResponseBody
	public Quote getRandomQuote() {
		return retrieveRandomQuoteFromDB();
	}
	
	private Quote retrieveLastQuoteFromDB() {
		List<Quote> quotes = quoteService.findAllQuotes();
		if(quotes != null && !quotes.isEmpty()) {
			return quotes.get(quotes.size()-1);
		}
		return null;
	}

	private Quote retrieveRandomQuoteFromDB() {
		List<Quote> quotes = quoteService.findAllQuotes();
		if(!quotes.isEmpty()) {
			return quotes.get(new Random().nextInt(quotes.size()));
		}
		return null;
	}
}
