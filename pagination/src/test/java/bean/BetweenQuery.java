package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Between;
import com.endsound.pagination.bean.BetweenParam;

@PageQuery
public class BetweenQuery {
    @Between(value = "id")
    private BetweenParam<Integer> id;

    public BetweenParam<Integer> getId() {
        return id;
    }

    public BetweenQuery setId(BetweenParam<Integer> id) {
        this.id = id;
        return this;
    }
}
