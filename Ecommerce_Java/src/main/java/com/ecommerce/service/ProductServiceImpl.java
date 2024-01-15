package com.ecommerce.service;

import com.ecommerce.dao.CategoryDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.exception.ProductException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.request.CreateProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;
    @Override
    public Product createProduct(CreateProductRequest req) throws ProductException {
        log.info("crate product {}",req);

        Category topLevel=categoryDao.findByName(req.getTopLevelCategory());

        if(topLevel==null) {

            Category topLavelCategory=new Category();
            topLavelCategory.setName(req.getTopLevelCategory());
            topLavelCategory.setLevel(1);

            topLevel= categoryDao.save(topLavelCategory);
        }

        Category secondLevel=categoryDao.
                findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
        if(secondLevel==null) {

            Category secondLavelCategory=new Category();
            secondLavelCategory.setName(req.getSecondLevelCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);

            secondLevel= categoryDao.save(secondLavelCategory);
        }

        Category thirdLevel=categoryDao.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel==null) {

            Category thirdLavelCategory=new Category();
            thirdLavelCategory.setName(req.getThirdLevelCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);

            thirdLevel=categoryDao.save(thirdLavelCategory);
        }


        Product product=new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPercent(req.getDiscountPersent());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct= productDao.save(product);

        log.info("products{}", product);

        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {

        Product product=findProductById(productId);

        log.info("delete product {}",product.getId()+" - "+productId);
        product.getSizes().clear();
        productDao.delete(product);

        return "Product deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product=findProductById(productId);

        if(req.getQuantity()!=0) {
            product.setQuantity(req.getQuantity());
        }
        if(req.getDescription()!=null) {
            product.setDescription(req.getDescription());
        }




        return productDao.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();

    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt=productDao.findById(id);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new ProductException("product not found with id "+id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        log.info("category {}",category);

        return productDao.findByCategory(category);
    }

    @Override
    public List<Product> searchProduct(String query) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productDao.filterProducts(category, minPrice, maxPrice, minDiscount, sort);


        if (!colors.isEmpty()) {
            products = products.stream()
                    .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());

        }

        if(stock!=null) {
            if(stock.equals("in_stock")) {
                products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }
            else if (stock.equals("out_of_stock")) {
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
        return filteredProducts; // If color list is empty, do nothing and return all products

    }
}
