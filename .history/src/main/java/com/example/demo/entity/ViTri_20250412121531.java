package com.example.demo.entity;

@Entity
@Table(name = "\"bophan\"") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoPhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idbp\"")
    private Long id;

    @Column(name = "\"tenbp\"")
    private String name;

    public Long getId() {
        return this.id;
    }
        
    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public void setName(String name) {
        this.name = name; 
    }