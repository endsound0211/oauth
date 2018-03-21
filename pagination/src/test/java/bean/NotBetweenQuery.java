package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.NotBetween;
import com.endsound.pagination.bean.BetweenParam;

@PageQuery
public class NotBetweenQuery {
    @NotBetween(value = "id")
    private BetweenParam<Integer> id;

    public BetweenParam<Integer> getId() {
        return id;
    }

    public NotBetweenQuery setId(BetweenParam<Integer> id) {
        this.id = id;
        return this;
    }
}
