package gift.repository;

import gift.dto.EditProduct;
import gift.entity.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    public Product save(Product product) {
        Map<String, Object> parameters = Map.of(
                "name", product.getName(),
                "url", product.getUrl(),
                "price", product.getPrice()
        );
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        Long id = newId.longValue();
        return new Product(id, product.getName(), product.getPrice(), product.getUrl());
    }

    public List<Product> findAll() {
        var sql = "select * from products";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                )
        );
    }

    public Product findOneById(Long id) {
        var sql = "select name,price,url from products where id=?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Product(
                        id,
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                ),
                id
        );
    }

    public Product update(Long id, Product newProduct) {
        try {
            var sql = "update products set name=?, price=?, url=? where id=?";
            jdbcTemplate.update(sql, newProduct.getName(), newProduct.getPrice(), newProduct.getUrl(), id);
            return new Product(id, newProduct.getName(), newProduct.getPrice(), newProduct.getUrl());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Long delete(Long id) {
        try {
            var sql = "delete from products where id= ?";
            jdbcTemplate.update(sql, id);
            return id;
        } catch (DataAccessException e) {
            return null;
        }
    }
}