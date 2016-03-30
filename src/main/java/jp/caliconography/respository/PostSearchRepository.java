package jp.caliconography.respository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.caliconography.domain.Post;

@Repository
public class PostSearchRepository {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;
	
	FullTextEntityManager createFullTextEntityManager(EntityManager entityManager) {
		return Search.getFullTextEntityManager(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> searchTitleOrSummary(String keyword) {
		System.out.println("############## repo: keyword:" + keyword);
		FullTextEntityManager fullTextEntityManager = createFullTextEntityManager(entityManager);

		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Post.class).get();

		// Splitした単語のAND
		// Function<String, Query> f = k ->
		// queryBuilder.keyword().onFields("post", "createdAt",
		// "updatedAt").matching(k).createQuery();
		// Query[] queries = (Query[])
		// Stream.of(keyword.split("\\s+")).map(f).toArray();

		// val luceneQuery =
		// queries.
		// queries.foldLeft(queryBuilder.bool) { (acc, c) =>
		// acc.must(c).asInstanceOf[BooleanJunction[BooleanJunction[_]]]
		// }.createQuery

		Query query = queryBuilder.keyword().onFields("body").matching(keyword).createQuery();

		return fullTextEntityManager.createFullTextQuery(query, Post.class).getResultList();
	}
}
