
import bean.*;
import com.endsound.pagination.bean.BetweenParam;
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

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", isIn(Arrays.asList(1, 3)))));
    }

    @Test
    public void likeQueryTest() throws Exception {
        LikeQuery likeQuery = new LikeQuery().setUsername("nds");
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(likeQuery));

        Page<User> page = userDao.queryTest(pageParam, LikeQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("username", containsString("nds"))));
    }

    @Test
    public void neQueryTest() throws Exception {
        NeQuery neQuery = new NeQuery().setId(1);

        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(neQuery));


        Page<User> page = userDao.queryTest(pageParam, NeQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", not(1))));

    }

    @Test
    public void notInQueryTest(){
        NotInQuery notInQuery = new NotInQuery().setId(Arrays.asList(1, 3));

        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(notInQuery));

        Page<User> page = userDao.queryTest(pageParam, NotInQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", not(isIn(Arrays.asList(1, 3))))));
    }

    @Test
    public void notLikeQueryTest() throws Exception {
        NotLikeQuery notLikeQuery = new NotLikeQuery().setUsername("nds");
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(notLikeQuery));

        Page<User> page = userDao.queryTest(pageParam, NotLikeQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("username", not(containsString("nds")))));
    }

    @Test
    public void ltQueryTest() throws Exception {
        LtQuery ltQuery = new LtQuery().setId(3);
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(ltQuery));

        Page<User> page = userDao.queryTest(pageParam, LtQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", lessThan(3))));
    }

    @Test
    public void leQueryTest() throws Exception {
        LeQuery leQuery = new LeQuery().setId(2);
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(leQuery));

        Page<User> page = userDao.queryTest(pageParam, LeQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", lessThanOrEqualTo(2))));
    }

    @Test
    public void gtQueryTest() throws Exception {
        GtQuery gtQuery = new GtQuery().setId(1);
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(gtQuery));

        Page<User> page = userDao.queryTest(pageParam, GtQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", greaterThan(1))));
    }

    @Test
    public void geQueryTest() throws Exception {
        GeQuery geQuery = new GeQuery().setId(2);
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(geQuery));

        Page<User> page = userDao.queryTest(pageParam, GeQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", greaterThanOrEqualTo(2))));
    }

    @Test
    public void betweenQueryTest() throws Exception{
        BetweenQuery betweenQuery = new BetweenQuery().setId(new BetweenParam<Integer>().setStart(1).setEnd(2));
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(betweenQuery));

        Page<User> page = userDao.queryTest(pageParam, BetweenQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", isIn(Arrays.asList(1, 2)))));
    }

    @Test
    public void notBetweenQueryTest() throws Exception{
        NotBetweenQuery notBetweenQuery = new NotBetweenQuery().setId(new BetweenParam<Integer>().setStart(1).setEnd(2));
        PageParam pageParam = new PageParam()
                .setLimit(10)
                .setOffset(0)
                .setQuery(new Gson().toJson(notBetweenQuery));

        Page<User> page = userDao.queryTest(pageParam, NotBetweenQuery.class);

        Assert.assertThat(page.getRows(), everyItem(hasProperty("id", not(isIn(Arrays.asList(1, 2))))));
    }
}
