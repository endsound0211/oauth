package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Ne;

@PageQuery
public class NeQuery {
    @Ne(value = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public NeQuery setId(Integer id) {
        this.id = id;
        return this;
    }
}
