package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.In;

import java.util.List;

@PageQuery
public class InQuery {
    @In(value = "id")
    private List<Integer> id;

    public List<Integer> getId() {
        return id;
    }

    public InQuery setId(List<Integer> id) {
        this.id = id;
        return this;
    }
}
