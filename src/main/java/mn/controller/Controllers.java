package mn.controller;

import mn.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import mn.service.IProductService;

import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class Controllers {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView displayProduct(){
        ModelAndView modelAndView = new ModelAndView("listProduct");
        ArrayList<Product> products = productService.getAllProduct();
        if (products.isEmpty()){
            modelAndView.addObject("message", "Don't have product");
            modelAndView.addObject("color", "red");
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/deleteProduct/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("listProduct");
        Product product = productService.deleteProduct(id);
        if (product == null){
            modelAndView.addObject("message", "Id invalid");
        }
        ArrayList<Product> products = productService.getAllProduct();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
   @GetMapping("/createProduct")
    public ModelAndView createProduct(Model model){
        ModelAndView modelAndView = new ModelAndView("createProduct");
        model.addAttribute("product", new Product());
        return modelAndView;
   }
   @PostMapping
    public ModelAndView createProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("createProduct");
        Product productCreated = productService.saveProduct(product);
        if (productCreated != null){
            modelAndView.addObject("message", "Create Success^^");
        }
        return modelAndView;
   }

   @GetMapping("/editProduct/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("editProduct");
        Product product = productService.getProduct(id);
        if (product != null){
            modelAndView.addObject("product", product);
        }else {
            modelAndView.addObject("message", "id invalid :(");
        }
        return modelAndView;
   }
    @PostMapping("/{id}")
    public ModelAndView editProduct(@ModelAttribute Product product, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("editProduct");
        product.setId(id);
        Product productEditor = productService.saveProduct(product);
        if (productEditor != null){
            modelAndView.addObject("message", "Update success^^");
        }
        return modelAndView;
    }
    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam(name = "search",required = false) String name){
        ModelAndView modelAndView = new ModelAndView("index");
        ArrayList<Product> products;
        if (name.equals("")){
            products = productService.getAllProduct();
        } else {
            products = productService.findByName(name);
        }
        modelAndView.addObject("products",products);
        return modelAndView;
    }
}
