package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Eq;

@PageQuery
public class EqQuery {
    @Eq(value = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public EqQuery setId(Integer id) {
        this.id = id;
        return this;
    }
}
