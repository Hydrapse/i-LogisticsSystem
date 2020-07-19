import myaxios from "../plugins/myaxios"

export function getOrderSettings() {
    return myaxios({
        url: "/settings/orderSettings",
        method: "GET"
    })
}

export function setOrderSettings(data) {
    var formData = new FormData();
    formData.append("enable", data.enable)
    formData.append("totalPriceLimit", data.totalPriceLimit)
    formData.append("totalPriceAmount", data.totalPriceAmount)
    formData.append("categoryIdLimit", data.categoryIdLimit)
    formData.append("categoryIdWhiteList", data.categoryIdWhiteList)
    formData.append("customerIdLimit", data.customerIdLimit)
    formData.append("customerIdWhiteList", data.customerIdWhiteList)
    return myaxios({
        url: "/settings/orderSettings",
        method: "PUT",
        data: formData,
        headers: { 'Content-Type': 'multipart/form-data' }
    })
}

export function getSiteIOSettings() {
    return myaxios({
        url: "/settings/siteIOSettings",
        method: "GET"
    })
}

export function setSiteIOSettings(data) {
    var formData = new FormData()
    formData.append("enable", data.enable)
    formData.append("totalPriceLimit", data.totalPriceLimit)
    formData.append("totalPriceAmount", data.totalPriceAmount)
    formData.append("totalNumLimit", data.totalNumLimit)
    formData.append("totalNum", data.totalNum)
    formData.append("categoryIdLimit", data.categoryIdLimit)
    formData.append("categoryIdWhiteList", data.categoryIdWhiteList)
    formData.append("siteInTypeLimit", data.siteInTypeLimit)
    formData.append("siteInTypeWhiteList", data.siteInTypeWhiteList)
    formData.append("siteOutTypeLimit", data.siteOutTypeLimit)
    formData.append("siteOutTypeWhiteList", data.siteOutTypeWhiteList)
    return myaxios({
        url: "/settings/siteIOSettings",
        method: "PUT",
        data: formData,
        headers: { 'Content-Type': 'multipart/form-data' }
    })
}

export function getSiteoutSettings() {
    return myaxios({
        url: "/settings/siteoutSettings",
        method:"GET"
    })
}

export function setSiteoutSettings(params) {
    return myaxios({
        url: "/settings/siteoutSettings",
        method: "PUT",
        params
    })
}

// 配送策略
export function getTaskSettings() {
    return myaxios({
        url: "/settings/taskSettings",
        method: "GET"
    })
}

export function setTaskSettings(data) {
    return myaxios({
        url: "/settings/taskSettings",
        method: "PUT",
        data
    })
}