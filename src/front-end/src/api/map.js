import myaxios from "../plugins/myaxios"

export function getDeliverPoints(taskFormId) {
    return myaxios({
        url: "/taskforms/"+taskFormId+"/route",
        method: "GET"
    })
}