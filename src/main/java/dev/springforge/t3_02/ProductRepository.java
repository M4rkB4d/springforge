package dev.springforge.t3_02;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * MongoDB Repository — works like JpaRepository but for MongoDB.
 *
 * Derived query methods work the same way as JPA:
 * findByCategory -> { "category": value }
 * findByPriceLessThan -> { "price": { "$lt": value } }
 *
 * @Query uses JSON filter syntax (not JPQL).
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategory(String category);

    List<Product> findByAvailableTrue();

    List<Product> findByPriceLessThan(BigDecimal maxPrice);

    List<Product> findByTagsContaining(String tag);

    // MongoDB JSON query — note: this is a JSON filter, not JPQL
    @Query("{ 'category': ?0, 'price': { '$lte': ?1 }, 'available': true }")
    List<Product> findAvailableInCategoryUnderPrice(String category, BigDecimal maxPrice);
}
