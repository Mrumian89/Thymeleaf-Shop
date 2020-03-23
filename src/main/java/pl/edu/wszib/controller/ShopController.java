package pl.edu.wszib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.dao.ProductDao;
import pl.edu.wszib.domain.Product;

@Controller
public class ShopController {

    @Autowired
    private ProductDao productDao;

    @GetMapping
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping("shipping")
    public String shipping(Model model) {
        model.addAttribute("shippingMethodName1", "Paczkomat");
        model.addAttribute("shippingMethodPrice1", 10.50);
        model.addAttribute("shippingMethodName2", "Kurier - przedpłata");
        model.addAttribute("shippingMethodPrice2", 15.00);
        model.addAttribute("shippingMethodName3", "Kurier - pobranie");
        model.addAttribute("shippingMethodPrice3", 20.00);
        return "shipping";
    }

    @GetMapping("products")
    public String products(Model model) {
        model.addAttribute("productList", productDao.getProducts());
        return "products";
    }

    @GetMapping("products/remove/{id}")
    public String removeProduct(@PathVariable Long id) {
        productDao.removeProduct(id);
        return "redirect:/products";
    }

    @GetMapping("products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("products/save")
    public String saveNewProduct(Product product) {
        productDao.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productDao.getById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("users")
    public String Users(){
        return "users";
    }


}
