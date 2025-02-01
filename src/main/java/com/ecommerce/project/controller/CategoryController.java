package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/api") // OPTIONAL: this says that all api methods start with "/api"
// Useful in teams for organization patterns!!!
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
/////// Learning purpose for Postman   ///////
//    @GetMapping("/api/echo")
//    public ResponseEntity<String> echoMessage(@RequestParam(name = "message", required = false) String message) {
//    //public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "Hello World!") String message) {
//        return new ResponseEntity<>("Echoed message: " + message, HttpStatus.OK);
//    }

    //@RequestMapping(value = "/api/public/categories", method = RequestMethod.GET) // OPTIONAL: which is same as below
    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        //return categoryService.getAllCategories();  // Return below handles HttpStatus code
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        //return "Category added successfully";  // Return below handles HttpStatus code
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
//       try { // X_NOTE: Should just be pass through with controller and not have logic with the try/catch
//            String status = categoryService.deleteCategory(categoryId);
//            return new ResponseEntity<>(status, HttpStatus.OK);  // Most common way to return
//            //return ResponseEntity.ok(status);
//            //return ResponseEntity.status(HttpStatus.OK).body(status);
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//        }
        CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId); // X_NOTE: Cleaner this way
        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);

    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
//        try { // X_NOTE: Should just be pass through with controller and not have logic with the try/catch
//            Category savedCategory = categoryService.updateCategory(category, categoryId);
//            return new ResponseEntity<>("Updated category with category id: " + categoryId, HttpStatus.OK);
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//        }

        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}
