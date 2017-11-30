package com.niit.frontend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.musicstorebackend.dao.ICartDAO;
import com.niit.musicstorebackend.dao.ICartItemDAO;
import com.niit.musicstorebackend.dao.ICategoryDAO;
import com.niit.musicstorebackend.dao.IProductDAO;
import com.niit.musicstorebackend.dao.ISupplierDAO;
import com.niit.musicstorebackend.dao.IUserDAO;
import com.niit.musicstorebackend.model.Cart;
import com.niit.musicstorebackend.model.CartItem;
import com.niit.musicstorebackend.model.Category;
import com.niit.musicstorebackend.model.Product;
import com.niit.musicstorebackend.model.Supplier;
import com.niit.musicstorebackend.model.User;

@Controller
public class AdminController {

	@Autowired
	ICategoryDAO categoryDAO;

	@Autowired
	Category category;

	@Autowired
	Product product;

	@Autowired
	IProductDAO productDAO;

	@Autowired
	Supplier supplier;

	@Autowired
	ISupplierDAO supplierDAO;

	@Autowired
	ICartDAO cartDAO;
	@Autowired
	Cart cart;

	@Autowired
	IUserDAO userDAO;

	@Autowired
	User user;

	@Autowired
	ICartItemDAO cartItemDAO;

	@ModelAttribute
	public Category returnObject1() {
		return new Category();

	}

	@ModelAttribute
	public Product returnObject3() {
		return new Product();

	}

	@ModelAttribute
	public Supplier returnObject2() {
		return new Supplier();

	}

	// ---------------------------Category Operations  // ------------------------------------------
	@RequestMapping("/addCategory")
	public ModelAndView ShowAddCategory(Model model) {
		ModelAndView mv = new ModelAndView("addCategory");

		model.addAttribute("categoryList", categoryDAO.getCategories());
		System.out.println("added category details  in controller");

		return mv;
	}

	@RequestMapping(value = "/addcat", method = RequestMethod.POST)
	public String addCate(@ModelAttribute("category") Category cate) {
		if (cate.getCatid() == 0) {
			// new category, add it

			categoryDAO.saveCategory(cate);
			System.out.println("adding of new category in controller");
		} else {
			// existing category, call update

			categoryDAO.update(cate);
			System.out.println("addcategory update method of category in controller");
		}

		return "redirect:/addCategory";

	}

	
	/*@RequestMapping(value="/editcategory/{id}")
	public ModelAndView updateCategory(@PathVariable("id")String id,Model model)
	{
	int i=Integer.parseInt(id);
	model.addAttribute("category", categoryDAO.get(i));
	model.addAttribute("categoryList", categoryDAO.getCategories());
	ModelAndView mv=new ModelAndView("addCategory");
	return mv;
	}
*/
	@RequestMapping("/remove/{id}")
    public String removeCategory(@PathVariable("id") int id){
		System.out.println("remove category"+id);
        this.categoryDAO.deleteCategory(id);
        return "redirect:/addCategory";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("category", this.categoryDAO.get(id));
        //categoryDAO.update(category);
        model.addAttribute("categoryList", this.categoryDAO.getCategories());
        return "addCategory";
    }
	

	// -------------------------------------Product Operations	// ----------------------------------------
	@RequestMapping("/AddProduct")
	public ModelAndView showProduct(Model model) {
		ModelAndView m = new ModelAndView("AddProduct");
		model.addAttribute("categoryList", categoryDAO.getCategories());
		model.addAttribute("supplierList", supplierDAO.list());
		model.addAttribute("productList", productDAO.list());
		return m;
	}

