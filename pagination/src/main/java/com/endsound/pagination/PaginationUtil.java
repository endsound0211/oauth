package com.endsound.pagination;


import com.endsound.pagination.annotation.PageQuery;
import com.endsound.pagination.annotation.PageQueryPolicy;
import com.endsound.pagination.bean.QueryParam;
import com.endsound.pagination.exception.FieldNotFoundException;
import com.endsound.pagination.exception.PageQueryPolicyNotSetException;
import com.endsound.pagination.provider.*;
import org.jooq.Condition;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.jooq.lambda.Unchecked;
import org.springframework.core.annotation.AnnotationUtils;
import java.util.*;
import java.util.stream.Collectors;

public class PaginationUtil {
    private static List<QueryConditionProvider> providers = Arrays.asList(
            new EqProvider(),
            new InProvider(),
            new LikeProvider(),
            new NeProvider(),
            new NotInProvider(),
            new NotLikeProvider(),
            new LtProvider(),
            new LeProvider()
    );

    protected static Condition genCondition(Object queryObject, Table table) {

        if(Objects.isNull(queryObject))
            return DSL.trueCondition();

        Class clazz = queryObject.getClass();

        PageQuery pageQuery = Optional.ofNullable(AnnotationUtils.findAnnotation(clazz, PageQuery.class))
                .orElseThrow(() -> new PageQueryPolicyNotSetException());

        return genDiffGroupCondition(pageQuery.diffGroupPolicy(), genSameGroupCondition(pageQuery.sameGroupPolicy(), genSamGroupConditionMap(queryObject, table)));

    }

    private static Map<Integer, List<Condition>> genSamGroupConditionMap(Object queryObject, Table table){
        Class clazz = queryObject.getClass();

        Map<Integer, List<Condition>> sameGroupConditionMap = new HashMap<Integer, List<Condition>>();

        Arrays.asList(clazz.getDeclaredFields()).stream()
                .forEach(field -> Arrays.asList(field.getAnnotations()).stream()
                        .forEach(annotation -> providers.stream()
                                .filter(provider -> provider.support(annotation, field))
                                .findFirst()
                                .ifPresent(Unchecked.consumer(provider -> {
                                    Integer group = (Integer) AnnotationUtils.getValue(annotation, "group");
                                    String fieldName = (String) AnnotationUtils.getValue(annotation, "value");
                                    field.setAccessible(true);

                                    QueryParam queryParam = new QueryParam()
                                            .setGroup(group)
                                            .setTable(table)
                                            .setField(
                                                    Optional.ofNullable(table.field(fieldName))
                                                            .orElseThrow(() -> new FieldNotFoundException(fieldName))
                                            ).setData(field.get(queryObject));

                                    if(Objects.isNull(sameGroupConditionMap.get(group)))
                                        sameGroupConditionMap.put(group, new ArrayList<Condition>());

                                    sameGroupConditionMap.get(group).add(provider.genCondition(queryParam));
                                }))
                        )
                );

        return sameGroupConditionMap;
    }

    private static List<Condition> genSameGroupCondition(PageQueryPolicy policy, Map<Integer, List<Condition>> sameGroupConditionMap){
        return sameGroupConditionMap.entrySet().parallelStream()
                .map(entry -> {
                    switch (policy){
                        case OR:
                            return DSL.or(entry.getValue());
                        case AND:
                            return DSL.and(entry.getValue());
                        default:
                            return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static Condition genDiffGroupCondition(PageQueryPolicy policy, List<Condition> diffGroupConditions){
        switch (policy){
            case OR:
                return DSL.or(diffGroupConditions);
            case AND:
                return DSL.and(diffGroupConditions);
            default:
                return null;
        }
    }

}
