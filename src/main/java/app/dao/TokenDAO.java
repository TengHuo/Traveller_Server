package app.dao;

import app.entity.TokenEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhujay on 16/6/17.
 */
@Transactional
@Component
public interface TokenDAO extends CrudRepository<TokenEntity,String> {
    public TokenEntity findByToken(String token);
    public TokenEntity findByUserId(String userId);


    //    @Query("select te from TokenEntity te , UserEntity ue where ue.name=:name and te.userId = ue.id")

    @Transactional
    @Modifying
    @Query("update TokenEntity as te set te.exp = :_exp where te.userId in(select ue.id from UserEntity ue where ue.name = :username)")
    public int updateToken(@Param("username") String username,@Param("_exp") long _exp);

}
