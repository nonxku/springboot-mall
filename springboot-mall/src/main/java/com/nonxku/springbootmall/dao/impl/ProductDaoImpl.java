package com.nonxku.springbootmall.dao.impl;

import com.nonxku.springbootmall.constant.ProductCategory;
import com.nonxku.springbootmall.dao.ProductDao;
import com.nonxku.springbootmall.dto.ProductQueryParams;
import com.nonxku.springbootmall.dto.ProductRequest;
import com.nonxku.springbootmall.model.Product;
import com.nonxku.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //提煉重複的sql語句，將查詢條件獨立成一個方法！
    private String addFliteringSql(String sql,
                                   Map<String, Object> map,
                                   ProductQueryParams productQueryParams){

        //查詢條件，唯獨查詢條件不為null才做拼接語句(拼接語句記得加上空白鍵！）：
        if(productQueryParams.getCategory()!=null){
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }

        if(productQueryParams.getSearch()!=null){
            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%"+productQueryParams.getSearch()+"%");
        }

        return sql;
    }


    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "select count(*) from product where 1=1";

        Map<String, Object> map = new HashMap<>();

        //利用把拼接語句提煉成方法，拿來使用
       sql = addFliteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date " +
                "FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();


        //利用把拼接語句提煉成方法，拿來使用
        sql = addFliteringSql(sql, map, productQueryParams);

        //排序
        //因為在controller已經有default值的設定了，所以就不用判斷是否為null:
        sql = sql + " ORDER BY "+productQueryParams.getOrderBy()+" "+productQueryParams.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffSet());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());


        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {

        String sql = "select product_id, product_name, category, image_url, price, stock, description,created_date, last_modified_date from product where product_id =:productId";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size()>0){
            return productList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql="INSERT INTO product(product_name, category, image_url, price, stock," +
                " description, created_date, last_modified_date) " +
                "VALUES (:productName,:category, :imageUrl, :price, :stock, :description," +
                " :createdDate, :lastModifiedDate)";

        Map<String,Object> map=new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().name());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now =new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql ="UPDATE product SET product_name=:productName, " +
                "category=:category, " +
                "image_url=:imageUrl," +
                "price=:price," +
                "stock=:stock,"+
                "description=:description," +
                "last_modified_date=:lastModifiedDate WHERE product_id=:productId";

        Map<String,Object> map=new HashMap<>();
        map.put("productId",productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().name());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        //表示儲存商品數據最後修改的時間，紀錄當下的時間點
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        String sql = "UPDATE product SET stock=:stock, last_modified_date=:lastModifiedDate" +
                " WHERE product_id=:productId";

        Map<String,Object> map=new HashMap<>();
        map.put("productId", productId);
        map.put("stock", stock);
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql="DELETE FROM product WHERE Product_Id=:productId";
        Map<String,Object> map=new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql,map);

    }


}
