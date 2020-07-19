import myaxios from "../plugins/myaxios"

export function getTaskFormList(params) {
    return myaxios({
        url: "/taskforms",
        method: "GET",
        params
    })
}

export function getTaskFormDetail(taskFormId) {
    return myaxios({
        url: "/taskforms/" + taskFormId,
        method: "GET"
    })
}

export function getTaskFormsStatus() {
    return myaxios({
        url: "/taskforms/status",
        method: "GET"
    })
}

export function getTrasferInfo(mainsiteId) {
    return myaxios({
        url: "/transfer/" + mainsiteId,
        method: "GET"
    })
}