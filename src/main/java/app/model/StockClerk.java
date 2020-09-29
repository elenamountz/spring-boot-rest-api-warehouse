package app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stock_clerk")
public class StockClerk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registry_number")
    private String registryNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
