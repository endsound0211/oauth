
import bean.EqQuery;
import bean.InQuery;
import com.endsound.pagination.bean.Page;
import com.endsound.pagination.bean.PageParam;
import com.google.gson.Gson;
import dao.UserDao;
import org.jooq.tables.pojos.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class PaginationTest {
    @Autowired
    private UserDao userDao;


    @Test
    public void eqQueryTest(){
        EqQuery userQuery = new EqQuery().setId(1);

        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(userQuery));


        Page<User> page = userDao.queryTest(pageParam, EqQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", is(1))));
    }

    @Test
    public void inQueryTest(){
        InQuery inQuery = new InQuery().setId(Arrays.asList(1, 3));

        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(inQuery));

        Page<User> page = userDao.queryTest(pageParam, InQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", anyOf(is(1), is(3)))));
    }
}
