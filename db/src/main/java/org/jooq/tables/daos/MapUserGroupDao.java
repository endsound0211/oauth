/*
 * This file is generated by jOOQ.
*/
package org.jooq.tables.daos;


import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;
import org.jooq.tables.MapUserGroup;
import org.jooq.tables.records.MapUserGroupRecord;


/**
 * 使用者群組對應
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MapUserGroupDao extends DAOImpl<MapUserGroupRecord, org.jooq.tables.pojos.MapUserGroup, Record2<Integer, Integer>> {

    /**
     * Create a new MapUserGroupDao without any configuration
     */
    public MapUserGroupDao() {
        super(MapUserGroup.MAP_USER_GROUP, org.jooq.tables.pojos.MapUserGroup.class);
    }

    /**
     * Create a new MapUserGroupDao with an attached configuration
     */
    public MapUserGroupDao(Configuration configuration) {
        super(MapUserGroup.MAP_USER_GROUP, org.jooq.tables.pojos.MapUserGroup.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<Integer, Integer> getId(org.jooq.tables.pojos.MapUserGroup object) {
        return compositeKeyRecord(object.getUserId(), object.getGroupId());
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<org.jooq.tables.pojos.MapUserGroup> fetchByUserId(Integer... values) {
        return fetch(MapUserGroup.MAP_USER_GROUP.USER_ID, values);
    }

    /**
     * Fetch records that have <code>group_id IN (values)</code>
     */
    public List<org.jooq.tables.pojos.MapUserGroup> fetchByGroupId(Integer... values) {
        return fetch(MapUserGroup.MAP_USER_GROUP.GROUP_ID, values);
    }
}
