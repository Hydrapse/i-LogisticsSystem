package com.tcsquad.ilogistics.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.map.DistanceResult;
import com.tcsquad.ilogistics.domain.map.GeoCodingResult;
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
        return getPosition(address,null);
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
}
