package com.hjc.learn.test.optional;

import com.alibaba.fastjson.JSONObject;
import com.hjc.learn.model.CalcModel;
import com.hjc.learn.model.IndexValueCalcRelationVo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author houjichao
 */
public class OptionalOrElseTest {
    public static void main(String[] args) {
        String calcModel = "{\"indexId\":\"ceecb877c52d45e0ae18427362dde93d\",\"sourceType\":2,\"calcRule\":1,"
                + "\"calcFormula\":\"\",\"calcRelations\":[{\"relationId\":\"o886e2c8abbbb4ce18d78a1dcab0432d7\",\"relationName\":\"深圳市光明区\",\"indexValue\":36.52,\"weight\":1.0},{\"relationId\":\"o7d4db0df15c34f1987e0e6bbe1708190\",\"relationName\":\"深圳市坪山区\",\"indexValue\":38.03,\"weight\":1.0},{\"relationId\":\"oa6ed517200ca444a8c978b96acd15ba0\",\"relationName\":\"深圳市龙华区\",\"indexValue\":37.53,\"weight\":1.0},{\"relationId\":\"o0cbe197977be44c3b3e3ae3326f2d0b7\",\"relationName\":\"深圳市盐田区\",\"indexValue\":37.86,\"weight\":1.0},{\"relationId\":\"oa7749f313e0640dab0b631a00f05d78d\",\"relationName\":\"深圳市龙岗区\",\"indexValue\":37.76,\"weight\":1.0},{\"relationId\":\"o6c807bc2a67043e0a90fc20d863429ae\",\"relationName\":\"深圳市宝安区\",\"indexValue\":36.81,\"weight\":1.0},{\"relationId\":\"o8c4f40b3b4ec4feb8600f3520fd060b5\",\"relationName\":\"深圳市南山区\",\"indexValue\":37.71,\"weight\":1.0},{\"relationId\":\"odfa642073ade4e8da2832b34336a25f3\",\"relationName\":\"深圳市福田区\",\"indexValue\":35.16,\"weight\":1.0},{\"relationId\":\"occ3d0b85ec944f52bc5953367bbca351\",\"relationName\":\"深圳市罗湖区\",\"indexValue\":35.56,\"weight\":1.0},{\"relationId\":\"o8d2241b8ef93478392ddf5849cfda631\",\"relationName\":\"深圳市\",\"indexValue\":37.01,\"weight\":1.0},{\"relationId\":\"oaa88cbbe593340a6a027fe4a6157a165\",\"relationName\":\"广州市花都区\",\"indexValue\":38.29,\"weight\":1.0},{\"relationId\":\"ofe4909ce5ca34529bbfa2ae98fc69157\",\"relationName\":\"广州市增城区\",\"indexValue\":39.54,\"weight\":1.0},{\"relationId\":\"oba9d40bd019d43e18e2ba0ac7f54f4bd\",\"relationName\":\"广州市从化区\",\"indexValue\":39.03,\"weight\":1.0},{\"relationId\":\"oc570fbee4a8a49f6a59b21773507acfa\",\"relationName\":\"广州市南沙区\",\"indexValue\":40.43,\"weight\":1.0},{\"relationId\":\"o5c7a5a25f5ad48c19fccc8718a299594\",\"relationName\":\"广州市黄埔区\",\"indexValue\":38.75,\"weight\":1.0},{\"relationId\":\"o3bbc70152fdb4935a1706c529994a08a\",\"relationName\":\"广州市番禺区\",\"indexValue\":39.93,\"weight\":1.0},{\"relationId\":\"o0e7b4264a6bb40a0be55b7c2294e178b\",\"relationName\":\"广州市白云区\",\"indexValue\":38.75,\"weight\":1.0},{\"relationId\":\"o8eecb2da18b3497ca5a7bb7448d1ee39\",\"relationName\":\"广州市海珠区\",\"indexValue\":39.66,\"weight\":1.0},{\"relationId\":\"ofb3dd9c16ee14718b666e922ff66da47\",\"relationName\":\"广州市越秀区\",\"indexValue\":39.08,\"weight\":1.0},{\"relationId\":\"o39b2a0319bff46bab4de4205970d7979\",\"relationName\":\"广州市荔湾区\",\"indexValue\":39.25,\"weight\":1.0},{\"relationId\":\"o87fa9e99383f413e99289037bf289267\",\"relationName\":\"广州市天河区\",\"indexValue\":39.56,\"weight\":1.0},{\"relationId\":\"odc5edece82dc48438ce5cb6366a932d5\",\"relationName\":\"北京市\",\"indexValue\":40.45,\"weight\":1.0},{\"relationId\":\"o272b1434043143b1acc191b50e79e54b\",\"relationName\":\"广州市\",\"indexValue\":37.82,\"weight\":1.0},{\"relationId\":\"oe012501130cc44b2b42789a22d5a7b90\",\"relationName\":\"上海市\",\"indexValue\":38.63,\"weight\":1.0}]}";
        CalcModel calcModel1 = JSONObject.parseObject(calcModel, CalcModel.class);
        List<IndexValueCalcRelationVo> calcRelations = calcModel1.getCalcRelations();
        Map<String, Optional<Double>> collect = calcRelations.stream()
                .collect(Collectors.toMap(IndexValueCalcRelationVo::getRelationId,
                        vo -> Optional.ofNullable(vo.getWeight())));
        System.out.println("" + collect);
        Optional<Double> o886e2c8abbbb4ce18d78a1dcab0432d7 = collect.get("123");
        System.out.println(o886e2c8abbbb4ce18d78a1dcab0432d7.isPresent());
        Double d = o886e2c8abbbb4ce18d78a1dcab0432d7.orElse(0D);
        System.out.println("d" + d);
    }

    public static double getZero() {
        return 0D;
    }


}
