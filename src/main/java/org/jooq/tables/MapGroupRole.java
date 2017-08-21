/*
 * This file is generated by jOOQ.
*/
package org.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Keys;
import org.jooq.Oauth;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.tables.records.MapGroupRoleRecord;


/**
 * 群組角色對應
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MapGroupRole extends TableImpl<MapGroupRoleRecord> {

    private static final long serialVersionUID = -448723452;

    /**
     * The reference instance of <code>oauth.map_group_role</code>
     */
    public static final MapGroupRole MAP_GROUP_ROLE = new MapGroupRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MapGroupRoleRecord> getRecordType() {
        return MapGroupRoleRecord.class;
    }

    /**
     * The column <code>oauth.map_group_role.group_id</code>. 群組ID
     */
    public final TableField<MapGroupRoleRecord, Integer> GROUP_ID = createField("group_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "群組ID");

    /**
     * The column <code>oauth.map_group_role.role_id</code>. 角色ID
     */
    public final TableField<MapGroupRoleRecord, Integer> ROLE_ID = createField("role_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "角色ID");

    /**
     * Create a <code>oauth.map_group_role</code> table reference
     */
    public MapGroupRole() {
        this("map_group_role", null);
    }

    /**
     * Create an aliased <code>oauth.map_group_role</code> table reference
     */
    public MapGroupRole(String alias) {
        this(alias, MAP_GROUP_ROLE);
    }

    private MapGroupRole(String alias, Table<MapGroupRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private MapGroupRole(String alias, Table<MapGroupRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "群組角色對應");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Oauth.OAUTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MapGroupRoleRecord> getPrimaryKey() {
        return Keys.KEY_MAP_GROUP_ROLE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MapGroupRoleRecord>> getKeys() {
        return Arrays.<UniqueKey<MapGroupRoleRecord>>asList(Keys.KEY_MAP_GROUP_ROLE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<MapGroupRoleRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MapGroupRoleRecord, ?>>asList(Keys.FK_MAP_GROUP_ROLE_GROUP, Keys.FK_MAP_GROUP_ROLE_ROLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapGroupRole as(String alias) {
        return new MapGroupRole(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MapGroupRole rename(String name) {
        return new MapGroupRole(name, null);
    }
}
