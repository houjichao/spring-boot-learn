package com.hjc.learn.enums;

import com.hjc.learn.model.IfElseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author houjichao
 */
@Getter
@AllArgsConstructor
public enum IfElseDemo {

    /**
     * 1
     */
    FIRST_ENUM(1) {
        @Override
        public StringBuilder buildSql(IfElseVO vo, StringBuilder filters) {
            filters.append(addFieldEscape(vo.getName(), SEPARATOR))
                    .append(" like ").append("'").append(vo.getName()).append("_DATA_SERVICE_Q?' ").append(" and ");
            return filters;
        }
    },

    /**
     * 2
     */
    SECOND_ENUM(2) {
        @Override
        public StringBuilder buildSql(IfElseVO vo, StringBuilder filters) {
            filters.append(addFieldEscape(vo.getName(), SEPARATOR))
                    .append(" not like ").append("'").append(vo.getName()).append("_DATA_SERVICE_Q?' ").append(" and ");
            return filters;
        }
    };

    /**
     * 定义一个静态map,存放枚举映射
     */
    private static Map<Integer, IfElseDemo> map = new HashMap<>();

    /**
     * 静态代码块，根据类加载顺序，仅在静态变量后进行加载，在步骤一之后进行加载
     */
    static {
        for (IfElseDemo demo : IfElseDemo.values()) {
            map.put(demo.getType(), demo);
        }
    }

    /**
     * 根据类型查找对应的枚举类
     *
     * @param type
     * @return
     */
    public static IfElseDemo getMethodByType(Integer type) {
        // 此处根据自己代码中的类型判断进行判断，是否有相应的枚举，这里限制类型只能为1-3
        if (type > 3 || type < 0) {
            // 下面是自定义异常，也可以根据需求自定义实现业务
            throw new RuntimeException("不支持的类型type：" + type);
        }
        return map.get(type);
    }

    private final Integer type;


    private static String SEPARATOR = "`";


    public abstract StringBuilder buildSql(IfElseVO vo, StringBuilder filters);


    /**
     * 根据数据库类型加转义
     *
     * @param filedName 列名
     * @param separator 分隔符
     * @return 拼接之后的列
     */
    public String addFieldEscape(String filedName, String separator) {
        String result = "";
        String newColumnName = filedName;
        if (filedName.contains(".")) {
            result = filedName.substring(0, filedName.lastIndexOf("."));
            newColumnName = filedName.substring(filedName.lastIndexOf(".") + 1, filedName.length());
        }
        if (!(newColumnName.startsWith(separator) && newColumnName.endsWith(separator))) {
            newColumnName = separator + newColumnName + separator;
        }
        return result + newColumnName;
    }

}
