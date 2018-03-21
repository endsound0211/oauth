package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Lt;

@PageQuery
public class LtQuery {
    @Lt(value = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public LtQuery setId(Integer id) {
        this.id = id;
        return this;
    }
}