	@RequestMapping(value = "/addprod", method = RequestMethod.POST)
	public String ShowAddProduct(@ModelAttribute("product") Product prod, Model model, BindingResult result,
			HttpServletRequest request) throws IOException {
		System.out.println("in add prod");
		if (result.hasErrors())

		{
			return "AddProduct";
		} else {
			System.out.println(prod.getProdname());
			System.out.println("image uploaded");
			System.out.println("myproduct controller called");
			MultipartFile image = prod.getImg();
			Path path;// belong to nio package
			path = Paths
					.get("C:/Users/Sandhya/git/musicstorefront/src/main/webapp/pics/" + prod.getProdname() + ".jpg");
			System.out.println("Path=" + path);
			System.out.println("File name" + prod.getImg().getOriginalFilename());
			if (image != null && !image.isEmpty()) {
				try {
					image.transferTo(new File(path.toString()));
					System.out.println("Image Saved in:" + path.toString());
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Image not saved");

				}
			}

			if (prod.getProdid() == 0) {
				// new product
				productDAO.saveProduct(prod);
			} else {
				productDAO.saveProduct(prod);
				return "redirect:/AddProduct";
			}

			model.addAttribute("message", "product added successfully");
			model.addAttribute("productList", productDAO.list());
			model.addAttribute("categoryList", categoryDAO.getCategories());
			model.addAttribute("supplierList", supplierDAO.list());

			return "redirect:/AddProduct";
		}
	}

	@RequestMapping("/allproducts")
	public @ResponseBody List<Product> productsall() {
		System.out.println("inside products all");
		return productDAO.list();

	}

	@RequestMapping("/viewproducts")
	public String showDetails(Model mp) {
		return "viewproducts";
	}

	// ---------------------------------Supplier operations
	// ---------------------------------------

	/*
	 * @RequestMapping("/AddSuppler") public String showSupplier() { return
	 * "AddSupplier"; }
	 */

	@RequestMapping("/AddSupplier")
	public ModelAndView ShowAddSupplier(Model model) {
		ModelAndView mv = new ModelAndView("AddSupplier");

		model.addAttribute("supplierList", supplierDAO.list());
		System.out.println("added supplier details  in controller");

		return mv;
	}

	@RequestMapping(value = "/addsup", method = RequestMethod.POST)
	public String addSupp(@ModelAttribute("supplier") Supplier su) {
		if (su.getSupplierid() == 0) {
			// new supplier, add it

			supplierDAO.saveorUpdate(su);
			System.out.println("adding of new supplier in controller");
		} else {
			// existing supplier, call update

			supplierDAO.saveorUpdate(su);
			System.out.println("addsup update method of supplier in controller");
		}

		return "redirect:/AddSupplier";

	}

	/* delete supplier... */
	@RequestMapping(value = "/deletesupplier{id}")
	public String showDeleteSupplier(@PathVariable("id") String id, Model model) throws Exception {

		int i = Integer.parseInt(id);

		supplier = supplierDAO.get(i);

		System.out.println("supplier deleted");

		ModelAndView mv = new ModelAndView("AddSupplier");

		supplierDAO.delete(supplier);
		// mv.addObject("Addsupplier");
		model.addAttribute("supplierList", supplierDAO.list());

		System.out.println("delete Id:" + id);

		return "redirect:/AddSupplier";

	}

	@RequestMapping(value = "/editsupplier{id}")
	public ModelAndView UpdatesuppPage(@PathVariable("id") String id, Model model) throws Exception {
		int i = Integer.parseInt(id);
		model.addAttribute("supplier", supplierDAO.get(i));
		supplier = supplierDAO.get(i);
		System.out.println("In supplier edit " + supplier.getSupplierid());
		supplierDAO.update(supplier);
		model.addAttribute("supplierList", supplierDAO.list());
		System.out.println("edit supplier in controller");
		ModelAndView mv = new ModelAndView("AddSupplier");
		return mv;

	}
	// -------------------------------------- Cart
	// Operations-------------------------------------------------------------------

	/*
	 * @RequestMapping("/AddToCart") public String showCart(Model mp) { return
	 * "AddToCart"; }
	 */

	@RequestMapping("/{id}/AddToCart")
	public String showDetails(@PathVariable Integer id, ModelMap model) {

		model.addAttribute("cart", cartDAO.getbyid(id));

		return "AddToCart";

	}

	@RequestMapping(value = "/{id}/cartitemdelete")
	public String showDeleteCart(@PathVariable("id") String id, Model model) throws Exception {

		int i = Integer.parseInt(id);

		/*
		 * cart =cartDAO.getbyid(i);
		 * 
		 * System.out.println("cart delete");
		 * 
		 * //ModelAndView mv = new ModelAndView("viewproducts");
		 * 
		 * cartDAO.delete(cart);
		 */ // mv.addObject("viewproducts", 0);
		CartItem ct = cartItemDAO.getCartItem(i);
		boolean b = cartItemDAO.deleteCartItem(ct);
		if (b == true) {
			System.out.println("delete Id:" + id);
		} else {
			System.out.println("Not deleted");
		}

		return "addtocart";
		// return mv;

	}

