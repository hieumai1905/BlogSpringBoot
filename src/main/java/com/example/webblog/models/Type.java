package com.example.webblog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "types")
@Data
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    public Type(String typeName){
        this.typeName = typeName;
        this.createAt = new Date();
    }
}