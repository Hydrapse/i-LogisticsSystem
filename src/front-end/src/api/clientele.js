import myaxios from "../plugins/myaxios"

export function getCustomers(params) {
    return myaxios({
        url: "/clientele/customers",
        method: "GET",
        params
    })
}

export function getSuppliers(params) {
    return myaxios({
        url: "/clientele/suppliers",
        method: "GET",
        params
    })
}

export function addSupplier(data) {
    return myaxios({
        url: "/suppliers",
        method: "POST",
        data
        // headers: {
        //     "Content-Type": "application/json;"
        // }
    })
}

export function deleteSupplier(supplierId) {
    return myaxios({
        url: "/clientele/suppliers/" + supplierId,
        method: "DELETE"
    })
}

export function modifySupplier(supplierId, params) {
    return myaxios({
        url: "/clientele/supplier/" + supplierId,
        method: "PATCH",
        params
    })
}

export function getSupplierItems(supplierId) {
    return myaxios({
        url: "/clientele/suppliers/" + supplierId + "/itemSupply",
        method: "GET"
    })
}

export function billNameAutoCompl(key) {
    return myaxios({
        url: "/orders/billNames",
        method: "GET",
        params: { infix: key }
    })
}

export function userIdAutoCompl(key) {
    return myaxios({
        url: "/clientele/customerIds",
        method: "GET",
        params: { infix: key }
    })
}