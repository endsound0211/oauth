/*
 * This file is generated by jOOQ.
*/
package org.jooq;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.impl.SchemaImpl;
import org.jooq.tables.Group;
import org.jooq.tables.MapGroupRole;
import org.jooq.tables.MapUserGroup;
import org.jooq.tables.Role;
import org.jooq.tables.User;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Oauth extends SchemaImpl {

    private static final long serialVersionUID = 492405952;

    /**
     * The reference instance of <code>oauth</code>
     */
    public static final Oauth OAUTH = new Oauth();

    /**
     * 群組
     */
    public final Group GROUP = org.jooq.tables.Group.GROUP;

    /**
     * 群組角色對應
     */
    public final MapGroupRole MAP_GROUP_ROLE = org.jooq.tables.MapGroupRole.MAP_GROUP_ROLE;

    /**
     * 使用者群組對應
     */
    public final MapUserGroup MAP_USER_GROUP = org.jooq.tables.MapUserGroup.MAP_USER_GROUP;

    /**
     * 角色
     */
    public final Role ROLE = org.jooq.tables.Role.ROLE;

    /**
     * 使用者基本資訊
     */
    public final User USER = org.jooq.tables.User.USER;

    /**
     * No further instances allowed
     */
    private Oauth() {
        super("oauth", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Group.GROUP,
            MapGroupRole.MAP_GROUP_ROLE,
            MapUserGroup.MAP_USER_GROUP,
            Role.ROLE,
            User.USER);
    }
}
