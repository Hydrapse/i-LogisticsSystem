package com.tcsquad.ilogistics.domain.clientele;
import com.tcsquad.ilogistics.domain.storage.Item;

import java.util.*;

/**
 *
 */
public class Supplier {
    private String supplierId;
    private String brandName;
    private String managerName;
    private String tel;
    private String email;
    private String province;
    private String city;
    private String district;
    private String addr;                //详细地址信息
    //private List<Item> itemSupplyList;//商品供应表
    private Map<String,String> supplyStatus = new HashMap<>();   //以itemId为键，以status为值

    //public String getItemSupplyStatus(String itemId){
        //return supplyStatus.get(itemId);
    //}

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Map<String, String> getSupplyStatus() {
        return supplyStatus;
    }

    public void setSupplyStatus(Map<String, String> supplyStatus) {
        this.supplyStatus = supplyStatus;
    }
}
