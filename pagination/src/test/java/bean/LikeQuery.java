package bean;

import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.query.Like;

@PageQuery
public class LikeQuery {
    @Like("username")
    public String username;

    public String getUsername() {
        return username;
    }

    public LikeQuery setUsername(String username) {
        this.username = username;
        return this;
    }
}
