package com.example.webblog.servies.type;

import com.example.webblog.models.Type;
import com.example.webblog.servies.IGeneralService;

public interface ITypeService extends IGeneralService<Type> {
    Type getTypeByName(String name);
}
