package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Gt;

@PageQuery
public class GtQuery {
    @Gt(value = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public GtQuery setId(Integer id) {
        this.id = id;
        return this;
    }
}
