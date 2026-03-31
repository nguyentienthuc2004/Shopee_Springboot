package org.thuc.shoppe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "temporary_table")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyTest;

    private String valueTest;
}