	@RequestMapping(value = "/{id}/editcart")
	public ModelAndView updateCartPage(@PathVariable("id") String id, Model model) throws Exception {
		int i = Integer.parseInt(id);

		model.addAttribute("product", cartDAO.getbyid(i));

		/*
		 * model.addAttribute("productList", productDAO.list());
		 * model.addAttribute("supplierList", supplierDAO.list());
		 * model.addAttribute("categoryList", categoryDAO.list());
		 */
		cartDAO.update(cart);
		System.out.println("edit cart in controller");
		ModelAndView mv = new ModelAndView("addtocart");
		return mv;

	}

	/*
	 * @RequestMapping(value = "/{id}/buy", method = RequestMethod.POST) public
	 * ModelAndView buyproductPage(@PathVariable("id") String
	 * id, @PathVariable("pid") String pid,
	 * 
	 * @RequestParam("quantity") int quantity, HttpSession session) throws
	 * Exception {
	 * 
	 * int i=Integer.parseInt(id); int ppid=Integer.parseInt(pid);
	 * user=userDAO.getbyid(i); ModelAndView mv = new ModelAndView("addtocart");
	 * //int k = Integer.parseInt(quantity); int y = 0; Cart kcart = new Cart();
	 * for (Cart temp : cartDAO.listcartproducts(i)) { if
	 * (temp.getProdid()==ppid) { y = 1; kcart = temp; } } if (y == 1) {
	 * kcart.setQuantity(kcart.getQuantity() + quantity);
	 * kcart.setPrice(kcart.getQuantity() * kcart.getCartproduct().getPrice());
	 * cartDAO.update(kcart); } else { cart.setQuantity(quantity);
	 * cart.setUserid(i); cart.setProdid(ppid);
	 * cart.setCartuser(userDAO.getbyid(i)); product = productDAO.get(ppid);
	 * cart.setCartproduct(product); cart.setPrice(cart.getQuantity() *
	 * product.getPrice()); cartDAO.save(cart); } mv.addObject("cartList",
	 * cartDAO.listcartproducts(i)); mv.addObject("cartprice",
	 * cartDAO.totalprice(cart.getUserid())); return mv; }
	 */

	/*
	 * @RequestMapping("/Cart") public String showCart() { return "Cart"; }
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/{id}/Cart")
	public String addCart(@PathVariable Integer id, Principal principal, ModelMap model) {
		User user = userDAO.get(principal.getName());
		user.setCpassword(user.getPassword());
		System.out.println(user.getPassword());
		Product product = productDAO.get(id);
		System.out.println(product.getProdname());
		Cart cart = cartDAO.getCartWithUserId(user.getUserid());
		System.out.println("prodid" + product.getProdid() + " cart id" + cart.getCartid());
		if (cart != null) {
			cart.setUsers(user);

			CartItem cartItem = cartItemDAO.getExistingCartItemCount(id, cart.getCartid());
			if (cartItem != null) {
				cartItem.setCart(cart);
				cartItem.setGrandtotal(cartItem.getGrandtotal() + product.getPrice());
				cartItem.setQty(cartItem.getQty() + 1);
				cartItemDAO.updateCartItem(cartItem);
			} else {
				cartItem = new CartItem();
				cartItem.setCart(cart);
				cartItem.setGrandtotal(product.getPrice());
				cartItem.setProduct(product);
				cartItem.setQty(1);
				cartItemDAO.addCartItem(cartItem);
			}
			cart.setGrandtotal(cart.getGrandtotal() + product.getPrice());
			cart.setQty(cart.getQty() + 1);
			List<CartItem> cartItems = cart.getCartitems();
			cartItems.add(cartItem);
			cart.setCartitems(cartItems);
			cartDAO.update(cart);

		} else {
			cart = new Cart();
			cart.setGrandtotal(product.getPrice());
			cart.setQty(1);
			cart.setUsers(user);
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setGrandtotal(product.getPrice());
			cartItem.setProduct(product);
			cartItem.setQty(1);

			cartDAO.save(cart);
			cartItemDAO.addCartItem(cartItem);
		}

		model.addAttribute("mycartList", cartItemDAO.getAll());
		return "Cart";
	}

}
