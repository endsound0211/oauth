package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.NotIn;

import java.util.List;

@PageQuery
public class NotInQuery {
    @NotIn(value = "id")
    private List<Integer> id;

    public List<Integer> getId() {
        return id;
    }

    public NotInQuery setId(List<Integer> id) {
        this.id = id;
        return this;
    }
}
