import request from '@/utils/request'

const baseService = '/jingli-admin';

// GET /sysCard/getCardList
export function getOrderList(data) {
    return request({
        url: baseService + '/sysOrder/getOrderList',
        method: 'get',
        params: data

    })
}
