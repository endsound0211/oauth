package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Le;

@PageQuery
public class LeQuery {
    @Le(value = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public LeQuery setId(Integer id) {
        this.id = id;
        return this;
    }
}
