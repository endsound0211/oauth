package com.endsound.pagination;

import com.endsound.pagination.bean.PageParam;
import org.jooq.Configuration;


public class PDSL {
    public static PDSLContext using(Configuration configuration, PageParam pageParam){
        return new PDSLContext(configuration, pageParam);
    }
}
