package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Ge;

@PageQuery
public class GeQuery {
    @Ge(value = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public GeQuery setId(Integer id) {
        this.id = id;
        return this;
    }
}
