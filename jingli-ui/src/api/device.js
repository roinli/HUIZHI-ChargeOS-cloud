import request from '@/utils/request'
// chargingpile/getChargingPileList
const baseService = '/jingli-admin';
export function getChargingPileList(data) {
    return request({
        url: baseService + '/chargingpile/getChargingPileList',
        method: 'get',
        params: data

    })
}
