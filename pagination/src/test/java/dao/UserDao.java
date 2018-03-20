package dao;

import com.endsound.pagination.PDSL;
import com.endsound.pagination.bean.Page;
import com.endsound.pagination.bean.PageParam;
import org.jooq.Configuration;
import org.jooq.Tables;
import org.jooq.tables.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends org.jooq.tables.daos.UserDao{
    @Autowired
    public UserDao(@Qualifier("config")Configuration configuration){
        super(configuration);
    }


    public Page<User> queryTest(PageParam pageParam, Class clazz){


        return PDSL.using(configuration(), pageParam)
                .select().from(Tables.USER)
                .query(clazz)
                .orderBy()
                .fetchInto(User.class);
    }
}
