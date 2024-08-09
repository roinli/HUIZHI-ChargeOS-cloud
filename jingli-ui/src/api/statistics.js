import request from '@/utils/request'
//Statistics/getUsersAndSales
const baseService = '/jingli-admin';
export function getUsersAndSales(data) {
    return request({
        url: baseService + '/Statistics/getUsersAndSales',
        method: 'get',
        params: data

    })
}

// Statistics/getSalesDetail
export function getSalesDetail(data) {
    return request({
        url: baseService + '/Statistics/getSalesDetail',
        method: 'get',
        params: data

    })
}

///Statistics/getSalesData
export function getSalesData(data) {
    return request({
        url: baseService + '/Statistics/getSalesData',
        method: 'get',
        params: data

    })
}

///Statistics/getNewUserCount
export function getNewUserCount(data) {
    return request({
        url: baseService + '/Statistics/getNewUserCount',
        method: 'get',
        params: data

    })
}

// Statistics/getPilesInfo
export function getPilesInfo(data) {
    return request({
        url: baseService + '/Statistics/getPilesInfo',
        method: 'get',
        params: data
    })
}

// Statistics/getDayRecharge
export function getDayRecharge(data) {
    return request({
        url: baseService + '/Statistics/getDayRecharge',
        method: 'get',
        params: data
    })
}

export function getManageTotalData(data) {
    return request({
        url: baseService + '/Statistics/getManageTotalData',
        method: 'post',
        data: data

    })
}
