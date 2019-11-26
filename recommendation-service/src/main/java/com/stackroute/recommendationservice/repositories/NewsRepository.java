package com.stackroute.recommendationservice.repositories;

import java.util.Collection;

import com.stackroute.recommendationservice.domain.Post;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource()
public interface NewsRepository extends Neo4jRepository<Post, Long> {

	Post findByTitle(@Param("title") String title);

	Collection<Post> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
	Collection<Post> graph(@Param("limit") int limit);

	@Query("match (u:User) where u.userName={userName} \n" +
			"\tmatch (u)-[:Viewed]->(n:News) with collect(n) as viewedNews \n" +
			"\t\tmatch (publisher:User)-[:Published]->(n) \n" +
			"\t\t\tmatch (publisher)-[:Published]->(recommend) \n" +
			"\t\t\t\tWHERE not recommend in viewedNews\n" +
			"\t\t\t\t\treturn DISTINCT recommend")
	Collection<Post> recommend(@Param("userName") String userName);


	@Query("MATCH (l:Location) where l.name={name} MATCH (p:Post)-[:NEWS_LOCATION]->(l) return p")
    Collection<Post> byLocation(@Param("name") String name);

	@Query("MATCH (c:SubCategory) where c.name={categoryName} MATCH (p:Post)-[:BelongsTo]->(c) return p")
	Collection<Post> byCategory(@Param("categoryName") String categoryName);

	//Most liked category
	@Query("MATCH (user:User)-[:LIKED]->(n:Post) where user.username={userName}\n" +
			"MATCH (n)-[:BelongsTo]->(subcategory)\n" +
			"\t\tWITH subcategory.name as mostLiked, count(subcategory) as total, collect(n) as viewedNews\n" +
			"\t\torder by count(subcategory) DESC LIMIT 1\n" +
			"\n" +
			"MATCH (recommend:Post)-[:BelongsTo]->(sub :SubCategory) where sub.name=mostLiked and not recommend in viewedNews\n" +
			"return distinct recommend")
	Collection<Post> mostLikedCategory(@Param("userName")String userName);

	//Most viewed category
	@Query("MATCH (user:User)-[:VIEWED]->(n:Post) where user.username={userName}\n" +
			"MATCH (n)-[:BelongsTo]->(subcategory)\n" +
			"\t\tWITH subcategory.name as mostViewed, count(subcategory) as total, collect(n) as viewedNews\n" +
			"\t\torder by count(subcategory) DESC LIMIT 1\n" +
			"\n" +
			"MATCH (recommend:Post)-[:BelongsTo]->(sub:SubCategory) where sub.name = mostViewed\n" +
			"and not recommend in viewedNews\n" +
			"return distinct recommend")
	Collection<Post> mostViewedCategory(@Param("userName")String userName);

	//Recommend by location and other profile parameters
	@Query("MATCH (user:User)-[:VIEWED]->(n:Post) where user.username={userName} WITH collect(n) as viewedNews\n" +
			"MATCH (user:User)-[:userLocation]->(l:Location) where user.username={userName}\n" +
			"MATCH (recommend:Post)-[:NEWS_LOCATION]->(l)\n" +
			"\t\twhere not recommend in viewedNews\n" +
			"OPTIONAL MATCH ()-[r:VIEWED]->(recommend)\n" +
			"\t\treturn recommend, count(r)\n" +
			"\t\torder by count(r) desc")
	Collection<Post> byProfile(@Param("userName") String userName);
}
