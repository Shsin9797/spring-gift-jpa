package gift.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "token")
public class Token {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String tokenValue;

    public Token() {
    }

    public Token(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Token(Long newId, String tokenValue) {
        this.id = newId;
        this.tokenValue = tokenValue;
    }

    public Long getId() {
        return id;
    }

    public String getTokenValue() {
        return tokenValue;
    }
}
