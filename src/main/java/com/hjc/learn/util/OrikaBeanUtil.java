package com.hjc.learn.util;

import com.hjc.learn.convert.JsonConfigConvert;
import com.hjc.learn.entity.Book;
import com.hjc.learn.enums.BookType;
import com.hjc.learn.model.BookDTO;
import com.hjc.learn.model.BookInfo;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * orika定义：
 * simpler, lighter and faster Java bean mapping framework.
 * 更简单、更轻、更快的Java bean映射框架
 * <p>
 * 1、浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝。
 * 2、深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。
 *
 * @author houjichao
 */
public class OrikaBeanUtil {

    /**
     * 默认字段工厂
     */
    private static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    /**
     * 默认字段实例
     */
    private static final MapperFacade MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

    /**
     * 默认字段实例集合
     */
    private static Map<String, MapperFacade> CACHE_MAPPER_FACADE_MAP = new ConcurrentHashMap<>();

    /**
     * 映射实体（默认字段）
     *
     * @param toClass 映射类对象
     * @param data    数据（对象）
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data) {
        return MAPPER_FACADE.map(data, toClass);
    }

    /**
     * 映射实体（自定义配置）
     *
     * @param toClass   映射类对象
     * @param data      数据（对象）
     * @param configMap 自定义配置
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data, Map<String, String> configMap) {
        MapperFacade mapperFacade = this.getMapperFacade(toClass, data.getClass(), configMap);
        return mapperFacade.map(data, toClass);
    }

    /**
     * 映射集合（默认字段）
     *
     * @param toClass 映射类对象
     * @param data    数据（集合）
     * @return 映射类对象
     */
    public <E, T> List<E> mapAsList(Class<E> toClass, Collection<T> data) {
        return MAPPER_FACADE.mapAsList(data, toClass);
    }


    /**
     * 映射集合（自定义配置）
     *
     * @param toClass   映射类
     * @param data      数据（集合）
     * @param configMap 自定义配置
     * @return 映射类对象
     */
    public <E, T> List<E> mapAsList(Class<E> toClass, Collection<T> data, Map<String, String> configMap) {
        T t = data.stream().findFirst().orElseThrow(() -> new RuntimeException("映射集合，数据集合为空"));
        MapperFacade mapperFacade = this.getMapperFacade(toClass, t.getClass(), configMap);
        return mapperFacade.mapAsList(data, toClass);
    }

    /**
     * 获取自定义映射
     *
     * @param toClass   映射类
     * @param dataClass 数据映射类
     * @param configMap 自定义配置
     * @return 映射类对象
     */
    private <E, T> MapperFacade getMapperFacade(Class<E> toClass, Class<T> dataClass, Map<String, String> configMap) {
        String mapKey = dataClass.getCanonicalName() + "_" + toClass.getCanonicalName();
        MapperFacade mapperFacade = CACHE_MAPPER_FACADE_MAP.get(mapKey);
        if (Objects.isNull(mapperFacade)) {
            MapperFactory factory = new DefaultMapperFactory.Builder().build();
            ClassMapBuilder classMapBuilder = factory.classMap(dataClass, toClass);
            configMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault().register();
            mapperFacade = factory.getMapperFacade();
            CACHE_MAPPER_FACADE_MAP.put(mapKey, mapperFacade);
        }
        return mapperFacade;
    }


    public static void main(String[] args) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        //在数据库中存tinyint类型的字段，而在表现层需要将这个数值转换成一个具体意义的枚举类型.
        mapperFactory
                .getConverterFactory()
                .registerConverter("bookTypeConvert", new BidirectionalConverter<BookType, Byte>() {
                    @Override
                    public Byte convertTo(BookType bookType, Type<Byte> type, MappingContext mappingContext) {
                        return bookType.getCode();
                    }

                    @Override
                    public BookType convertFrom(Byte aByte, Type<BookType> type, MappingContext mappingContext) {
                        return BookType.getByCode(aByte);
                    }
                });


        //对于Json 和 String类型的转换，定义了一个更加通用的Convert，这里Json的序列化工具使用的是fastjson
        mapperFactory
                .getConverterFactory()
                .registerConverter("bookInfoConvert", new JsonConfigConvert<BookInfo>());

        mapperFactory
                .classMap(Book.class, BookDTO.class)
                .field("authorName", "author.name")
                .field("authorBirthday", "author.birthday")
                .fieldMap("type", "bookType").converter("bookTypeConvert").add()
                .fieldMap("bookInformation", "bookInfo").converter("bookInfoConvert").add()
                .byDefault()
                .register();


        MapperFacade mapper = mapperFactory.getMapperFacade();

        Book bookEntity = new Book(
                "银河系漫游指南",
                "道格拉斯·亚当斯",
                Date.from(LocalDate.of(1952, Month.MARCH, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "{\"ISBN\": \"9787532754687\", \n \"page\": 279\n }",
                new Integer(1).byteValue());

        //集合复制
        //mapper.mapAsList();
        final BookDTO bookDTO = mapper.map(bookEntity, BookDTO.class);

        System.out.print("bookDTO:" + bookDTO);

    }
}
