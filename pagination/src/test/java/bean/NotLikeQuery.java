package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.NotLike;

@PageQuery
public class NotLikeQuery {
    @NotLike("username")
    public String username;

    public String getUsername() {
        return username;
    }

    public NotLikeQuery setUsername(String username) {
        this.username = username;
        return this;
    }
}
