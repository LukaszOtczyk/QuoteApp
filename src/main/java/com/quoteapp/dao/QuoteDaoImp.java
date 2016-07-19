package com.quoteapp.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.query.spi.QueryParameterBindingTypeResolver;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quoteapp.model.Quote;

import jdk.nashorn.internal.ir.GetSplitState;

@Repository("quoteDao")
public class QuoteDaoImp extends AbstractDao implements QuoteDao {

	@Override
	public void saveQuote(Quote q) {
		persist(q);
	}

	@Override
	public List<Quote> findAllQuotes() {
		CriteriaQuery<Quote> criteria = 
				getSession().getCriteriaBuilder().createQuery(Quote.class);
		criteria.select(criteria.from(Quote.class));
		return getSession().createQuery(criteria).getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteQuoteById(Long id) {
		Query query = getSession().createQuery("delete from Quote where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public Quote findById(Long id) {
		Query query = getSession().createQuery("from Quote where id = :id");
		query.setParameter("id", id);
		return (Quote) query.uniqueResult();
	}

	@Override
	public void updateQuote(Quote q) {
		getSession().update(q);

	}

}
