package com.tcsquad.ilogistics.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.map.DistanceResult;
import com.tcsquad.ilogistics.domain.map.GeoCodingResult;
import com.tcsquad.ilogistics.domain.map.RouteResult;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 提供地址信息验证, 地图等服务
 **/
@Service
public class AddressService {

    @Value("${map.ak}")
    private String ak;

    @Value("${map.sn}")
    private String sn;

    private HttpClient httpClient = HttpClient.newHttpClient();

    private static String coordinateToString(Pair<Double, Double> coordinate) {
        if (coordinate.getFirst() != null && coordinate.getSecond() != null) {
            return new String(coordinate.getFirst() + "," + coordinate.getSecond());
        } else {
            throw new BusinessErrorException("坐标不能为空", ErrorCode.MISS_PARAMS.getCode());
        }
    }

    private HttpResponse<String> send(String uri) {
        try {
            return httpClient.send(HttpRequest.newBuilder().uri(URI.create(uri)).build(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T send(String uri, Class<T> valueType) {
        var response = send(uri);
        if (response == null)
            throw new BusinessErrorException("发送失败", ErrorCode.CONNECTION_ERROR.getCode());

        try {
            return new ObjectMapper().readValue(response.body(), valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GeoCodingResult.Location getPosition(String address, @Nullable String city) {

        String uri = "http://api.map.baidu.com/geocoding/v3/?&output=json"
                + "&address=" + address
                + (city != null ? "&city=" + city : "")
                + "&ak=" + ak
                + "&sn=" + sn;
        var tmp = send(uri, GeoCodingResult.class);
        return tmp.getResult().getLocation();
    }

    public GeoCodingResult.Location getPosition(String address) {
        return getPosition(address, null);
    }

    public List<DistanceResult.Result> distance(Pair<Double, Double> startPoint, List<Pair<Double, Double>> endPoints) {
        if (startPoint == null)
            throw new BusinessErrorException("起始点不能为空", ErrorCode.MISS_PARAMS.getCode());
        if (endPoints == null || endPoints.size() == 0)
            throw new BusinessErrorException("结束点列表不能为空", ErrorCode.MISS_PARAMS.getCode());

        List<DistanceResult.Result> result = null;
        for (int i = 0; i < endPoints.size(); i += 50) { //API最多一次支持50个
            int j = Math.min(i + 50, endPoints.size());
            var query = endPoints.subList(i, j);

            String destinations = "";
            for (Pair<Double, Double> endPoint : query) {
                if (destinations.equals("")) {
                    destinations = AddressService.coordinateToString(endPoint);
                } else {
                    destinations += "|" + AddressService.coordinateToString(endPoint);
                }
            }

            String uri = "https://api.map.baidu.com/routematrix/v2/driving?output=json"
                    + "&origins=" + AddressService.coordinateToString(startPoint)
                    + "&destinations=" + destinations
                    + "&ak=" + ak;
//            uri = "http://127.0.0.1:4523/mock/348623/test/distance";
            var tmp = send(uri, DistanceResult.class);
            if (tmp.getStatus() != 0) {
                if (tmp.getStatus() == 1)
                    throw new BusinessErrorException(tmp.getMessage(), ErrorCode.OUTSIDE_SERVER_ERROR.getCode());
                else if (tmp.getStatus() == 2)
                    throw new BusinessErrorException(tmp.getMessage(), ErrorCode.PARAMS_ERROR.getCode());
                else
                    throw new BusinessErrorException(tmp.getMessage(), ErrorCode.CONNECTION_ERROR.getCode());
            }

            if (result == null)
                result = tmp.getResult();
            else
                result.addAll(tmp.getResult());

        }
        return result;
    }

    /**
     * @param from         起始坐标
     * @param to           结束坐标
     * @param waypoints    途径坐标串 （小于18个）
     * @param tactics      策略 (0：默认, 2：距离最短（只返回一条路线，不考虑限行和路况，距离最短且稳定，用于估价场景）, 3：不走高速, 4：高速优先, 5：躲避拥堵, 6：少收费, 7: 躲避拥堵 & 高速优先, 8: 躲避拥堵 & 不走高速, 9: 躲避拥堵 & 少收费, 10: 躲避拥堵 & 不走高速 & 少收费, 11: 不走高速 & 少收费, 12: 距离优先（考虑限行和路况，距离相对短且不一定稳定）)
     * @param alternatives 是否返回备选路线 (false:返回一条推荐路线 ,true:返回1-3条路线供选择)
     * @return
     */
    public RouteResult route(Pair<Double, Double> from, List<Pair<Double, Double>> waypoints, Pair<Double, Double> to, int tactics, boolean alternatives) {
        if (from == null || to == null) {
            throw new BusinessErrorException("坐标不能为空", ErrorCode.MISS_PARAMS.getCode());
        }
        if (!List.of(0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).contains(tactics))
            throw new BusinessErrorException("策略不存在", ErrorCode.PARAMS_ERROR.getCode());

        String way = null;
        if (waypoints != null && !waypoints.isEmpty()) {
            if (waypoints.size() > 18)
                throw new BusinessErrorException("途径坐标不能超过18个", ErrorCode.MISS_PARAMS.getCode());

            for (var point : waypoints) {
                if (point == null)
                    throw new BusinessErrorException("坐标不能为空", ErrorCode.MISS_PARAMS.getCode());

                if (way == null)
                    way = AddressService.coordinateToString(point);
                else
                    way += "|" + AddressService.coordinateToString(point);
            }
        }

        String uri = "http://api.map.baidu.com/direction/v2/driving"
                + "?origin=" + AddressService.coordinateToString(from)
                + "&destination=" + AddressService.coordinateToString(to)
                + (way != null ? "&waypoints=" + way : "")
                + "&tactics=" + tactics
                + "&alternatives=" + (alternatives ? 1 : 0)
                + "&ak=" + ak;
        return send(uri,RouteResult.class);
    }
}
