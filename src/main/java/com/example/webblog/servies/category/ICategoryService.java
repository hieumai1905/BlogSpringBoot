package com.example.webblog.servies.category;

import com.example.webblog.models.Category;
import com.example.webblog.servies.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    Category getCategoryByName(String name);
}
