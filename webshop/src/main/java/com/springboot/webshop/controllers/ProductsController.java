package com.springboot.webshop.controllers;


import com.springboot.webshop.models.Brand;
import com.springboot.webshop.models.Category;
import com.springboot.webshop.models.Product;
import com.springboot.webshop.models.ProductDto;
import com.springboot.webshop.repositories.BrandsRepository;
import com.springboot.webshop.repositories.CategoriesRepository;
import com.springboot.webshop.repositories.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository repo;
    private CategoriesRepository cate;
    private BrandsRepository bra;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ){
        MultipartFile imageFile = productDto.getImageFile();

        if (imageFile == null || imageFile.isEmpty()) {
            result.addError(new FieldError("productDto", "imageFile", "Image file is required"));
        }

        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        //Save image file
        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storedFileName = image.getOriginalFilename();
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storedFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        //product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
       // product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storedFileName);

        repo.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam String id
    ) {
        try{
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);
           // Brand brand = BrandsRepository.findById(product.getBrand().getBrand_id());
           // model.addAttribute("brand", brand);
           // model.addAttribute("category", category);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            //productDto.setBrand(product.getBrand());
            productDto.setPrice(product.getPrice());
           // productDto.setCategory(product.getCategory());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto", productDto);
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model,
            @RequestParam String id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
            ){

        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()){
            return "products/EditProduct";
            }

            if (!productDto.getImageFile().isEmpty()) {
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
                //Save image file
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = image.getOriginalFilename();
                try(InputStream inputStream = image.getInputStream()){
                        Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                    }

                product.setImageFileName(storageFileName);
                }

            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            repo.save(product);
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam String id
    ){
        try{
            Product product = repo.findById(id).get();

            Path imagePath = Paths.get("public/images/" + product.getImageFileName());
            try{
                Files.delete(imagePath);
            }
            catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());

            }
            repo.delete(product);
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }

}
