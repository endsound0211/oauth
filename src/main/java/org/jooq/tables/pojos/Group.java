/*
 * This file is generated by jOOQ.
*/
package org.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * 群組
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Group implements Serializable {

    private static final long serialVersionUID = 1760980404;

    private Integer id;
    private String  name;

    public Group() {}

    public Group(Group value) {
        this.id = value.id;
        this.name = value.name;
    }

    public Group(
        Integer id,
        String  name
    ) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Group (");

        sb.append(id);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
