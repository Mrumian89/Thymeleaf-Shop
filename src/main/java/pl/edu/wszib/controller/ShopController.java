package pl.edu.wszib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.dao.ProductDao;
import pl.edu.wszib.dao.UserDao;
import pl.edu.wszib.domain.Product;
import pl.edu.wszib.domain.User;

import javax.annotation.security.PermitAll;
import java.util.Map;

@Controller
public class ShopController {

    @Autowired
    private UserDao userDao;

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
    public String users(Model model) {
        model.addAttribute("users", userDao.getUsers());
        return "users";
    }

    @GetMapping("users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("users/save")
    public String saveNewUser(User user) {
        userDao.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userDao.getById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("users/remove/{id}")
    public String removeUser(@PathVariable Long id) {
        userDao.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("users/deactivate")
    public String deactivate(User user, Model model, Long id) {
        model.addAttribute("users", userDao.getUsers());
        userDao.deactivateUsers(id, user);
        return "redirect:/users";
    }

}