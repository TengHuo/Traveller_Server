package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zhujay on 16/6/17.
 */
@Entity
@Table(name = "token", schema = "traveller", catalog = "")
public class TokenEntity {
    @Id
    @GeneratedValue(generator = "tokenGenerator")
    @GenericGenerator(name = "tokenGenerator",strategy = "uuid2")
    @Column(name = "token")
    private String token;
    private String userId;
    private long exp;

    public TokenEntity() {
    }

    public TokenEntity(String userId, long exp) {

        this.userId = userId;
        this.exp = exp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "exp")
    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenEntity that = (TokenEntity) o;

        if (exp != that.exp) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (int) (exp ^ (exp >>> 32));
        return result;
    }


}
