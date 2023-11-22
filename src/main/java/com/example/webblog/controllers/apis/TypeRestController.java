package com.example.webblog.controllers.apis;

import com.example.webblog.models.Type;
import com.example.webblog.servies.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/types")
public class TypeRestController {

    @Autowired
    private ITypeService typeService;

    @GetMapping
    public ResponseEntity<List<Type>> getTypes() {
        return ResponseEntity.ok((List<Type>) typeService.findAll());
    }

    @GetMapping("/{typeId}")
    public ResponseEntity<Type> getTypeById(@PathVariable("typeId") Long typeId) {
        Optional<Type> type = typeService.findById(typeId);
        if (type.isPresent()) {
            return ResponseEntity.ok(type.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Type> createType(@RequestBody Type type) {
        type.setCreateAt(new Date());
        Type typeExist = typeService.getTypeByName(type.getTypeName());
        if (typeExist != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(typeService.save(type));
    }

    @PutMapping("/{typeId}")
    public ResponseEntity<Type> updateType(@PathVariable("typeId") Long typeId, @RequestBody Type typeRequest) {
        Optional<Type> type = typeService.findById(typeId);
        if (type.isPresent()) {
            Type typeExist = typeService.getTypeByName(typeRequest.getTypeName());
            if (typeExist != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            type.get().setTypeName(typeRequest.getTypeName());
            return ResponseEntity.ok(typeService.save(type.get()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity<Boolean> deleteType(@PathVariable("typeId") Long typeId) {
        boolean isDeleted = typeService.remove(typeId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}